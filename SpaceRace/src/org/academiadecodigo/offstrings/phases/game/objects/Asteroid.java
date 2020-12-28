package org.academiadecodigo.offstrings.phases.game.objects;

import org.academiadecodigo.offstrings.SpaceRace;
import org.academiadecodigo.offstrings.configs.ResourcePaths;
import org.academiadecodigo.offstrings.phases.game.Game;

public class Asteroid extends GameObject {
    private static final int MAX_STARTING_Y = 700;
    public static final String[] ASTEROIDS = new String[]{
            ResourcePaths.ASTEROID1,
            ResourcePaths.ASTEROID2,
            ResourcePaths.ASTEROID3
    };

    private Asteroid(Direction direction, int startX, int startY, String pathToRepresentation) {
        super(direction, startX, startY, pathToRepresentation, 5);
    }

    public static Asteroid createInstance() {
        String pathToRepresentation = ASTEROIDS[(int) (Math.random() * ASTEROIDS.length)];
        int startY = (int) (Math.random() * MAX_STARTING_Y);
        double random = Math.random();
        if (random < 0.5) {
            return new Asteroid(Direction.RIGHT, 0, startY, pathToRepresentation);
        } else {
            return new Asteroid(Direction.LEFT, Game.MAX_X - 30, startY, pathToRepresentation);
        }
    }

    public boolean isOnEdge() {
        return (direction == Direction.RIGHT && representation.getMaxX() == Game.MAX_X)
                || (direction == Direction.LEFT && representation.getX() == SpaceRace.PADDING);
    }

    public void cleanup() {
        resetRepresentation();
        representation.delete();

    }
}
