package org.teamvoided.krabnet.init

import net.minecraft.component.DataComponentType
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.dynamic.Codecs
import org.teamvoided.krabnet.KrabNet.id
import java.util.function.UnaryOperator

object KNDataComponents {
    fun init() = Unit

    val CONFETTI_LEVEL: DataComponentType<Int> = register("confetti_level") {
         it.codec(Codecs.createRangedInt(1, 24)).packetCodec(PacketCodecs.VAR_INT)
    }

    private fun <T> register(id: String, operator: UnaryOperator<DataComponentType.Builder<T>>): DataComponentType<T> {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE, id(id),
            operator.apply(DataComponentType.builder()).build()
        )
    }
}