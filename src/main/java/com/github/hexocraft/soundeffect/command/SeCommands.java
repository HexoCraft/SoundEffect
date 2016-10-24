package com.github.hexocraft.soundeffect.command;

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

import com.github.hexocraftapi.chat.MessageBuilder;
import com.github.hexocraftapi.chat.event.ClickEvent;
import com.github.hexocraftapi.chat.event.HoverEvent;
import com.github.hexocraftapi.command.Command;
import com.github.hexocraftapi.command.CommandArgument;
import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.predifined.CommandHelp;
import com.github.hexocraftapi.command.predifined.CommandReload;
import com.github.hexocraftapi.command.type.ArgTypePlayer;
import com.github.hexocraftapi.command.type.ArgTypeSound;
import com.github.hexocraftapi.command.type.ArgTypeString;
import com.github.hexocraftapi.command.type.ArgTypeWorld;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.Message;
import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.Sentence;
import com.github.hexocraftapi.message.predifined.MessageColor;
import com.github.hexocraftapi.message.predifined.line.Title;
import com.github.hexocraftapi.message.predifined.message.*;
import com.github.hexocraftapi.sounds.PlaySounds;
import com.github.hexocraftapi.sounds.Sounds;
import com.github.hexocraftapi.util.PlayerUtil;
import com.github.hexocraft.soundeffect.SoundEffectApi;
import com.github.hexocraft.soundeffect.SoundEffectPlugin;
import com.github.hexocraft.soundeffect.command.ArgType.ArgTypeDoubleEvaluate;
import com.github.hexocraft.soundeffect.command.ArgType.ArgTypeSoundDelay;
import com.github.hexocraft.soundeffect.command.ArgType.ArgTypeSoundEffect;
import com.github.hexocraft.soundeffect.command.ArgType.ArgTypeSoundPitch;
import com.github.hexocraft.soundeffect.command.ArgType.ArgTypeSoundVolume;
import com.github.hexocraft.soundeffect.configuration.Messages;
import com.github.hexocraft.soundeffect.configuration.Permissions;
import com.github.hexocraft.soundeffect.sound.SoundEffect;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static com.github.hexocraft.soundeffect.SoundEffectPlugin.config;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class SeCommands extends Command<SoundEffectPlugin>
{
	private final Prefix prefix;
	private final ChatColor messageColor = ChatColor.GOLD;

	enum cmd
	{
		// SoundEffect
		SoundEffect, se,
		// PlaySound
		PlaySound, playsound, ps,
		// CreateSimple
		CreateSimple, createsimple, cs,
		// CreateComplex
		CreateComplex, createcomplex, cc,
		// Add
		Add, add, a,
		// Delete
		Delete, delete, d,
		// Play
		Play, play, p,
		// Spawn
		Spawn, spawn, s,
		// Broadcast
		Broadcast, broadcast, b,
		// List
		List, list, l,
	}

	public SeCommands(SoundEffectPlugin plugin)
	{
		super(cmd.SoundEffect.toString(), plugin);
		this.setAliases(Lists.newArrayList(cmd.se.toString()));

		this.addSubCommand(new SeCommandPlaySound(plugin));
		this.addSubCommand(new SeCommandCreateSimpleEffect(plugin));
		this.addSubCommand(new SeCommandCreateComplexEffect(plugin));
		this.addSubCommand(new SeCommandAddSoundEffect(plugin));
		this.addSubCommand(new SeCommandDeleteSoundEffect(plugin));
		this.addSubCommand(new SeCommandPlaySoundEffect(plugin));
		this.addSubCommand(new SeCommandSpawnSoundEffect(plugin));
		this.addSubCommand(new SeCommandBroadcastSoundEffect(plugin));
		this.addSubCommand(new SeCommandListSoundEffect(plugin));
		this.addSubCommand(new SeCommandReload(plugin));
		this.addSubCommand(new SeCommandHelp(plugin));

		//
		prefix = new Prefix(plugin.messages.chatPrefix);
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
			super(cmd.PlaySound.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.playsound.toString(), cmd.ps.toString()));
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
			float volume = (float) SoundEffect.evaluate(commandInfo.getNamedArg("volume"));
			float pitch = (float) SoundEffect.evaluate(commandInfo.getNamedArg("pitch"));

			PlaySounds.play(commandInfo.getPlayer(), sound, volume, pitch);

			return true;
		}
	}




	public class SeCommandCreateSimpleEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandCreateSimpleEffect(SoundEffectPlugin plugin)
		{
			super(cmd.CreateSimple.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.createsimple.toString(), cmd.cs.toString()));
			this.setPermission(Permissions.CREATE_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cCreateSimpleEffect);

			this.addArgument(new CommandArgument<String>("name", ArgTypeString.get(), true, true, plugin.messages.cCreateSimpleEffectName));
			this.addArgument(new CommandArgument<Sound>("sound", ArgTypeSound.get(), true, true, plugin.messages.cCreateSimpleEffectSound));
			this.addArgument(new CommandArgument<String>("volume", ArgTypeString.get(), true, true, plugin.messages.cCreateSimpleEffectVolume));
			this.addArgument(new CommandArgument<String>("pitch", ArgTypeString.get(), true, true, plugin.messages.cCreateSimpleEffectPitch));
			this.addArgument(new CommandArgument<Long>("delay", ArgTypeSoundDelay.get(), false, false, plugin.messages.cCreateSimpleEffectDelay));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			String name = commandInfo.getNamedArg("name").replace(" ", "_");
			Sound sound = Sounds.get(commandInfo.getNamedArg("sound"));
			String volume = commandInfo.getNamedArg("volume");
			String pitch = commandInfo.getNamedArg("pitch");
			long delay = commandInfo.getNamedArg("delay") != null ? (long) SoundEffect.evaluate(commandInfo.getNamedArg("delay")) : 0;

			if(SoundEffectApi.createSoundEffect(name, sound, volume, pitch, delay)) {
				SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.sCreateSoundEffect, messageColor);
				return true;
			}
			else {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eCreateSoundEffect);
				return false;
			}
		}
	}




	public class SeCommandCreateComplexEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandCreateComplexEffect(SoundEffectPlugin plugin)
		{
			super(cmd.CreateComplex.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.createcomplex.toString(), cmd.cc.toString()));
			this.setPermission(Permissions.CREATE_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cCreateComplexEffect);

			this.addArgument(new CommandArgument<String>("name", ArgTypeString.get(), true, true, plugin.messages.cCreateComplexEffectName));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			String name = commandInfo.getNamedArg("name").replace(" ", "_");

			if(SoundEffectApi.createSoundEffect(name)) {
				SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.sCreateSoundEffect, messageColor);
				return true;
			}
			else {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eCreateSoundEffect);
				return false;
			}
		}
	}




	public class SeCommandAddSoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandAddSoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.Add.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.add.toString(), cmd.a.toString()));
			this.setPermission(Permissions.CREATE_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cAddSoundEffect);

			this.addArgument(new CommandArgument<SoundEffect>("sound", ArgTypeSoundEffect.get(), true, true, plugin.messages.cAddSoundEffectName));
			this.addArgument(new CommandArgument<SoundEffect>("to", ArgTypeSoundEffect.get(), true, true, plugin.messages.cAddSoundEffectToAdd));
			this.addArgument(new CommandArgument<Long>("delay", ArgTypeSoundDelay.get(), false, false, plugin.messages.cAddSoundEffectDelay));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			SoundEffect soundEffect = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("sound"));
			SoundEffect soundEffectToAdd = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("to"));
			long delay = commandInfo.getNamedArg("delay") != null ? (long) SoundEffect.evaluate(commandInfo.getNamedArg("delay")) : 0;

			if(SoundEffectApi.addSoundEffect(soundEffect.getName(), soundEffectToAdd.getName(), delay)) {
				SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.sAddSoundEffect, messageColor);
				return true;
			}
			else {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eAddSoundEffect);
				return false;
			}
		}
	}




	public class SeCommandDeleteSoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandDeleteSoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.Delete.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.delete.toString(), cmd.d.toString()));
			this.setPermission(Permissions.CREATE_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cDeleteSoundEffect);

			this.addArgument(new CommandArgument<SoundEffect>("sound", ArgTypeSoundEffect.get(), true, true, plugin.messages.cAddSoundEffectName));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			SoundEffect soundEffect = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("sound"));

			if(SoundEffectApi.deleteSoundEffect(soundEffect.getName())) {
				SimplePrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.sDeleteSoundEffect, messageColor);
				return true;
			}
			else {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.eAddSoundEffect);
				return false;
			}
		}
	}




	public class SeCommandPlaySoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandPlaySoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.Play.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.play.toString(), cmd.p.toString()));
			this.setPermission(Permissions.PLAY_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cPlaySoundEffect);

			this.addArgument(new CommandArgument<SoundEffect>("sound", ArgTypeSoundEffect.get(), true, true, plugin.messages.cPlaySoundEffectName));
			this.addArgument(new CommandArgument<Player>("player", ArgTypePlayer.get(), true, true, plugin.messages.cPlaySoundEffectPlayer));
			this.addArgument(new CommandArgument<Double>("x", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cPlaySoundEffectX));
			this.addArgument(new CommandArgument<Double>("y", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cPlaySoundEffectY));
			this.addArgument(new CommandArgument<Double>("z", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cPlaySoundEffectZ));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			SoundEffect soundEffect = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("sound"));
			Player player = PlayerUtil.getPlayer(commandInfo.getNamedArg("player"));
			double x = commandInfo.getNamedArg("x") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("x")) : 0;
			double y = commandInfo.getNamedArg("y") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("y")) : 0;
			double z = commandInfo.getNamedArg("z") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("z")) : 0;

			if(!SoundEffectApi.playSoundEffect(soundEffect.getName(), player, x, y, z)) {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.ePlaySoundEffect);
				return false;
			}

			return true;
		}
	}




	public class SeCommandSpawnSoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandSpawnSoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.Spawn.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.spawn.toString(), cmd.s.toString()));
			this.setPermission(Permissions.PLAY_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cSpawnSoundEffect);

			this.addArgument(new CommandArgument<SoundEffect>("sound", ArgTypeSoundEffect.get(), true, true, plugin.messages.cSpawnSoundEffectName));
			this.addArgument(new CommandArgument<World>("world", ArgTypeWorld.get(), true, true, plugin.messages.cSpawnSoundEffectWorld));
			this.addArgument(new CommandArgument<Double>("x", ArgTypeDoubleEvaluate.get(), true, true, plugin.messages.cSpawnSoundEffectX));
			this.addArgument(new CommandArgument<Double>("y", ArgTypeDoubleEvaluate.get(), true, true, plugin.messages.cSpawnSoundEffectY));
			this.addArgument(new CommandArgument<Double>("z", ArgTypeDoubleEvaluate.get(), true, true, plugin.messages.cSpawnSoundEffectZ));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			SoundEffect soundEffect = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("sound"));
			World world = Bukkit.getServer().getWorld(commandInfo.getNamedArg("world"));
			double x = commandInfo.getNamedArg("x") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("x")) : 0;
			double y = commandInfo.getNamedArg("y") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("y")) : 0;
			double z = commandInfo.getNamedArg("z") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("z")) : 0;

			if(!SoundEffectApi.playSoundEffect(soundEffect.getName(), world, x, y, z)) {
				ErrorPrefixedMessage.toPlayer(commandInfo.getPlayer(), prefix, plugin.messages.ePlaySoundEffect);
				return false;
			}

			return true;
		}
	}




	public class SeCommandBroadcastSoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandBroadcastSoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.Broadcast.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.broadcast.toString(), cmd.b.toString()));
			this.setPermission(Permissions.PLAY_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cBroadcastSoundEffect);

			this.addArgument(new CommandArgument<SoundEffect>("sound", ArgTypeSoundEffect.get(), true, true, plugin.messages.cBroadcastSoundEffectName));
			this.addArgument(new CommandArgument<World>("world", ArgTypeWorld.get(), false, false, plugin.messages.cSpawnSoundEffectWorld));
			this.addArgument(new CommandArgument<Double>("x", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cBroadcastSoundEffectX));
			this.addArgument(new CommandArgument<Double>("y", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cBroadcastSoundEffectY));
			this.addArgument(new CommandArgument<Double>("z", ArgTypeDoubleEvaluate.get(), false, false, plugin.messages.cBroadcastSoundEffectZ));
		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			SoundEffect soundEffect = SoundEffectApi.listSoundEffect().get(commandInfo.getNamedArg("sound"));
			World world = commandInfo.getNamedArg("world") != null ? Bukkit.getServer().getWorld(commandInfo.getNamedArg("world")) : null;
			double x = commandInfo.getNamedArg("x") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("x")) : 0;
			double y = commandInfo.getNamedArg("y") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("y")) : 0;
			double z = commandInfo.getNamedArg("z") != null ? SoundEffect.evaluate(commandInfo.getNamedArg("z")) : 0;

			SoundEffectApi.broadcastSoundEffect(soundEffect.getName(), world, x, y, z);
			return true;
		}
	}




	public class SeCommandListSoundEffect extends Command<SoundEffectPlugin>
	{
		public SeCommandListSoundEffect(SoundEffectPlugin plugin)
		{
			super(cmd.List.toString(), plugin);
			this.setAliases(Lists.newArrayList(cmd.list.toString(), cmd.l.toString()));
			this.setPermission(Permissions.PLAY_SOUND_EFFECT.toString());
			this.setDescription(plugin.messages.cListSoundEffect);

		}

		@Override
		public boolean onCommand(CommandInfo commandInfo)
		{
			if(SoundEffectApi.listSoundEffect().size() == 0)
			{
				WarningMessage.toPlayer(commandInfo.getPlayer(), plugin.messages.eListEmpty);
			}
			else
			{
				// Empty line
				EmptyMessage.toPlayer(commandInfo.getPlayer());

				// Title line
				Title title = new Title('-', ChatColor.AQUA, new Sentence(plugin.messages.mList, ChatColor.YELLOW));
				TitleMessage.toPlayer(commandInfo.getPlayer(), title);

				// Sounds
				for(SoundEffect soundEffect : SoundEffectApi.listSoundEffect().getList())
				{
					// Create the clickable sentence
					Sentence soundName = new Sentence(soundEffect.getName() + (soundEffect.getDescription() != null && !soundEffect.getDescription().isEmpty() ? " : " : ""));

					MessageBuilder hoverText = new MessageBuilder("");
					hoverText.append(plugin.messages.mListClick).color(MessageColor.SUBCOMMAND.color());
					ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/se p "+ soundEffect.getName() + " " + commandInfo.getPlayer().getName());
					HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create());
					soundName.color(MessageColor.COMMAND.color()).event(clickEvent).event(hoverEvent);

					// Line
					Line soundLine = new Line();
					soundLine.add(new Sentence(Character.toString('\u00BB') + " ").color(MessageColor.COMMAND.color()));
					// - Sound
					soundLine.add(soundName);
					// - Description
					if(soundEffect.getDescription() != null && !soundEffect.getDescription().isEmpty())
						soundLine.add(new Sentence(soundEffect.getDescription()).color(MessageColor.MANDATORY_ARGUMENT.color()));

					new Message(soundLine).send(commandInfo.getPlayer());
				}
			}

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
					// Reload sounds file
					plugin.sounds.load();

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
			this.setDisplayArguments(false);
			this.setDisplayInlineDescription(true);
		}
	}

}
