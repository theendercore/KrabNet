package org.teamvoided.krabnet.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

@Debug(export = true)
@Mixin(GameRenderer.class)
public class GameRendererMix {
}
