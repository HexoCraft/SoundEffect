package com.github.hexocraft.soundeffect.command.ArgType;

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

import com.github.hexocraftapi.command.CommandInfo;
import com.github.hexocraftapi.command.type.ArgType;
import com.github.hexocraft.soundeffect.SoundEffectApi;
import com.github.hexocraft.soundeffect.sound.SoundEffect;
import com.google.common.collect.ImmutableList;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ArgTypeSoundEffect implements ArgType<SoundEffect>
{
	private ArgTypeSoundEffect() {};
	private static ArgTypeSoundEffect t = new ArgTypeSoundEffect();
	public static ArgTypeSoundEffect get() { return t; }

	@Override
	public boolean check(String soundEffectName)
	{
		return get(soundEffectName)!=null;
	}

	@Override
	public SoundEffect get(String soundEffectName)
	{
		try
		{
			return SoundEffectApi.listSoundEffect().get(soundEffectName);
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<String> tabComplete(CommandInfo commandInfo)
	{
		String lastWord = commandInfo.numArgs() == 0 ? "" : commandInfo.getArgs().get(commandInfo.numArgs()-1);

		// List of sounds
		if(SoundEffectApi.listSoundEffect().getList() == null)
			return ImmutableList.of();

		ArrayList<String> matchedSoundEffect = new ArrayList<String>();
		for(SoundEffect soundEffect: SoundEffectApi.listSoundEffect().getList())
		{
			String name = soundEffect.getName();
			if(StringUtil.startsWithIgnoreCase(name, lastWord))
				matchedSoundEffect.add(name);
		}

		return matchedSoundEffect;
	}
}
