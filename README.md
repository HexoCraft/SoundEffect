# SoundEffect
SoundEffect is a plugin and an API which make easy to create any sound effect by compiling all sort of existing sound already present in Minecraft.
<br>
<br>

#### Commands:
* /SoundEffect PlaySound \<sound> \<volume> \<pitch> : Play a sound
* /SoundEffect CreateSimpleEffect \<name> \<sound> \<volume> \<pitch> [delay]: Create a simple effect
* /SoundEffect CreateComplexEffect \<name> : Create a complex effect
* /SoundEffect AddSoundEffect \<soundEffect> \<soundEffectToAdd> [delay] : Add a sound effect
* /SoundEffect PlaySoundEffect \<soundEffect> \<player> [x] [y] [z] : Play a sound effect
* /SoundEffect SpawnSoundEffect \<soundEffect> \<world> [x] [y] [z] : Spawn a sound effect at location
* /SoundEffect BroadcastSoundEffect \<soundEffect> \<world> [x] [y] [z] : Broadcast a sound effect
* /SoundEffect ListSoundEffect : List of sound effect
* /SoundEffect reload : Reload SoundEffect
* /SoundEffect help : Display help
<br>
<br>

#### Permissions:
* SoundEffect.admin : Give all access, default to OP
* SoundEffect.play.sound : Enable player to play a sound
* SoundEffect.create.soundEffect : Enable player to create any type of sound effect
* SoundEffect.play.soundEffect : Enable player to play a sound effect
<br>
<br>

#### Messages:
All the messages sent by SoundEffect are configurable in the message.yml file.
You can also create your own message file and change it in the config file.
Feel free to send me any translation and I'll add it into SoundEffect.
<br>
<br>

#### Config:
The plugin use metrics and an integrated updater.<br>
Both can be disable in config.yml
<br>
<br>

#### API:
As it is designed as an API, you can add SoundEffect to your plugin using maven

```
    <repositories>
        <repository>
           <id>hexosse-repo</id>
           <url>https://raw.github.com/hexosse/maven-repo/master/</url>
       </repository>
    </repositories>
```

```
   <dependencies>
        <dependency>
            <groupId>com.github.hexosse</groupId>
            <artifactId>SoundEffect</artifactId>
            <version>1.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
```

The API can be found in SoundEffectApi.
<br>
<br>


#### Ressources:
Releases : https://github.com/hexosse/SoundEffect/releases
