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

//#if FABRIC
import net.fabricmc.api.ModInitializer;
//#elseif FORGE
//$$ import net.minecraftforge.fml.common.Mod;
//#elseif NEOFORGE
//$$ import net.neoforged.fml.common.Mod;
//#endif


//#if FORGE_LIKE
//$$ @Mod(AutoTame.MOD_ID)
//#endif
public class AutoTame
		//#if FABRIC
		implements ModInitializer
		//#endif
{
	public static final String MOD_ID = "autotame";

	//#if FABRIC
	@Override
	public void onInitialize() {}
	//#elseif FORGE_LIKE
	//$$ public AutoTame() {}
	//#endif
}
