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
import com.github.hexocraftapi.configuration.collection.ConfigurationObject;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SoundEffectDelay extends ConfigurationObject implements Cloneable
{
	@ConfigValue(path = "*.effect")				private String effect;
	@ConfigValue(path = "*.delay")				private Long   delay;


	protected SoundEffectDelay(JavaPlugin plugin) { super(plugin); }

	public SoundEffectDelay(JavaPlugin plugin, String soundEffectName, long delay)
	{
		super(plugin);

		this.effect = soundEffectName;
		this.delay = delay > 0 ? delay : null;
	}

	public SoundEffectDelay(JavaPlugin plugin, SoundEffect soundEffect)
	{
		super(plugin);

		this.effect = soundEffect.getName();
		this.delay = soundEffect.getDelay() > 0 ? soundEffect.getDelay() : null;
	}

	public String getEffect() { return effect; }
	public Long getDelay() { return delay; }
}
