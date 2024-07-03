package org.teamvoided.krabnet.utils

import org.joml.Math.toRadians

val Number.rad: Float get() = toRadians(this.toFloat())
