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
"# ===------------------------------------------------------------------------------------------ © 2016 Hexosse ---=== #"
})
@ConfigFooter(comment = {
" ",
"# ===--- Enjoy -------------------------------------------------------------------------------- © 2016 Hexosse ---=== #"
})
public class Messages extends Configuration
{
	/* Chat */
	@ConfigPath(path = "chat")
	@ConfigValue(path = "chat.prefix")							public String chatPrefix = "§3[§bSoundEffect§3]§r";

	/* Commands */
	@ConfigPath(path = "commands", 		comment = "List of messages used in commands")
	@ConfigValue(path = "commands.help")						public String cHelp   = "Display help";
	@ConfigValue(path = "commands.reload")						public String cReload = "Reload SoundEffect";

	@ConfigValue(path = "commands.playSound.cmd")				public String cPlaySound       = "Play a sound";
	@ConfigValue(path = "commands.playSound.sound")				public String cPlaySoundSound  = "Bukkit Sound";
	@ConfigValue(path = "commands.playSound.volume")			public String cPlaySoundVolume = "Volume";
	@ConfigValue(path = "commands.playSound.pitch") 			public String cPlaySoundPitch  = "Pitch";

	@ConfigValue(path = "commands.createSimpleEffect.cmd")		public String cCreateSimpleEffect       = "Create a simple effect";
	@ConfigValue(path = "commands.createSimpleEffect.name")		public String cCreateSimpleEffectName   = "Sound effect name";
	@ConfigValue(path = "commands.createSimpleEffect.sound")	public String cCreateSimpleEffectSound  = "Bukkit Sound";
	@ConfigValue(path = "commands.createSimpleEffect.volume")	public String cCreateSimpleEffectVolume = "Volume";
	@ConfigValue(path = "commands.createSimpleEffect.pitch")	public String cCreateSimpleEffectPitch  = "Pitch";
	@ConfigValue(path = "commands.createSimpleEffect.delay")	public String cCreateSimpleEffectDelay  = "Delay before playing the sound (in tick)";

	@ConfigValue(path = "commands.createComplexEffect.cmd")		public String cCreateComplexEffect      = "Create a complex effect";
	@ConfigValue(path = "commands.createComplexEffect.name")	public String cCreateComplexEffectName  = "Sound effect name";

	@ConfigValue(path = "commands.addSoundEffect.cmd")			public String cAddSoundEffect      = "Add a sound effect";
	@ConfigValue(path = "commands.addSoundEffect.soundEffect")	public String cAddSoundEffectName  = "Sound effect to modify";
	@ConfigValue(path = "commands.addSoundEffect.ToAdd")		public String cAddSoundEffectToAdd = "Sound effect to add";
	@ConfigValue(path = "commands.addSoundEffect.delay")		public String cAddSoundEffectDelay = "Delay before playing the sound (in tick)";

	@ConfigValue(path = "commands.deleteSoundEffect.cmd")			public String cDeleteSoundEffect   = "Delete a sound effect";
	@ConfigValue(path = "commands.deleteSoundEffect.soundEffect")	public String cDeleteSoundEffectName  = "Sound effect to delete";

	@ConfigValue(path = "commands.playSoundEffect.cmd")			public String cPlaySoundEffect 		 = "Play a sound effect";
	@ConfigValue(path = "commands.playSoundEffect.name")		public String cPlaySoundEffectName 	 = "Sound effect name";
	@ConfigValue(path = "commands.playSoundEffect.player")		public String cPlaySoundEffectPlayer = "The player";
	@ConfigValue(path = "commands.playSoundEffect.x")			public String cPlaySoundEffectX 	 = "X coordinate";
	@ConfigValue(path = "commands.playSoundEffect.y")			public String cPlaySoundEffectY 	 = "Y coordinate";
	@ConfigValue(path = "commands.playSoundEffect.z")			public String cPlaySoundEffectZ 	 = "Z coordinate";

	@ConfigValue(path = "commands.spawnSoundEffect.cmd")		public String cSpawnSoundEffect 	 = "Spawn a sound effect at location";
	@ConfigValue(path = "commands.spawnSoundEffect.name")		public String cSpawnSoundEffectName  = "Sound effect name";
	@ConfigValue(path = "commands.spawnSoundEffect.world")		public String cSpawnSoundEffectWorld = "The world";
	@ConfigValue(path = "commands.spawnSoundEffect.x")			public String cSpawnSoundEffectX 	 = "X coordinate";
	@ConfigValue(path = "commands.spawnSoundEffect.y")			public String cSpawnSoundEffectY 	 = "Y coordinate";
	@ConfigValue(path = "commands.spawnSoundEffect.z")			public String cSpawnSoundEffectZ 	 = "Z coordinate";

	@ConfigValue(path = "commands.playSoundEffect.cmd")			public String cBroadcastSoundEffect     = "Broadcast a sound effect";
	@ConfigValue(path = "commands.playSoundEffect.name")		public String cBroadcastSoundEffectName = "Sound effect name";
	@ConfigValue(path = "commands.playSoundEffect.x")			public String cBroadcastSoundEffectX 	= "X coordinate";
	@ConfigValue(path = "commands.playSoundEffect.y")			public String cBroadcastSoundEffectY 	= "Y coordinate";
	@ConfigValue(path = "commands.playSoundEffect.z")			public String cBroadcastSoundEffectZ 	= "Z coordinate";

	@ConfigValue(path = "commands.listSoundEffect.cmd")			public String cListSoundEffect = "List of sound effect";

	/* Messages */
	@ConfigPath(path = "messages", 		comment = "List of messages")
	@ConfigValue(path = "messages.list.list")					public String mList      = "List of sound effect";
	@ConfigValue(path = "messages.list.click")					public String mListClick = "Click here to play the sound effect";

	/* Success */
	@ConfigPath(path = "success", 		comment = "List of success messages")
	@ConfigValue(path = "success.createSoundEffect")			public String sCreateSoundEffect = "Sound Effect created";
	@ConfigValue(path = "success.addSoundEffect")				public String sAddSoundEffect = "Sound Effect added";
	@ConfigValue(path = "success.deleteSoundEffect")			public String sDeleteSoundEffect = "Sound Effect deleted";
	@ConfigValue(path = "success.reload")						public String sReload = "SoundEffect has been reloaded";

	/* Errors */
	@ConfigPath(path = "errors", 		comment = "List of error messages")
	@ConfigValue(path = "errors.createSoundEffect")			public String eCreateSoundEffect = "Error while creating a SoundEffect";
	@ConfigValue(path = "errors.addSoundEffect")			public String eAddSoundEffect = "Error while adding a SoundEffect";
	@ConfigValue(path = "errors.deleteSoundEffect")			public String eDeleteSoundEffect = "Error while deleting a SoundEffect";
	@ConfigValue(path = "errors.playSoundEffect")			public String ePlaySoundEffect = "Error while playing a SoundEffect";
	@ConfigValue(path = "errors.list.empty")				public String eListEmpty = "There is no sound effect";


	public Messages(JavaPlugin plugin, String fileName, boolean load)
	{
		super(plugin, fileName);

		if(load) load();
	}
}
