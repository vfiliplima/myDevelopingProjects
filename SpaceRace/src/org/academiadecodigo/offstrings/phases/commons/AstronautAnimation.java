package org.academiadecodigo.offstrings.phases.commons;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class AstronautAnimation {
    private static final String  FIRST_IMG = "1.png";
    private static final String  SECOND_IMG = "2.png";
    private static final String  THIRD_IMG = "3.png";

    private final Picture[] representations;
    private int currentImage;

    public AstronautAnimation(int x, int y, String pathToImages) {
        representations = new Picture[]{
                new Picture(x, y, pathToImages + FIRST_IMG),
                new Picture(x, y, pathToImages + SECOND_IMG),
                new Picture(x, y, pathToImages + THIRD_IMG),
        };
    }

    public void drawNextFrame(){
        representations[currentImage].delete();
        if(++currentImage == representations.length){
            currentImage = 0;
        }
        representations[currentImage].draw();
    }

    public void delete(){
        for (Picture p: representations) {
            p.delete();
        }
    }
}
