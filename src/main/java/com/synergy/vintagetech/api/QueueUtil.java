package com.synergy.vintagetech.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Create a generic queue list to check multiple elements based on a mutable value
 */
// TODO API : move to api
public class QueueUtil<T> {

    private boolean add_start = true;
    private Function<Queue<T>, Boolean> loop_when = q -> !q.isEmpty();
    private T start;
    private List<BiFunction<Queue<T>, T, QueueStatus>> chain = List
            .of((queue, pos) -> QueueStatus.SUCCESS);

    public QueueUtil(T pos) {
        this.start = pos;
    }

    public static <T> QueueUtil<T> of(T pos) {
        return new QueueUtil<>(pos);
    }

    public QueueUtil<T> ignoreStart() {
        this.add_start = false;
        return this;
    }

    public QueueUtil<T> condition(Function<Queue<T>, Boolean> loop_when) {
        this.loop_when = loop_when;
        return this;
    }

    public QueueUtil<T> define(List<BiFunction<Queue<T>, T, QueueStatus>> chain) {
        this.chain = new ArrayList<>(chain);
        return this;
    }

    public QueueUtil<T> clear() {
        this.chain = new ArrayList<>();
        return this;
    }

    public QueueUtil<T> define(BiFunction<Queue<T>, T, QueueStatus> action) {
        this.chain.add(action);
        return this;
    }

    public boolean run() {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

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
