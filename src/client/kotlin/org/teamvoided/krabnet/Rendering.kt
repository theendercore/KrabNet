package org.teamvoided.krabnet

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.ShaderProgram
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import org.joml.Matrix4f
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.utils.*

object Rendering {
    private var customType: ShaderProgram? = null
    private var statueShader: ShaderProgram? = null
    fun init() {
        CoreShaderRegistrationCallback.EVENT.register { ctx ->
            ctx.register(id("rendertype_custom"), VertexFormats.POSITION_COLOR) { customType = it }
            ctx.register(
                id("rendertype_statue"),
                VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL
            ) { statueShader = it }
        }
        WorldRenderEvents.AFTER_ENTITIES.register(::renderCustom)
    }

    fun renderCustom(ctx: WorldRenderContext) = ctx.matrixStack()?.apply {
        val profiler = ctx.profiler()
        profiler.push("krabNet")

        val color = 0xff_ffffff.toInt()
        val tessellator = Tessellator.getInstance()
        val pose = this.peek().model

        this.push()
        RenderSystem.disableDepthTest()
        RenderSystem.enableBlend()
        RenderSystem.setShader { statueShader }
        RenderSystem.disableCull()
        RenderSystem.setShaderTexture(0, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)
        RenderSystem.setShaderTexture(1, EndPortalBlockEntityRenderer.SKY_TEXTURE)
        RenderSystem.setShaderTexture(2, Identifier.ofDefault("textures/misc/white.png"))
        val id = Identifier.ofDefault("block/campfire_fire")
        val x = MinecraftClient.getInstance().getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).apply(id)



        RenderSystem.disableCull()
        val builder: BufferBuilder =
            tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL)
        val camPos = ctx.camera().pos

        val light = 16

        val minU = 0
        val minV = 0
        val maxU = 1
        val maxV = 1

        builder.vertex(pose, camPos, Vec3d(0), minU, minV, color, light)
        builder.vertex(pose, camPos, Vec3d(0, 0, 1), minU, maxV, color, light)
        builder.vertex(pose, camPos, Vec3d(1, 0, 1), maxU, maxV, color, light)
        builder.vertex(pose, camPos, Vec3d(1, 0, 0), maxU, minV, color, light)

        builder.end()?.let { BufferRenderer.drawWithShader(it) }
        this.pop()

        profiler.pop()
        Thread.yield()
    }

    fun BufferBuilder.vertex(
        pose: Matrix4f,
        camera: Vec3d,
        vec: Vec3d,
        u: Number,
        v: Number,
        color: Int,
        light: Int,
    ): VertexConsumer {
        return this.xyz(pose, vec, camera)
            .color(color)
            .uv0(u, v)
            .normal(UP)
            .uv1(OverlayTexture.DEFAULT_UV)
            .uv2(light)
    }
}