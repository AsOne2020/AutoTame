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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 12111
import net.minecraft.world.entity.animal.equine.AbstractHorse;
//#else
//$$ import net.minecraft.world.entity.animal.horse.AbstractHorse;
//#endif

@Mixin(AbstractHorse.class)
public class MixinAbstractHorse {
	@Inject(method = "handleEntityEvent", at = @At("HEAD"))
	public void handleEntityEvent(byte status, CallbackInfo ci) {
		if (status != 6) return;
		HorseEventTrigger.onEvent((AbstractHorse) (Object) this, HorseEventTrigger.Source.UPDATE_STATUS);
	}
}
