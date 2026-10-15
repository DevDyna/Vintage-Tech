
package com.synergy.vintagetech.api.datagen.builders;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

import org.spongepowered.include.com.google.common.collect.Lists;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.synergy.vintagetech.init.types.zWorldGenFeatures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class OliveTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<OliveTrunkPlacer> CODEC = RecordCodecBuilder
            .mapCodec(i -> trunkPlacerParts(i).apply(i, OliveTrunkPlacer::new));

    public OliveTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return zWorldGenFeatures.TrunkPlacer.OLIVE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter, RandomSource random, int treeHeight, BlockPos origin,
            TreeConfiguration config) {

        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);

        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        var logPos = new BlockPos.MutableBlockPos();

        var baseDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);


        var leanSteps = Math.max(2, treeHeight / 2);

        var tx = origin.getX();
        var tz = origin.getZ();

        var endY = OptionalInt.empty();

        for (int y = 0; y < treeHeight; y++) {
            if (y >= Math.max(2, treeHeight / 3) && leanSteps > 0 && (y - Math.max(2, treeHeight / 3)) % 2 == 0) {
                tx += baseDir.getStepX();
                tz += baseDir.getStepZ();
                leanSteps--;
            }

            if (placeLog(level, trunkSetter, random, logPos.set(tx, origin.getY() + y, tz), config))
                endY = OptionalInt.of(origin.getY() + y + 1);

        }

        if (endY.isPresent())
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(tx, endY.getAsInt(), tz), 1, false));

        var forkDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        if (forkDir != baseDir) {
            tx = origin.getX();
            tz = origin.getZ();
            endY = OptionalInt.empty();
            int branchStart = Math.max(2, treeHeight / 3);
            int branchLength = 2 + random.nextInt(3);

            for (var i = 0; i < branchLength; i++) {

                var y = origin.getY() + branchStart + i;
                tx += forkDir.getStepX();
                tz += forkDir.getStepZ();

                if (placeLog(level, trunkSetter, random, logPos.set(tx, y, tz), config))
                    endY = OptionalInt.of(y + 1);

            }
            if (endY.isPresent())
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(tx, endY.getAsInt(), tz), 0, false));

        }

        var secDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        if (secDir != baseDir && secDir != forkDir) {
            tx = origin.getX();
            tz = origin.getZ();
            endY = OptionalInt.empty();

            for (var i = 0; i < (2 + random.nextInt(2)); i++) {
                var y = origin.getY() + Math.max(2, treeHeight / 2) + i;
                tx += secDir.getStepX();
                tz += secDir.getStepZ();

                if (placeLog(level, trunkSetter, random, logPos.set(tx, y, tz), config))
                    endY = OptionalInt.of(y + 1);

            }

            if (endY.isPresent())
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(tx, endY.getAsInt(), tz), 0, false));

        }
        return list;
    }

}
