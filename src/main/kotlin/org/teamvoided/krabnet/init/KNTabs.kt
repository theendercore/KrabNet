package org.teamvoided.krabnet.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.utils.*

object KNTabs {
    fun init() = Unit

    val KN_TAB = register(
        "krabnet", FabricItemGroup.builder()
            .icon { KNItems.PARTY_POPPER.defaultStack }
            .name(Text.translatable("itemGroup.krabnet"))
            .entries { _, entries ->
                entries.addItems(
                    KNItems.PARTY_POPPER,
                    KNItems.CONFETTI_BOMB
                )
                entries.addStacks(
                    listOf(
                        KNItems.PARTY_POPPER.stackWithMaxLevel().setColor(0xff0f0f ),
                        KNItems.CONFETTI_BOMB.stackWithMaxLevel(),
                        KNItems.PARTY_POPPER.defaultStack.setColor(0x9842ac ).setUnbreakable()
                            .setCustomName("Ender's Party Popper")
                    )
                )

            }
    )


    @Suppress("SameParameterValue")
    private fun <T : ItemGroup.Builder> register(name: String, tab: T): Holder<ItemGroup> =
        Registries.ITEM_GROUP.registerHolder(id(name), tab.build())


}

private fun ItemGroup.ItemStackCollector.addItems(vararg item: Item) = this.addStacks(item.map(Item::getDefaultStack))

