package org.academiadecodigo.offstrings.phases;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public interface GamePhase extends KeyboardHandler {
    void execute();
}
