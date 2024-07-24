package org.teamvoided.krabnet.particle

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleType
import org.teamvoided.krabnet.init.KNParticleTypes

data class ConfettiEffect(val int: Int) : ParticleEffect {

    override fun getType(): ParticleType<ConfettiEffect> = KNParticleTypes.CONFETTI_EMITTER

    companion object {
        val CODEC: MapCodec<ConfettiEffect> =
            RecordCodecBuilder.mapCodec { instance ->
                instance.group(Codec.INT.fieldOf("int").forGetter { it.int })
                    .apply(instance, ::ConfettiEffect)
            }
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, ConfettiEffect> =
            PacketCodec.tuple(PacketCodecs.INT, { it.int }, ::ConfettiEffect)
    }
}