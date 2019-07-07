# game

A 3D Java game engine!

## Quick-start guide

1. Clone this GitHub repository: `git clone https://github.com/maxwellreuter/game.git`

2. Get the Eclipse IDE to manage Java code (try `www.eclipse.org/downloads/packages`).

3. Open Eclipse and do `File -> Open Projects from File System...`, then navigate to the `GameEngine` folder, select it, and confirm.

4. Configure your Eclipse project starting in the `game` directory as done here: `https://youtu.be/Jdkq-aSFEA0?t=86`. It may be a bit tricky to get Eclipse to recognize the library properly.

5. Skip this step if you're not using macOS. If you are, right-click the project (`GameEngine`), then do `Build Path -> Configure Build Path`, then drag the three `.jar` files from under `Modulepath` to under `Classpath`. Then, you might need to click on the JRE System Library in the same window, then click `Edit...` and select your JRE again. If one doesn't work, try another.

6. Run `ValidateSetup.java`. If a window pops up, everything's working!

## Acknowledgments

* ThinMatrix's guide is the foundation of our game engine: `https://www.youtube.com/user/ThinMatrix`
