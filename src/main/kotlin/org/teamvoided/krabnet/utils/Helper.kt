package org.teamvoided.krabnet.utils

import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.DyedColorComponent
import net.minecraft.component.type.UnbreakableComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleEffect
import net.minecraft.registry.Holder
import net.minecraft.registry.Registry
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import org.teamvoided.krabnet.init.KNDataComponents

fun <T> Registry<T>.register(id: Identifier, entry: T): T = Registry.register(this, id, entry)
fun <T> Registry<T>.registerHolder(id: Identifier, entry: T): Holder<T> = Registry.registerHolder(this, id, entry)

//fun World.addParticle(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
//    this.addParticle(particle, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z)

fun ServerWorld.spawnParticles(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
    this.spawnParticles(particle, pos.x, pos.y, pos.z, 0, velocity.x, velocity.y, velocity.z, 1.0)

fun ServerWorld.playSound(pos: Vec3d, sound: SoundEvent, category: SoundCategory, volume: Float, pitch: Float) =
    this.playSound(null, pos.x, pos.y, pos.z, sound, category, volume, pitch)

//fun RandomGenerator.posOrNeg(): Int = if (this.nextBoolean()) 1 else -1


fun ItemStack.confettiLevel(): Int? = this.get(KNDataComponents.CONFETTI_LEVEL)


fun Item.stackWithMaxLevel(): ItemStack {
    val stack = this.defaultStack
    stack.set(KNDataComponents.CONFETTI_LEVEL, 10)
    return stack
}

fun ItemStack.setColor(color: Int, showInTooltip: Boolean = false): ItemStack {
    this.set(
        DataComponentTypes.DYED_COLOR, DyedColorComponent(
            color, this.get(DataComponentTypes.DYED_COLOR)?.shownInTooltip ?: showInTooltip
        )
    )
    return this
}

fun ItemStack.setUnbreakable(showInTooltip: Boolean = true): ItemStack {
    this.set(DataComponentTypes.UNBREAKABLE, UnbreakableComponent(showInTooltip))
    return this
}

fun ItemStack.setCustomName(text: String): ItemStack {
    this.set(DataComponentTypes.CUSTOM_NAME, Text.literal(text))
    return this
}