package org.teamvoided.krabnet.init

import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.MutableText
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.text.ImageComponent

object KNCommands {
    fun init() = CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, env ->
        val yest = literal("yest").executes { yest(it); 0 }.build()
        dispatcher.root.addChild(yest)
    }

    private fun yest(it: CommandContext<ServerCommandSource>) {
        val src = it.source ?: return


        src.sendSystemMessage(MutableText.create(ImageComponent(id("yes"))))
    }
}