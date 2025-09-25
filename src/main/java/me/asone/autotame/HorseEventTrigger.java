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

package me.asone.autotame;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.util.Hand;

public class HorseEventTrigger {
	private static long statusTime = -1, ridingTime = -1;
	private static HorseBaseEntity statusHorse, ridingHorse;
	private static final long TIME_WINDOW = 100L;

	private static final int DELAY_TICKS = 2;
	private static HorseBaseEntity delayedHorse = null;
	private static int remainingTicks = 0;

	public enum Source {UPDATE_STATUS, STOP_RIDING}

	public static void onEvent(HorseBaseEntity horse, Source src) {
		long now = System.currentTimeMillis();
		if (src == Source.UPDATE_STATUS) {
			statusTime = now;
			statusHorse = horse;
		} else if (src == Source.STOP_RIDING) {
			ridingTime = now;
			ridingHorse = horse;
		}

		if (Math.abs(statusTime - ridingTime) > TIME_WINDOW || statusHorse == null || statusHorse != ridingHorse)
			return;

		delayedHorse = horse;
		remainingTicks = DELAY_TICKS;
		clear();
	}

	public static void tick() {
		if (delayedHorse == null) return;
		remainingTicks--;
		if (remainingTicks <= 0) {
			trigger(delayedHorse);
			delayedHorse = null;
			remainingTicks = 0;
		}
	}

	private static void clear() {
		statusTime = ridingTime = -1;
		statusHorse = ridingHorse = null;
	}

	private static void trigger(HorseBaseEntity horse) {
		MinecraftClient client = MinecraftClient.getInstance();

		if (!client.player.getMainHandStack().isEmpty() || !horse.isAlive()) return;

		client.interactionManager.interactEntity(client.player, horse, Hand.MAIN_HAND);
	}
}
