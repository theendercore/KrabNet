package org.teamvoided.krabnet.text

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.text.component.TextComponent
import net.minecraft.util.Identifier

class ImageComponent(val texture: Identifier) : TextComponent {
    override fun type() = TYPE
    override fun toString(): String = "Image=[$texture]"
    companion object {
        val CODEC: MapCodec<ImageComponent> = RecordCodecBuilder.mapCodec {
            it
                .group(Identifier.CODEC.fieldOf("id").forGetter(ImageComponent::texture))
                .apply(it, ::ImageComponent)
        }
        val TYPE = TextComponent.Type<ImageComponent>(CODEC, "selector")
    }
}