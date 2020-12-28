package org.academiadecodigo.offstrings.input;

import org.academiadecodigo.offstrings.phases.GamePhase;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardManager implements KeyboardHandler {
    private KeyboardHandler handler;

    public void setHandler(GamePhase handler){
        this.handler = handler;
    }

    public void setupKeyEvents(Keyboard keyboard){
        for (Key key: Key.values()) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key.keyNumber);
            event.setKeyboardEventType(key.type);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        //pressing Q at any point terminates the process
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q){
            System.exit(0);
        }
        handler.keyPressed(keyboardEvent);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        handler.keyReleased(keyboardEvent);
    }
}
