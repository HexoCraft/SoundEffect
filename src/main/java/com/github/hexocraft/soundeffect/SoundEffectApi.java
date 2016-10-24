package com.github.hexocraft.soundeffect;

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

import com.github.hexocraft.soundeffect.configuration.Sounds;
import com.github.hexocraft.soundeffect.sound.SoundEffect;
import com.github.hexocraftapi.util.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static com.github.hexocraft.soundeffect.SoundEffectPlugin.sounds;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
@SuppressWarnings("unused")
public class SoundEffectApi
{
	private static final SoundEffectPlugin plugin = SoundEffectPlugin.instance;

	private SoundEffectApi() {}

	public static boolean createSoundEffect(String soundEffectName, Sound sound, float volume, float pitch, long delay)
	{
		if(sounds.get(soundEffectName) != null)
			return false;
		if(!sounds.add(new SoundEffect(plugin, soundEffectName, sound, volume, pitch, delay)))
			return false;
		return sounds.save();
	}

	public static boolean createSoundEffect(String soundEffectName, Sound sound, String volume, String pitch, long delay)
	{
		if(sounds.get(soundEffectName) != null)
			return false;
		if(!sounds.add(new SoundEffect(plugin, soundEffectName, sound, volume, pitch, delay)))
			return false;
		return sounds.save();
	}

	public static boolean createSoundEffect(String soundEffectName)
	{
		if(sounds.get(soundEffectName) != null)
			return false;
		if(!sounds.add(new SoundEffect(plugin, soundEffectName)))
			return false;
		return sounds.save();
	}

	public static boolean addSoundEffect(String to, String add, long delay)
	{
		SoundEffect sTo = sounds.get(to);
		if(sTo == null) return false;

		SoundEffect sAdd = sounds.get(add);
		if(sAdd == null) return false;

		if(!sTo.addSoundEffect(sAdd, delay))
			return false;
		return sounds.save();
	}

	public static boolean deleteSoundEffect(String soundEffectName)
	{
		SoundEffect soundEffect = sounds.get(soundEffectName);
		if(soundEffect == null) return false;

		if(!sounds.remove(soundEffect))
			return false;
		return sounds.save();
	}

	public static SoundEffect getSoundEffect(String soundEffectName)
	{
		if(soundEffectName == null) return null;

		return sounds.get(soundEffectName);
	}

	public static boolean playSoundEffect(String soundEffectName, Player player, double x, double y, double z)
	{
		if(soundEffectName == null) return false;
		if(player == null) return false;

		Location location = player.getLocation();
		location.setX(location.getX() + x);
		location.setY(location.getY() + y);
		location.setZ(location.getZ() + z);

		return playSoundEffect(soundEffectName, location);
	}

	public static boolean playSoundEffect(String soundEffectName, Player player, String x, String y, String z)
	{
		return playSoundEffect(soundEffectName, player, SoundEffect.evaluate(x), SoundEffect.evaluate(y), SoundEffect.evaluate(z));
	}

	public static boolean playSoundEffect(String soundEffectName, Player player)
	{
		if(soundEffectName == null) return false;
		if(player == null) return false;

		return playSoundEffect(soundEffectName, player.getLocation());
	}

	public static boolean playSoundEffect(String soundEffectName, Location location)
	{
		SoundEffect soundEffect = sounds.get(soundEffectName);

		if(soundEffect == null)
			return false;

		soundEffect.play(location);

		return true;

	}

	public static boolean playSoundEffect(String soundEffectName, World world, double x, double y, double z)
	{
		return playSoundEffect(soundEffectName, new Location(world, x, y, z));
	}

	public static boolean playSoundEffect(String soundEffectName, World world, String x, String y, String z)
	{
		return playSoundEffect(soundEffectName, world, SoundEffect.evaluate(x), SoundEffect.evaluate(y), SoundEffect.evaluate(z));
	}

	public static void broadcastSoundEffect(String soundEffectName, World world, double x, double y, double z)
	{
		if(soundEffectName == null)  return;

		if(world==null)
		{
			broadcastSoundEffect(soundEffectName, x, y, z);
		}

		else
			{
			for(Player player : PlayerUtil.getOnlinePlayers())
				if(player.getWorld().equals(world))
					playSoundEffect(soundEffectName, player, x, y, z);
		}

	}

	public static void broadcastSoundEffect(String soundEffectName, World world, String x, String y, String z)
	{
		broadcastSoundEffect(soundEffectName, world, SoundEffect.evaluate(x), SoundEffect.evaluate(y), SoundEffect.evaluate(z));
	}

	public static void broadcastSoundEffect(String soundEffectName, World world)
	{
		broadcastSoundEffect(soundEffectName, world, 0, 0, 0);
	}

	public static void broadcastSoundEffect(String soundEffectName, double x, double y, double z)
	{
		if(soundEffectName == null)  return;

		for(Player player : PlayerUtil.getOnlinePlayers())
			playSoundEffect(soundEffectName, player, x, y, z);
	}

	public static void broadcastSoundEffect(String soundEffectName, String x, String y, String z)
	{
		broadcastSoundEffect(soundEffectName, SoundEffect.evaluate(x), SoundEffect.evaluate(y), SoundEffect.evaluate(z));
	}

	public static void broadcastSoundEffect(String soundEffectName)
	{
		broadcastSoundEffect(soundEffectName, 0, 0, 0);
	}

	public static Sounds listSoundEffect()
	{
		return sounds;
	}
}
