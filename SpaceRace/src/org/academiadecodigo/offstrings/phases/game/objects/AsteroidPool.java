package org.academiadecodigo.offstrings.phases.game.objects;

import java.util.Collections;
import java.util.LinkedList;

public class AsteroidPool {
    private static final int INITIAL_SIZE = 20;
    private static final int TIME_TO_SHUFFLE = 20;

    private final LinkedList<Asteroid> storage;
    private int shuffleCounter;

    public AsteroidPool() {
        storage = new LinkedList<>();
        shuffleCounter = 0;
        initAsteroids();
    }

    private void initAsteroids() {
        for (int i = 0; i < INITIAL_SIZE; i++) {
            storage.add(Asteroid.createInstance());
        }
        Collections.shuffle(storage);
    }

    public Asteroid get() {
        Asteroid a;
        if (storage.isEmpty()) {
            a = Asteroid.createInstance();
        } else {
            a = storage.poll();
        }
        a.show();
        return a;
    }

    public void giveBack(Asteroid a) {
        a.cleanup();
        storage.offer(a);
        shuffleCounter++;
        if(shuffleCounter == TIME_TO_SHUFFLE){
            Collections.shuffle(storage);
            shuffleCounter = 0;
        }
    }
}
