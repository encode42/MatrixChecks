<img src="https://repository-images.githubusercontent.com/282035636/0f82a000-f37a-11ea-8fb2-9aa79ad4123e" width="200px" align="right">
<div align="center">
<h1>MatrixChecks</h1>
<h3>The optimized checks for <a href="https://www.mc-market.org/resources/13999">Matrix Anticheat</a>, a powerful and lightweight anticheat for Minecraft.</h3>
<h4>These checks work for both the free and premium versions of Matrix.</h4>
<br/>
</div>

<table align="center">
<tr>
<td>

**Make sure to use the checks meant for the version of Matrix you use!**  
<sub>Sometimes Matrix premium updates faster than free, there will be another file if so.</sub>

[![Matrix Anticheat](https://img.shields.io/badge/Plugin-Matrix%20Anticheat-%237009ac?style=flat-square)](https://www.mc-market.org/resources/13999) ![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/Encode42/MatrixChecks/latest/main?label=Commits%20since%20release&style=flat-square)  
[![Support Discord](https://img.shields.io/discord/707330384328654869?color=7289DA&label=Support&style=flat-square)](https://discord.gg/rjSkFyj) [![Matrix Support](https://img.shields.io/discord/392904793758367745?color=7289DA&label=Matrix%20Support&style=flat-square)](https://discord.gg/rGhYma6)
</td>
<td>

#### Features
- Less false positives.
- Better warning messages.
- More strict checks. *(Better detection)*
- Multiple checks types to suit your needs.
- Up to date!
</td>
</tr>
</table>

⚠ **NOTE:**  
`config.yml` **does not** include any checks!  
Install `checks.yml` to make use of the optimizations.  
The config file only includes minor changes to the base config!

## 🔧 Setup
### Server Usage
1. Download [checks.yml](https://raw.githubusercontent.com/Encode42/MatrixChecks/main/checks.yml) or [error.checks.yml](https://raw.githubusercontent.com/Encode42/MatrixChecks/main/error.checks.yml) depending on what you want.  
<sub>For specific Matrix versions, head to the [releases page](https://github.com/Encode42/MatrixChecks/releases).</sub>
2. Rename the original `checks.yml` to something else. `checks.old.yml` will work.
3. Upload/move the checks file to your Matrix plugin folder. (`/plugins/Matrix/`)
4. If you downloaded a checks file besides `checks.yml`, rename the new `*.checks.yml` to `checks.yml`.
5. Restart your server!

### Cloud Usage
#### Method 1
1. Download [config.yml](https://raw.githubusercontent.com/Encode42/MatrixChecks/main/config.yml) and replace your original `config.yml` with the downloaded file.
2. Change `cloud_config.enable: false` to `cloud_config.enable: true`.
3. Restart your server! It will now update the file every reboot.

#### Method 2
1. Open `config.yml` in your Matrix plugin folder. (`/plugins/Matrix/config.yml`)
2. Set `cloud_config.enable: false` to `cloud_config.enable: true`.
3. Change the line "`your configuration file link`" (cloud_config.links) to "`https://raw.githubusercontent.com/Encode42/MatrixChecks/main/cloud/checks.yml`" or any other checks file depending on what you want.  
<sub>For specific Matrix versions, replace `main` with the Matrix version number.</sub>

## ❔ FAQ
These checks may not work perfectly with your server.
Plugins, software, and performance can all affect how well these checks and the anticheat, in general, will work.
**This is not a drag-and-drop solution! Some values may need to be changed to work best with your setup.**

Matrix Anticheat isn't a perfect anticheat. It itself has bugs that we cannot fix.
These checks aims to mitigate those issues and improve what works well, but there's only so much we can do.
Tested and configured for survival and minigame servers. Tweak the checks for your own server!

### 1. Checks file types
#### checks.yml
The main checks file. Includes the standard checks and everything advertised.  
Conditional commands, optimized checks, increased speed, etc.

#### error.checks.yml
Same as `checks.yml` but with different kick messages.  
The messages are from classic Minecraft server disconnect messages such as "`java.net.SocketException: Connection reset`" and "`java.net.ConnectException: Connection timed out: no further information:`."

#### /cloud/\*.checks.yml
Same as `checks.yml` but minified. It is a lot smaller in file size, but it is nearly impossible to read.  
Because of its small file size though, it can be automatically updated fast on server startup.  
This is optimized for cloud usage in `config.yml`. For instructions, read [Cloud Usage](https://github.com/Encode42/MatrixChecks#cloud-usage).

### 2. Suggested changes
1. `nofall.damage: true` -> `nofall.damage: false` (For servers with fall-damage disabled)
2. `speed.tolerance: 0.0225` -> `speed.tolerance: (number from 0.0125 to 0.035)` (Decrease if players are bypassing speed checks)
3. `hitbox.max-reach: 3.2` -> `hitbox.max_reach: 3.3` (If you have issues with the hitbox check)  
<sub>**NOTE**: Makes hitbox detection more lenient! Only change this if you *have* to.</sub>
4. `killaura.modules.autoclicker.max_cps: 18` -> `killaura.modules.autoclicker.max_cps: (number from 8 to 24)` (Lower means less CPS, higher means more CPS)  
<sub>**NOTE**: The highest possible human CPS is 24. This means the number should not be above that as that indicates a macro.</sub>
5. `inventory.cancel_vl: 8` -> `inventory.cancel_vl: (number from 8 to 14)` (Increase if the check causes false positives)
6. `fastuse.commands.24: ...` -> `fastuse.commands.(number): ...` (Decrease if players should be kicked quicker, increase if causes false positives)
7. `block.fastplace.max_place_per_second: 16` -> `block.fastplace.max_place_per_second: (number from 9 to 19)` (Decrease if you think there are bypasses, increase if the check causes false positives)
8. `place.modules.delay.min_delay: 7` -> `place.modules.delay.min_delay: (number 5 to 9)` (Decrease if legit players are getting too many violations, increase if bypassed)

### 3. Can I modify the files in MatrixChecks?
Yes! I encourage you to do so. Since all servers are different, you most likely will have to modify the files anyways.  
You can also distribute it all you want or use it on a large network, just please don't claim it all as your own. (Credit the original authors)

### 4. How do I report a change or false positive?
**FIRST:**  
Make sure this isn't an issue with the anticheat itself. Not all issues can be fixed with a checks/config file. Head over to Matrix's support Discord and ask about the issue, or report the issue at their issue tracker.  
You can also try using the default checks + config to see if the issue still stands. If so, it's not a problem with MatrixChecks.  
Matrix's Discord: [Here](https://discord.gg/wjheaRj)  
Issue tracker: [Here](https://github.com/jiangdashao/Matrix-Issues/issues)

**THEN:**  
Make an issue at the issues page with the right template. If you describe what you want changed/fixed well, chances are it'll be taken care of quickly. If you already know what the issue is or how to fix it, feel free to make a pull request containing the change and why you made it.  
Issues: [Here](https://github.com/Encode42/MatrixChecks/issues)  
Pull Requests: [Here](https://github.com/Encode42/MatrixChecks/pulls)
