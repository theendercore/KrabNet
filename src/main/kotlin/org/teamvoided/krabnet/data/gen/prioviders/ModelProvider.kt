package org.teamvoided.krabnet.data.gen.prioviders

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import net.minecraft.item.Items
import org.teamvoided.krabnet.init.KNItems

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {}

    override fun generateItemModels(gen: ItemModelGenerator) {
        gen.register(KNItems.CONFETTI_STICK, Items.STICK, Models.SINGLE_LAYER_ITEM)
    }
}