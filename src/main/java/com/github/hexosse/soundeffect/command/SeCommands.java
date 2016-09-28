package com.github.hexosse.soundeffect.command;

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

import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.command.predifined.CommandReload;
import com.github.hexocraftapi.command.type.ArgTypeSound;
import com.github.hexocraftapi.command.type.ArgTypeSoundPitch;
import com.github.hexocraftapi.command.type.ArgTypeSoundVolume;
import com.github.hexocraftapi.message.predifined.message.PluginMessage;
import com.github.hexocraftapi.sounds.PlaySounds;
import com.github.hexocraftapi.sounds.Sounds;
import com.github.hexosse.soundeffect.SoundEffectPlugin;
import com.github.hexosse.soundeffect.configuration.Messages;
import com.github.hexosse.soundeffect.configuration.Permissions;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static com.github.hexosse.soundeffect.SoundEffectPlugin.config;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SeCommands extends Command<SoundEffectPlugin>
{
	public SeCommands(SoundEffectPlugin plugin)
	{
		super("SoundEffect", plugin);
		this.setAliases(Lists.newArrayList("se"));

		this.addSubCommand(new SeCommandPlaySound(plugin));

		this.addSubCommand(new SeCommandReload(plugin));
		this.addSubCommand(new SeCommandHelp(plugin));
	}

	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		plugin.getServer().dispatchCommand(commandInfo.getSender(), "SoundEffect help");

		return true;
	}




	public class SeCommandPlaySound extends Command<SoundEffectPlugin>
	{
		public SeCommandPlaySound(SoundEffectPlugin plugin)
		{
			super("PlaySound", plugin);
			this.setAliases(Lists.newArrayList("playsound", "ps"));
			this.setPermission(Permissions.PLAY_SOUND.toString());
			this.setDescription(plugin.messages.cPlaySound);

			this.addArgument(new CommandArgument<Sound>("sound", ArgTypeSound.get(), true,true, plugin.messages.cPlaySoundSound));
			this.addArgument(new CommandArgument<Float>("volume", ArgTypeSoundVolume.get(), true,true, plugin.messages.cPlaySoundVolume));
			this.addArgument(new CommandArgument<Float>("pitch", ArgTypeSoundPitch.get(), true,true, plugin.messages.cPlaySoundPitch));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			Sound sound = Sounds.get(commandInfo.getNamedArg("sound"));
			float volume = Float.parseFloat(commandInfo.getNamedArg("volume"));
			float pitch = Float.parseFloat(commandInfo.getNamedArg("pitch"));

			PlaySounds.play(commandInfo.getPlayer(), sound, volume, pitch);

			return true;
		}
	}




	public class SeCommandReload extends CommandReload<SoundEffectPlugin>
	{
		public SeCommandReload(SoundEffectPlugin plugin)
		{
			super(plugin, Permissions.ADMIN.toString());
			this.setDescription(plugin.messages.cReload);
		}

		@Override
		public boolean onCommand(final CommandInfo commandInfo)
		{
			final Player player = commandInfo.getPlayer();

			super.onCommand(commandInfo);

			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					// Reload config file
					plugin.config.load();
					// Reload message file
					plugin.messages = new Messages(plugin, config.messages, true);

					// Send message
					PluginMessage.toSenders(commandInfo.getSenders(),plugin, plugin.messages.sReload, ChatColor.YELLOW);
				}

			}.runTask(plugin);

			return true;
		}
	}




	public class SeCommandHelp extends CommandHelp<SoundEffectPlugin>
	{
		public SeCommandHelp(SoundEffectPlugin plugin)
		{
			super(plugin);
			this.setDescription(plugin.messages.cHelp);
		}
	}

}
