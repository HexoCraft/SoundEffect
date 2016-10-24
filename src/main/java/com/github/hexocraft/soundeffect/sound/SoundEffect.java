package com.github.hexocraft.soundeffect.sound;

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

import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import com.github.hexocraftapi.configuration.collection.ConfigurationList;
import com.github.hexocraftapi.configuration.collection.ConfigurationObject;
import com.github.hexocraftapi.sounds.PlaySounds;
import com.github.hexocraft.soundeffect.SoundEffectApi;
import com.github.hexocraft.soundeffect.SoundEffectPlugin;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SoundEffect extends ConfigurationObject implements Cloneable
{
	@ConfigValue(path = "*.name")				private String name;
	@ConfigValue(path = "*.description")		private String description;
	@ConfigValue(path = "*.sound")				private Sound sound;
	@ConfigValue(path = "*.volume")				private String volume;
	@ConfigValue(path = "*.pitch")				private String pitch;
	@ConfigValue(path = "*.delay")				private Long delay;
	@ConfigValue(path = "*.list")				private ConfigurationList<SoundEffectDelay> list = null;


	protected SoundEffect(JavaPlugin plugin) { super(plugin); }

	public SoundEffect(JavaPlugin plugin, String soundEffectName, Sound sound, float volume, float pitch)
	{
		this(plugin, soundEffectName, "", sound, volume, pitch, 0);
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName, Sound sound, float volume, float pitch, long delay)
	{
		this(plugin, soundEffectName, "", sound, volume, pitch, delay);
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName, String description, Sound sound, float volume, float pitch, long delay)
	{
		this(plugin, soundEffectName, "", sound, Float.toString(volume), Float.toString(pitch), delay);
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName, Sound sound, String volume, String pitch)
	{
		this(plugin, soundEffectName, "", sound, volume, pitch, 0);
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName, Sound sound, String volume, String pitch, long delay)
	{
		this(plugin, soundEffectName, "", sound, volume, pitch, delay);
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName, String description, Sound sound, String volume, String pitch, long delay)
	{
		super(plugin);

		this.name = soundEffectName;
		this.description = description;
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.delay = delay > 0 ? delay : null;
	}

	public SoundEffect(JavaPlugin plugin, String soundEffectName)
	{
		super(plugin);

		this.name = soundEffectName;
	}

	public boolean addSoundEffect(SoundEffect toAdd, long delay)
	{
		// Create the list if necessary
		if(this.list == null)
			this.list = new ConfigurationList<>();

		// Add the sound
		return this.list.add(new SoundEffectDelay(getPlugin(), toAdd.getName(), delay));
	}

	public static double evaluate(String formula)
	{
		// Random generator
		Random random = new Random();

		// Evaluate expression
		Expression e = new ExpressionBuilder(formula)
		.variables("RANDOM")
		.build()
		.setVariable("RANDOM", random.nextDouble());

		return e.evaluate();
	}

	public String getName()  		{ return this.name; }
	public String getDescription()  { return this.description; }
	public Sound getSound() { return sound; }
	public String getVolume() 		{ return volume; }
	public float getVolumeValue()
	{
		float volume = (float) evaluate(this.volume);
		if(volume >= 0f) return volume;
		return 0f;
	}
	public String getPitch()  		{ return pitch; }
	public float getPitchValue()
	{
		float pitch = (float) evaluate(this.pitch);
		if(pitch >= 0f) return pitch;
		return 0f;
	}
	public long getDelay() 	 		{ return delay!=null ? delay : 0; }
	public void setDelay(Long delay) { this.delay = delay; }

	@Override
	protected Object clone()
	{
		SoundEffect soundEffect = null;
		try {
			soundEffect = (SoundEffect) super.clone();
		} catch(CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}

		return soundEffect;
	}

	public void play(final Location location)
	{
		final Sound sound = getSound();

		if(sound != null)
		{
			final float volume = getVolumeValue();
			final float pitch = getPitchValue();
			final long delay = getDelay();

			if(delay == 0)
				PlaySounds.play(location, sound, volume, pitch);
			else
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(SoundEffectPlugin.instance, new Runnable()
				{
					@Override
					public void run()
					{
						PlaySounds.play(location, sound, volume, pitch);
					}
				}, delay);
			}
		}

		if(list != null && list.size() > 0)
		{
			for(SoundEffectDelay soundEffectDelay : list)
			{
				final SoundEffect soundEffect = SoundEffectApi.getSoundEffect(soundEffectDelay.getEffect());
				if(soundEffect != null)
				{
					SoundEffect clone = (SoundEffect) soundEffect.clone();
					clone.setDelay(soundEffectDelay.getDelay());
					clone.play(location);
				}
			}
		}
	}

	public void play(Player player)
	{
		play(player.getLocation());
	}
}
