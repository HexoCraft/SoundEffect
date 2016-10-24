package com.github.hexocraft.soundeffect.configuration;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.configuration.Configuration;
import com.github.hexocraftapi.configuration.annotation.ConfigFooter;
import com.github.hexocraftapi.configuration.annotation.ConfigHeader;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import com.github.hexocraftapi.configuration.collection.ConfigurationList;
import com.github.hexocraft.soundeffect.sound.SoundEffect;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */

@ConfigHeader(comment = {
"# ===--- SoundEffect ---------------------------------------------------------------------------------------------=== #",
"#                                                                                                                      ",
"# SoundEffect is a plugin and an API which make easy to create any sound effect                                        ",
"# by compiling all sort of existing sound already present in Minecraft.                                                ",
"#                                                                                                                      ",
"# How to play a sound :                                                                                                ",
"# For testing purpose and before creating a sound effect, you can test your sound by using the PlaySound command.      ",
"#  /SoundEffect PlaySound <sound> <volume> <pitch>                                                                     ",
"#  /se ps ENTITY_LIGHTNING_THUNDER 1 0.8                                                                               ",
"#  /se ps ENTITY_LIGHTNING_THUNDER \"RANDOM * (10 - 1)+ 1\" 1                                                          ",
"#                                                                                                                      ",
"# How to create a simple sound effect :                                                                                ",
"#  /SoundEffect CreateSimple <name> <sound> <volume> <pitch> [delay]                                                   ",
"#  /se cs THUNDER ENTITY_LIGHTNING_THUNDER \"RANDOM * (10 - 1) + 1\" 1                                                 ",
"#                                                                                                                      ",
"# How to create a complex sound effect :                                                                               ",
"#   First, give a name to the effect :                                                                                 ",
"#    /SoundEffect CreateComplex <name> 								                                                ",
"#    /se cc MINI_THUNDER           								                                                    ",
"#   then, add an existing simple or complex sound effect :                                                             ",
"#    /SoundEffect Add <soundEffect> <soundEffectToAdd> [delay]                                                         ",
"#    /se a MINI_THUNDER THUNDER 0	    		                                                                        ",
"#    /se a MINI_THUNDER THUNDER 25			                                                                            ",
"#    /se a MINI_THUNDER THUNDER 50			                                                                            ",
"#    /se a MINI_THUNDER THUNDER 70			                                                                            ",
"#                                                                                                                      ",
"#   You can also add complex effect into complex effect :                                                              ",
"#    /se cc MEGA_THUNDER           								                                                    ",
"#    /se a MEGA_THUNDER MINI_THUNDER 0		                                                                            ",
"#    /se a MEGA_THUNDER MINI_THUNDER 40		                                                                        ",
"#                                                                                                                      ",
"# There's different way of playing a sound effect :                                                                    ",
"#  - based on player location :                                                                                        ",
"#    /SoundEffect Play <soundEffect> <player>                                                                          ",
"#    /se p THUNDER notch                                                                                               ",
"#                                                                                                                      ",
"#  - based on player location and applying changes on coordinates :                                                    ",
"#    /SoundEffect Play <soundEffect> <player> [x] [y] [z]                                                              ",
"#    /se p THUNDER notch 2 0 4                                                                                         ",
"#    /se p THUNDER notch \"RANDOM * 2\" 0 \"RANDOM * 4\"                                                               ",
"#    /se p THUNDER notch \"-5+(RANDOM*((5--5)+1))\" 0 \"-3+(RANDOM*((3--3)+1))\"                                       ",
"#                                                                                                                      ",
"#  - based on a location :                                                                                             ",
"#    /SoundEffect Spawn <soundEffect> <world> <x> <y> <z>                                                              ",
"#    /se s THUNDER world 120 65 120                                                                                    ",
"#                                                                                                                      ",
"# Here is a list of placeholder that can be used                                                                       ",
"#  - RANDOM : generate a random value between 0 and 1 exclusive [0,1)                                                  ",
"#             To generate a random value in range [Min,Max] use this formula                                           ",
"#               \"Min + (RANDOM * ((Max - Min) + 1))\"                                                                 ",
"#                                                                                                                      ",
"# ===------------------------------------------------------------------------------------------ © 2016 Hexosse ---=== #"
})
@ConfigFooter(comment = {
" ",
"# ===--- Enjoy -------------------------------------------------------------------------------- © 2016 Hexosse ---=== #"
})
public class Sounds extends Configuration
{
	@ConfigValue(path = "sounds", comment ="This your list of cool sound effect !")
	private ConfigurationList<SoundEffect> sounds = new ConfigurationList();


	public Sounds(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		if(load) load();
	}

	/**
	 * Returns the list of sound effect
	 *
	 * @return the list of sound effect
	 */
	public ConfigurationList<SoundEffect> getList()
	{
		return sounds;
	}

	/**
	 * Returns the number of sound effect in this list
	 *
	 * @return the number of sound effect in this list
	 */
	public int size()
	{
		return sounds.size();
	}

	/**
	 * Returns <tt>true</tt> if this list contains no sound effect.
	 *
	 * @return <tt>true</tt> if this list contains no sound effect
	 */
	public boolean isEmpty()
	{
		return sounds.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified sound effect.
	 *
	 * @param soundEffect sound effect whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified sound effect
	 */
	public boolean contains(SoundEffect soundEffect)
	{
		return this.sounds.contains(soundEffect);
	}

	/**
	 * Appends the specified sound effect to the end of this list
	 *
	 * @param soundEffect sound effect to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	public boolean add(SoundEffect soundEffect)
	{
		return sounds.add(soundEffect);
	}

	/**
	 * Removes the first occurrence of the specified sound effect from this list
	 *
	 * @param soundEffectName sound effect to be remove from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	public boolean remove(String soundEffectName)
	{
		SoundEffect soundEffect = get(soundEffectName);
		if(soundEffect == null) return false;

		return this.sounds.remove(soundEffectName);
	}

	/**
	 * Removes the first occurrence of the specified sound effect from this list
	 *
	 * @param soundEffect sound effect to be remove from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	public boolean remove(SoundEffect soundEffect)
	{
		return sounds.remove(soundEffect);
	}

	/**
	 * Removes all of the sound effect from this list.
	 * The list will be empty after this call returns.
	 */
	public void clear()
	{
		this.sounds.clear();
	}

	/**
	 * Returns the sound effect at the specified position in this list.
	 *
	 * @param soundEffectName Name of the element to return
	 * @return the element at the specified position in this list
	 */
	public SoundEffect get(String soundEffectName)
	{
		for(SoundEffect se : this.sounds)
			if(se.getName().equals(soundEffectName))
				return se;
		return null;
	}
}
