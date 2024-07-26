package org.teamvoided.krabnet.data.gen.prioviders

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Model
import net.minecraft.data.client.model.ModelIds
import net.minecraft.data.client.model.Models
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import org.teamvoided.krabnet.init.KNItems
import java.util.*

class ModelProvider(o: FabricDataOutput) : FabricModelProvider(o) {
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {}

    override fun generateItemModels(gen: ItemModelGenerator) {
        gen.register(KNItems.PARTY_POPPER, Models.HANDHELD_MACE)
        gen.register(KNItems.CONFETTI_BOMB, Models.SINGLE_LAYER_ITEM)
    }

    private val <T : Any> T.myb get() = Optional.of<T>(this)

    private fun parentedModel(item: Item) = Model(ModelIds.getItemModelId(item).myb, Optional.empty())

    fun parentedItemModel(id: Identifier) = Model(Optional.of(id.withPrefix("item/")), Optional.empty())

}