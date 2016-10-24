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
import com.github.hexocraftapi.configuration.annotation.ConfigPath;
import com.github.hexocraftapi.configuration.annotation.ConfigValue;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */

@ConfigHeader(comment = {
"# ===--- SoundEffect ---------------------------------------------------------------------------------------------=== #",
"#                                                                                                                      ",
"# SoundEffect is a plugin and an API which make easy to create any sound effect                                        ",
"# by compiling all sort of existing sound already present in Minecraft.                                                ",
"#                                                                                                                      ",
"#                                                                                                                      ",
"# For more details on how to play sounds or sound effect, look at sounds.yml                                           ",
"#                                                                                                                      ",
"# ===------------------------------------------------------------------------------------------ © 2016 Hexosse ---=== #"
})
@ConfigFooter(comment = {
" ",
"# ===--- Enjoy -------------------------------------------------------------------------------- © 2016 Hexosse ---=== #"
})
public class Config extends Configuration
{
	/* Plugin options */
	@ConfigPath(path = "plugin", comment = "Plugin options")
	@ConfigValue(path = "plugin.useMetrics", comment ="Enable Metrics")		public boolean useMetrics = (boolean) true;
	@ConfigValue(path = "plugin.useUpdater", comment = "Enable Updater")	public boolean useUpdater = (boolean) true;

	/* Messages */
	@ConfigValue(path = "Messages", comment = "The file where all messages are stored")	public String messages = "messages.yml";


	public Config(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		if(load) load();
	}
}
