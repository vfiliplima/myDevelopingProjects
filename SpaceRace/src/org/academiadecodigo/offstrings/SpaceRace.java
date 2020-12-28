package org.academiadecodigo.offstrings;

import org.academiadecodigo.offstrings.phases.GamePhase;
import org.academiadecodigo.offstrings.input.KeyboardManager;
import org.academiadecodigo.offstrings.phases.end.EndMenu;
import org.academiadecodigo.offstrings.phases.game.Game;
import org.academiadecodigo.offstrings.phases.start.StartMenu;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;

public class SpaceRace {
    public static final int PADDING = 10;

    public void init() {
        KeyboardManager keyboardManager = new KeyboardManager();
        Keyboard keyboard = new Keyboard(keyboardManager);
        keyboardManager.setupKeyEvents(keyboard);
        GamePhase[] phases = new GamePhase[]{
                new StartMenu(),
                new Game(),
                new EndMenu()
        };

        while (true) {
            for (GamePhase phase: phases){
                keyboardManager.setHandler(phase);
                phase.execute();
            }
        }
    }
}
