package org.academiadecodigo.offstrings.phases.start;

import org.academiadecodigo.bootcamp.Sound;
import org.academiadecodigo.offstrings.SpaceRace;
import org.academiadecodigo.offstrings.configs.ResourcePaths;
import org.academiadecodigo.offstrings.phases.GamePhase;
import org.academiadecodigo.offstrings.phases.commons.AstronautAnimation;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class StartMenu implements GamePhase {

    private static final int ASTRONAUT1_X = 100;
    private static final int ASTRONAUT2_X = 1370;
    private static final int ASTRONAUTS_Y = 550;
    private static final int SPACEBAR_X = 588;
    private static final int SPACEBAR_Y = 300;
    private static final int SLEEP_TIME = 200;

    private final AstronautAnimation astronautAnimation1;
    private final AstronautAnimation astronautAnimation2;
    private final Picture spaceBarMessage;
    private final Picture background;
    private final Sound music;
    private boolean showingSpacebarImage;
    private boolean idling;

    public StartMenu() {
        astronautAnimation1 = new AstronautAnimation(ASTRONAUT1_X,ASTRONAUTS_Y, ResourcePaths.ASTRONAUT1_IMAGES);
        astronautAnimation2 = new AstronautAnimation(ASTRONAUT2_X, ASTRONAUTS_Y, ResourcePaths.ASTRONAUT2_IMAGES);
        spaceBarMessage = new Picture(SPACEBAR_X, SPACEBAR_Y, ResourcePaths.SPACEBAR_MESSAGE);
        background = new Picture(SpaceRace.PADDING, SpaceRace.PADDING, ResourcePaths.MENU_BACKGROUND);
        music = new Sound(ResourcePaths.GAME_MUSIC);
        idling = true;
        showingSpacebarImage = false;
    }

    public void execute(){
        background.draw();
        music.play(true);
        music.setLoop(5); //due to Sound lib limitations
        while (idling){
            astronautAnimation1.drawNextFrame();
            astronautAnimation2.drawNextFrame();
            showOrHideSpaceBarMessage();
            sleep();
        }
        cleanup();
    }

    private void showOrHideSpaceBarMessage(){
        if(showingSpacebarImage){
            spaceBarMessage.delete();
            showingSpacebarImage = false;
        } else {
            spaceBarMessage.draw();
            showingSpacebarImage = true;
        }
    }

    private void sleep(){
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cleanup(){
        astronautAnimation1.delete();
        astronautAnimation2.delete();
        background.delete();
        spaceBarMessage.delete();
        music.stop();
        idling = true;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if(e.getKey() == KeyboardEvent.KEY_SPACE){
            idling = false;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
    }
}
