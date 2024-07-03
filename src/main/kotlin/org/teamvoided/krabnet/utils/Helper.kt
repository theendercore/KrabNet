package org.teamvoided.krabnet.utils

import net.minecraft.particle.ParticleEffect
import net.minecraft.registry.Holder
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

fun <T> Registry<T>.register(id: Identifier, entry: T): T = Registry.register(this, id, entry)
fun <T> Registry<T>.registerHolder(id: Identifier, entry: T): Holder<T> = Registry.registerHolder(this, id, entry)

fun World.addParticle(particle: ParticleEffect, pos: Vec3d, velocity: Vec3d) =
    this.addParticle(particle, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z)
