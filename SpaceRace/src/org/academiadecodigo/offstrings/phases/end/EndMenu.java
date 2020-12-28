package org.academiadecodigo.offstrings.phases.end;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.offstrings.SpaceRace;
import org.academiadecodigo.offstrings.configs.ResourcePaths;
import org.academiadecodigo.offstrings.phases.GamePhase;
import org.academiadecodigo.offstrings.phases.commons.AstronautAnimation;
import org.academiadecodigo.offstrings.phases.game.Game;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EndMenu implements GamePhase {
    private static final String WINNER_TEXT = "win-text.png";
    private static final int ASTRONAUT_X = 713;
    private static final int ASTRONAUT_Y = 450;
    private static final int SLEEP = 200;
    private static final int MESSAGE_X = 405;
    private static final int MESSAGE_Y = 175;

    private final Picture background;
    private final Sound clapping;
    private AstronautAnimation winner;
    private Picture winnerText;
    private boolean idling;

    public EndMenu() {
        background = new Picture(SpaceRace.PADDING, SpaceRace.PADDING, ResourcePaths.END_BACKGROUND);
        clapping = new Sound(ResourcePaths.END_MUSIC);
        idling = true;
    }

    public void execute(){
        background.draw();
        winner = new AstronautAnimation(ASTRONAUT_X, ASTRONAUT_Y, Game.getWinningPlayer());
        winnerText = new Picture(MESSAGE_X,MESSAGE_Y, Game.getWinningPlayer() + WINNER_TEXT);
        winnerText.draw();
        clapping.play(true);
        clapping.setLoop(10); //due to Sound lib limitations
        while (idling){
            winner.drawNextFrame();
            sleep();
        }
        cleanup();
    }

    private void sleep(){
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cleanup(){
        winner.delete();
        winnerText.delete();
        clapping.stop();
        winner = null;
        background.delete();
        idling = true;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if(e.getKey() == KeyboardEvent.KEY_R){
            idling = false;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {

    }
}
