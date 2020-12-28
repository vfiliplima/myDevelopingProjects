package org.academiadecodigo.offstrings.phases.game;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.offstrings.SpaceRace;
import org.academiadecodigo.offstrings.configs.ResourcePaths;
import org.academiadecodigo.offstrings.phases.GamePhase;
import org.academiadecodigo.offstrings.phases.game.objects.Asteroid;
import org.academiadecodigo.offstrings.phases.game.objects.AsteroidPool;
import org.academiadecodigo.offstrings.phases.game.objects.Player;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game implements GamePhase {

    public static final int MAX_Y = 900;
    public static final int MAX_X = 1600;

    private static String winningPlayer;
    private static final int MAX_SCORE = 2;
    private static final int P1_X = 344;
    private static final int P2_x = 1151;
    private static final int GAME_SLEEP = 1000/60;
    private static final int LIST_SIZE = 20;
    private static final double SPAWN_CHANCE = 0.1;

    private final Picture background;
    private final Sound music;
    private final Player player1;
    private final Player player2;
    private final AsteroidPool pool;
    private final List<Asteroid> asteroids;

    public Game() {
        background = new Picture(SpaceRace.PADDING, SpaceRace.PADDING, ResourcePaths.GAME_BACKGROUND);
        music = new Sound(ResourcePaths.GAME_MUSIC);
        player1 = new Player(P1_X, ResourcePaths.PLAYER1_SHIP);
        player2 = new Player(P2_x, ResourcePaths.PLAYER2_SHIP);
        pool = new AsteroidPool();
        asteroids = new LinkedList<>();
        winningPlayer = null;
    }

    public void execute() {
        //this has to be done here instead of cleanup so that it's only reset when the game is started again
        //it's also possibly not the cleanest solution having this be static...
        winningPlayer = null;
        background.draw();
        player1.show();
        player2.show();
        music.play(true);
        music.setLoop(10); //Due to Sound lib limitations

        while (winningPlayer == null) {
            player1.move();
            player2.move();
            populateAsteroidContainer();
            moveAsteroids();
            sleep();
            winningPlayer = checkForWinner();
        }
        cleanup();
    }

    private void sleep() {
        try {
            Thread.sleep(GAME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void populateAsteroidContainer(){
        if(asteroids.size() <= LIST_SIZE){
            if (Math.random() < SPAWN_CHANCE){
                asteroids.add(pool.get());
            }
        }
    }

    private void moveAsteroids(){
        Iterator<Asteroid> iterator = asteroids.iterator();
        while (iterator.hasNext()){
            Asteroid a = iterator.next();
            a.move();
            if (a.isOnEdge()){
                iterator.remove();
                pool.giveBack(a);
                break;
            }
            if(player1.isColliding(a)){
                player1.blowUp();
            }
            if(player2.isColliding(a)){
                player2.blowUp();
            }
        }
    }

    private String checkForWinner(){
        if(player1.getScore() == MAX_SCORE){
            return ResourcePaths.ASTRONAUT1_IMAGES;
        }
        if (player2.getScore() == MAX_SCORE) {
            return ResourcePaths.ASTRONAUT2_IMAGES;
        }
        return null;
    }

    private void cleanup(){
        music.stop();
        player1.cleanup();
        player2.cleanup();
        background.delete();
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        switch (e.getKey()) {
            case KeyboardEvent.KEY_W:
                player1.goUp();
                break;
            case KeyboardEvent.KEY_S:
                player1.goDown();
                break;
            case KeyboardEvent.KEY_O:
                player2.goUp();
                break;
            case KeyboardEvent.KEY_L:
                player2.goDown();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        switch (e.getKey()) {
            case KeyboardEvent.KEY_W:
            case KeyboardEvent.KEY_S:
                player1.stop();
                break;
            case KeyboardEvent.KEY_O:
            case KeyboardEvent.KEY_L:
                player2.stop();
                break;
        }

    }

    public static String getWinningPlayer(){
        return winningPlayer;
    }
}
