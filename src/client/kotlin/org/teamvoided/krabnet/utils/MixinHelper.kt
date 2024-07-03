package org.teamvoided.krabnet.utils

import net.minecraft.client.particle.Particle
import org.teamvoided.krabnet.mixin.ParticleAccessor

fun Particle.stoppedByCollision(boolean: Boolean) = ( this as ParticleAccessor).setStoppedByCollision(boolean)
