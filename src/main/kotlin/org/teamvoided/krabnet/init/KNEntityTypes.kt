package org.teamvoided.krabnet.init

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.entity.ConfettiBombEntity
import org.teamvoided.krabnet.utils.register

object KNEntityTypes {
    fun init()  = Unit

    val CONFETTI_BOMB = register(
        "confetti_bomb",
        EntityType.Builder.create(EntityType.EntityFactory(::ConfettiBombEntity), SpawnGroup.MISC)
            .setDimensions(0.2f, 0.2f).setEyeHeight(0.15f)
    )

    @Suppress("UNCHECKED_CAST")
    private fun <J : Entity, T : EntityType.Builder<J>> register(name: String, entityBuilder: T): EntityType<J> =
        Registries.ENTITY_TYPE.register(id(name), entityBuilder.build()) as EntityType<J>

}