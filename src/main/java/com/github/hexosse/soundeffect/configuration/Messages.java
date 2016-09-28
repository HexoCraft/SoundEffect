package com.github.hexosse.soundeffect.configuration;

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
import com.github.hexocraftapi.configuration.annotation.ConfigPath;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Messages extends Configuration
{
	/* Chat */
	@ConfigPath(path = "chat")
	@ConfigValue(path = "chat.prefix")					public String chatPrefix = "§3[§bSoundEffect§3]§r";

	/* Commands */

	/* Success */

	/* Errors */



	public Messages(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		if(load) load();
	}
}
