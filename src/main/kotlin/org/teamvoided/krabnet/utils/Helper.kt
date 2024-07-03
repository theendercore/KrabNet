package org.teamvoided.krabnet.utils

import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

fun <T> Registry<T>.register(id: Identifier, entry: T): T = Registry.register(this, id, entry)
