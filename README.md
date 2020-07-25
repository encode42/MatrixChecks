# MatrixConfig
[logo here]  
The continuation of [MatrixConfig](https://git.disroot.org/lampenit/MatrixConfig) by gowgotz.

[![Matrix Anticheat](https://img.shields.io/badge/Plugin-Matrix%20Anticheat-%237009ac?style=flat-square)](https://www.mc-market.org/resources/13999)
![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/Encode42/MatrixConfig/latest/master?label=Commits%20since%20release&style=flat-square)

[![Support Discord](https://img.shields.io/discord/707330384328654869?color=7289DA&label=Support&style=flat-square)](https://discord.gg/rjSkFyj)
[![Matrix Support](https://img.shields.io/discord/392904793758367745?color=7289DA&label=Matrix%20Support&style=flat-square)](https://discord.gg/rGhYma6)

## About
The optimized config for [Matrix Anticheat](https://www.mc-market.org/resources/13999/), a powerful anticheat for Minecraft.  

This config works for both the free and premium versions of Matrix.  
**Make sure to use the config meant for the version of Matrix you use!**  

#### Features
- Less false positives.
- Better warning messages.
- More strict checks. *(Better detection)*
- Up to date!

## Setup
1. Download either [checks.free.yml](https://raw.githubusercontent.com/Encode42/MatrixConfig/master/checks.free.yml) or [checks.premium.yml](https://raw.githubusercontent.com/Encode42/MatrixConfig/master/checks.premium.yml) depending on what version of Matrix you use.  
<sub>For specific Matrix versions, head to the [releases page](https://github.com/Encode42/MatrixConfig/releases).</sub>
2. Upload/move the checks file to your Matrix plugin folder. (`<server>/plugins/matrix/`)
3. Rename the original `checks.yml` to something else. `checks.old.yml` will work.
4. Rename the new `checks.free.yml` or `checks.premium.yml` to `checks.yml`.
5. Restart your server!

## FAQ
This config may not work perfectly with your server.  
Plugins, software, performance can all effect how well this config and the anticheat in general will work.  
**This is not a drag-and-drop solution! Some values may need to be changed to work best with your setup.**  

Matrix Anticheat isn't a perfect anticheat. It itself has bugs that we cannot fix.  
This config aims to mitigate those issues and improve what works well, but there's only so much we can do.

### 0. Suggested changes
1. `nofall.damage: true` -> `nofall.damage: false` (For servers with fall-damage disabled)
2. `hitbox.max-reach: 3.2` -> `hitbox.max_reach: 3.3` (If you have issues with the hitbox check)  
<sub>**NOTE**: Makes hitbox detection more lenient! Only change this if you *have* to.</sub>
3. `killaura.modules.autoclicker.max_cps: 18` -> `killaura.modules.autoclicker.max_cps: (number from 8 to 24)` (Lower means less CPS, higher means more CPS)  
<sub>**NOTE**: The highest possible human CPS is 24. This means the number should not be above that as that indicates a macro.</sub>
4. `inventory.cancel_vl: 8` -> `inventory.cancel_vl: (number from 8 to 14)` (Increase if the check causes false positives)
5. `fastuse.commands.24: ...` -> `fastuse.commands.(number): ...` (Decrease if players should be kicked quicker, increase if causes false positives)
6. `block.fastplace.max_place_per_second: 16` -> `block.fastplace.max_place_per_second: (number from 9 to 19)` (Decrease if you think there are bypasses, increase if the check causes false positives)

### 1. Why is the anti-killaura NPC spawned in the HitBox check?  
This check also checks if the player isn't using Angle cheats, and the NPC checks if the player can hit people behind the player. To prevent lag and false positives from happening with this, the NPC will be spawned only if the player's ping is lower than 185ms. If I'm right, this should make the killaura detection more strict. [This has been approved by RE](https://github.com/jiangdashao/Matrix-Issues/commit/988e130f60559105cea7ec384e49357864b9f5b4).

### 2. How do I report a change or false positive?
**FIRST:**  
Make sure this isn't an issue with the anticheat itself. Not all issues can be fixed with a config. Head over to Matrix's support Discord and ask about the issue, or report the issue at their issue tracker.  
You can also try using the default config to see if the issue still stands. If so, it's not a problem with the config.  
Matrix's Discord: [Here](https://discord.gg/wjheaRj)  
Issue tracker: [Here](https://github.com/jiangdashao/Matrix-Issues/issues)

**THEN:**  
Make an issue at the issues page with the right template. If you describe what you want changed/fixed well, chances are it'll be taken care of quickly. If you already know what the issue is or how to fix it, feel free to make a pull request containing the change and why you made it.  
Issues: [Here](https://github.com/Encode42/MatrixConfig/issues)  
Pull Requests: [Here](https://github.com/Encode42/MatrixConfig/pulls)  

### 3. Can I modify the config?
Yes! I encourage you do so. You can also distribute it all you want, just please don't claim it all as your own. (Credit the original authors.)
