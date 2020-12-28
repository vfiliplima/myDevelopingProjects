package org.academiadecodigo.offstrings.input;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public enum Key {
    SPACE(KeyboardEvent.KEY_SPACE, KeyboardEventType.KEY_PRESSED),
    R(KeyboardEvent.KEY_R, KeyboardEventType.KEY_PRESSED),
    W_PRESS(KeyboardEvent.KEY_W, KeyboardEventType.KEY_PRESSED),
    W_RELEASE(KeyboardEvent.KEY_W, KeyboardEventType.KEY_RELEASED),
    S_PRESS(KeyboardEvent.KEY_S, KeyboardEventType.KEY_PRESSED),
    S_RELEASE(KeyboardEvent.KEY_S, KeyboardEventType.KEY_RELEASED),
    O_PRESS(KeyboardEvent.KEY_O, KeyboardEventType.KEY_PRESSED),
    O_RELEASE(KeyboardEvent.KEY_O, KeyboardEventType.KEY_RELEASED),
    L_PRESS(KeyboardEvent.KEY_L, KeyboardEventType.KEY_PRESSED),
    L_RELEASE(KeyboardEvent.KEY_L, KeyboardEventType.KEY_RELEASED),
    Q(KeyboardEvent.KEY_Q, KeyboardEventType.KEY_PRESSED);

    public final int keyNumber;
    public final KeyboardEventType type;

    Key(int keyNumber, KeyboardEventType type) {
        this.keyNumber = keyNumber;
        this.type = type;
    }
}
