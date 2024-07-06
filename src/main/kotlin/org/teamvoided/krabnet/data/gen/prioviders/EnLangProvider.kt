package org.teamvoided.krabnet.data.gen.prioviders

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils
import org.teamvoided.krabnet.init.KNItems
import org.teamvoided.krabnet.init.KNTabs
import java.util.concurrent.CompletableFuture

class EnLangProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(o, r) {
    override fun generateTranslations(reg: HolderLookup.Provider, gen: TranslationBuilder) {
        KNItems.tabItems.forEach { gen.add(it.translationKey, lang(it.id)) }

        KNTabs.KN_TAB.key.ifPresent { gen.add(it, lang(it.value)) }
    }

    private fun lang(item: Identifier): String = WordUtils.capitalize(item.path.replace("_", " "))

    private val Item.id get() = Registries.ITEM.getId(this)
}