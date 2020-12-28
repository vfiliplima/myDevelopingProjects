package org.academiadecodigo.offstrings.phases.game.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObject {
    private final int startY;
    private final int startX;
    protected Direction direction;
    protected Picture representation;
    protected int speed;

    protected GameObject(Direction startingDirection, int startX, int startY, String pathToRepresentation, int speed){
        direction = startingDirection;
        representation = new Picture(startX, startY, pathToRepresentation);
        this.startY = startY;
        this.startX = startX;
        this.speed = speed;
    }

    public void move(){
        switch (direction){
            case UP:
                representation.translate(0, -speed);
                break;
            case DOWN:
                representation.translate(0, speed);
                break;
            case LEFT:
                representation.translate(-speed, 0);
                break;
            case RIGHT:
                representation.translate(speed, 0);
                break;
        }
    }

    public void show(){
        representation.draw();
    }

    protected void resetRepresentation(){
        representation.translate(startX - representation.getX(), startY - representation.getY());
    }
}
