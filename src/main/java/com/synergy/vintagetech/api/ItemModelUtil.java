package com.synergy.vintagetech.api;

import java.util.Optional;

import com.devdyna.cakesticklib.api.utils.x;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.item.DynamicFluidContainerModel;

public class ItemModelUtil {

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
            boolean coverIsMask, boolean applyFluidLuminosity,
            boolean forceOpaqueFluid, Identifier container, Identifier mask) {
                var item = (BucketItem)bucket;
        itemModels.itemModelOutput.accept(item,
                new DynamicFluidContainerModel.Unbaked(
                        new DynamicFluidContainerModel.Textures(
                                Optional.empty(),
                                Optional.of(new Material(container)),
                                Optional.of(new Material(mask)),
                                Optional.empty()),
                        item.content, flipGas, coverIsMask, applyFluidLuminosity, forceOpaqueFluid));

    }

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
            boolean coverIsMask, boolean applyFluidLuminosity,
            boolean forceOpaqueFluid) {
        createBucketItem(itemModels, bucket, flipGas, coverIsMask, applyFluidLuminosity, forceOpaqueFluid,
                x.mcLoc("item/bucket"), x.rl("neoforge", "item/mask/bucket_fluid_drip"));

    }

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
            boolean coverIsMask, boolean applyFluidLuminosity) {
        createBucketItem(itemModels, bucket, flipGas, coverIsMask, applyFluidLuminosity, true);
    }

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas,
            boolean coverIsMask) {
        createBucketItem(itemModels, bucket, flipGas, coverIsMask, true);
    }

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket, boolean flipGas) {
        createBucketItem(itemModels, bucket, flipGas, false);
    }

    public static void createBucketItem(ItemModelGenerators itemModels, Item bucket) {
        createBucketItem(itemModels, bucket, false);
    }
}
