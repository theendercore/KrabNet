package org.teamvoided.krabnet.utils

import net.minecraft.particle.ParticleEffect
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

fun World.addParticle(
    particle: ParticleEffect, pos: Vec3d, velocity: Vec3d, spawnAlways: Boolean = particle.type.shouldAlwaysSpawn()
) = this.addParticle(particle, spawnAlways, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z)