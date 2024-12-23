package org.teamvoided.krabnet

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.ShaderProgram
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer
import org.teamvoided.krabnet.KrabNet.id
import org.teamvoided.krabnet.utils.Vec3d
import org.teamvoided.krabnet.utils.xyz

object Rendering {
    private var customType: ShaderProgram? = null
    fun init() {
        CoreShaderRegistrationCallback.EVENT.register { ctx ->
            ctx.register(id("rendertype_custom"), VertexFormats.POSITION_COLOR_LIGHT) { customType = it }
        }
        WorldRenderEvents.AFTER_ENTITIES.register(::renderCustom)
    }

    fun renderCustom(ctx: WorldRenderContext) = ctx.matrixStack()?.apply {
        var profiler = ctx.profiler()
        profiler.push("krabNet")

        val color = 0x90ff9090.toInt()
        val tessellator = Tessellator.getInstance()
        val pose = this.peek().model

        this.push()
        RenderSystem.disableDepthTest()
        RenderSystem.enableBlend()
        RenderSystem.setShader(GameRenderer::getPositionColorShader)
        RenderSystem.setShaderTexture(0, EndPortalBlockEntityRenderer.PORTAL_TEXTURE)
        RenderSystem.disableCull()
        val builder: BufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR)
        val camPos = ctx.camera().pos
        builder.xyz(pose, Vec3d(0), camPos).color(color)
        builder.xyz(pose, Vec3d(0, 0, 1), camPos).color(color)
        builder.xyz(pose, Vec3d(1, 0, 1), camPos).color(color)
        builder.xyz(pose, Vec3d(1, 0, 0), camPos).color(color)
        builder.end()?.let { BufferRenderer.drawWithShader(it) }
        this.pop()

        profiler.pop()
        Thread.yield()
    }
}