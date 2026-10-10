package org.teamvoided.krabnet.mixin;

import net.minecraft.text.TextCodecs;
import net.minecraft.util.StringIdentifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.teamvoided.krabnet.text.ImageComponent;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(TextCodecs.class)
public class TextCodecsMixin {

    @ModifyArg(method = "createCodec", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TextCodecs;dispatchingCodec([Lnet/minecraft/util/StringIdentifiable;Ljava/util/function/Function;Ljava/util/function/Function;Ljava/lang/String;)Lcom/mojang/serialization/MapCodec;"))
    private static <T extends StringIdentifiable> T[] x(T[] values) {
        var types = new ArrayList<>(Arrays.asList(values));
        //noinspection unchecked
        types.add((T) ImageComponent.TYPE);
        return types.toArray(values);
    }

}