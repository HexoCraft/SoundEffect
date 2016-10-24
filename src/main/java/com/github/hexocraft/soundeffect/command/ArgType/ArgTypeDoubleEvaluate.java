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
import com.github.hexocraft.soundeffect.sound.SoundEffect;

import java.util.List;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ArgTypeDoubleEvaluate implements ArgType<Double>
{
	private ArgTypeDoubleEvaluate() {};
	private static ArgTypeDoubleEvaluate t = new ArgTypeDoubleEvaluate();
	public static ArgTypeDoubleEvaluate get() { return t; }

	@Override
	public boolean check(String volume)
	{
		return get(volume)!=null;
	}

	@Override
	public Double get(String dbl)
	{
		try
		{
			// Volume must positive
			return (double) SoundEffect.evaluate(dbl);
		}
		catch(Exception e)
		{
			return 0d;
		}
	}

	@Override
	public List<String> tabComplete(CommandInfo commandInfo)
	{
		return null;
	}
}
