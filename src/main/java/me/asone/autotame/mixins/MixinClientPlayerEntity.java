/*
 * This file is part of the AutoTame project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025  As_One and contributors
 *
 * AutoTame is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AutoTame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AutoTame.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.asone.autotame.mixins;

import me.asone.autotame.HorseEventTrigger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {


	@Inject(
			//#if MC <= 11502
			method = "stopRiding",
			//#elseif MC <= 11605
			//$$ method = "method_29239",
			//#else
			//$$ method = "dismountVehicle",
			//#endif
			at = @At("HEAD")
	)
	public void stopRidingHead(CallbackInfo ci) {
		if (MinecraftClient.getInstance().player.getVehicle() instanceof HorseBaseEntity) {
			PlayerEntity player = MinecraftClient.getInstance().player;
			HorseBaseEntity hbe = (HorseBaseEntity) player.getVehicle();
			if (hbe.isTame() || !player.getMainHandStack().isEmpty()) return;
			HorseEventTrigger.onEvent(hbe, HorseEventTrigger.Source.STOP_RIDING);
		}
	}

	@Inject(method = "tick", at = @At("RETURN"))
	public void tick(CallbackInfo ci) {
		HorseEventTrigger.tick();
	}
}
