package org.teamvoided.krabnet.utils

import net.minecraft.particle.ParticleEffect
import net.minecraft.registry.Holder
import net.minecraft.registry.Registry
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.World

fun <T> Registry<T>.register(id: Identifier, entry: T): T = Registry.register(this, id, entry)
fun <T> Registry<T>.registerHolder(id: Identifier, entry: T): Holder<T> = Registry.registerHolder(this, id, entry)

fun World.addParticle(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
    this.addParticle(particle, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z)

fun ServerWorld.spawnParticles(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
    this.spawnParticles(particle, pos.x, pos.y, pos.z, 0, velocity.x, velocity.y, velocity.z, 1.0)

fun ServerWorld.playSound(pos: Vec3d, sound: SoundEvent, category: SoundCategory, volume: Float, pitch: Float) =
    this.playSound(null, pos.x, pos.y, pos.z, sound, category, volume, pitch)

fun RandomGenerator.posOrNeg(): Int = if (this.nextBoolean()) 1 else -1
