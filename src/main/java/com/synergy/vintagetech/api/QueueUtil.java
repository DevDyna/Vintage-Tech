package com.synergy.vintagetech.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.minecraft.core.BlockPos;

/**
 * Create a generic blockpos queue
 */
// TODO API : move to api
public class QueueUtil {

    private boolean add_start = true;
    private Function<Queue<BlockPos>, Boolean> loop_when = q -> !q.isEmpty();
    private BlockPos start;
    private List<BiFunction<Queue<BlockPos>, BlockPos, QueueStatus>> chain = List
            .of((queue, pos) -> QueueStatus.SUCCESS);

    public QueueUtil(BlockPos pos) {
        this.start = pos;
    }

    public static QueueUtil of(BlockPos pos) {
        return new QueueUtil(pos);
    }

    public QueueUtil ignoreStart() {
        this.add_start = false;
        return this;
    }

    public QueueUtil condition(Function<Queue<BlockPos>, Boolean> loop_when) {
        this.loop_when = loop_when;
        return this;
    }

    public QueueUtil define(List<BiFunction<Queue<BlockPos>, BlockPos, QueueStatus>> chain) {
        this.chain = new ArrayList<>(chain);
        return this;
    }

    public QueueUtil clear() {
        this.chain = new ArrayList<>();
        return this;
    }

    public QueueUtil define(BiFunction<Queue<BlockPos>, BlockPos, QueueStatus> action) {
        this.chain.add(action);
        return this;
    }

    public boolean run() {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        if (add_start)
            queue.add(start);

        main: while (loop_when.apply(queue)) {
            var pos = queue.poll();

            if (!visited.add(pos))
                continue;

            for (var operation : chain)
                switch (operation.apply(queue, pos)) {
                    case QueueStatus.SUCCESS:
                        return true;
                    case QueueStatus.FAIL:
                        return false;
                    case QueueStatus.CONTINUE:
                        continue main;
                }

        }

        return false;
    }

    public enum QueueStatus {
        CONTINUE(),
        FAIL(),
        SUCCESS();
    }
}
