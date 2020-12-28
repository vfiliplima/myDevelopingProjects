package org.academiadecodigo.offstrings.phases.game.objects;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.offstrings.SpaceRace;
import org.academiadecodigo.offstrings.configs.ResourcePaths;
import org.academiadecodigo.offstrings.phases.game.Game;

public class Player extends GameObject{
    private static final int START_Y = 777;
    private static final int SPEED = 10;
    private static final int TIME_BLOWING = 25;

    private final String startingImage;
    private final Sound explosionSound;
    private int cyclesToBlow;
    private int score;

    public Player(int x, String pathToImage) {
        super(Direction.IDLE, x, START_Y, pathToImage, SPEED);
        explosionSound = new Sound(ResourcePaths.EXPLOSION_SOUND);
        startingImage = pathToImage;
        cyclesToBlow = 0;
        score = 0;
    }

    @Override
    public void move() {
        if(cyclesToBlow > 0){
            cyclesToBlow--;
            if(cyclesToBlow == 0){
                resetRepresentation();
            }
            return;
        }
        if(representation.getMaxY() >= Game.MAX_Y && direction == Direction.DOWN){
            return;
        }
        if(representation.getY() <= SpaceRace.PADDING){
            score++;
            resetRepresentation();
        }
        super.move();
    }

    public void goUp(){
        direction = Direction.UP;
    }

    public void goDown(){
        direction = Direction.DOWN;
    }

    public void stop(){
        direction = Direction.IDLE;
    }

    public void resetRepresentation(){
        representation.load(startingImage);
        super.resetRepresentation();
    }

    public void cleanup(){
        score = 0;
        cyclesToBlow = 0; //redundant but as safety
        resetRepresentation();
        representation.delete();
        direction = Direction.IDLE;
    }

    public boolean isColliding(GameObject o){
        return cyclesToBlow <= 0
                && (o.representation.getY() <= representation.getMaxY()
                && o.representation.getX() <= representation.getMaxX()
                && o.representation.getMaxX() >= representation.getX()
                && o.representation.getMaxY() >= representation.getY());
    }

    public void blowUp(){
        explosionSound.play(true);
        representation.load(ResourcePaths.EXPLOSION);
        cyclesToBlow = TIME_BLOWING;
    }

    public int getScore() {
        return score;
    }
}
