package app;

import java.awt.image.BufferedImage;
import java.awt.Point;

public class Util{
    
    public static int[] findFirstPixel(BufferedImage image){
        Point pixel = new Point();
        int[] first_pixel = new int[2];
        //System.out.println(image.getRGB(717, 292));
        System.out.println(image.getRGB(image.getWidth()/2, image.getHeight()/2));
        for(int row=0; row<image.getHeight(); row++)
            for(int col=0; col<image.getWidth(); col++){
                if(image.getRGB(col, row) == -16777216){
                    first_pixel[0] = row;
                    first_pixel[1] = col;
                    pixel.x = row;
                    pixel.y = col;
                    System.out.println(row + " " + col);
                    return first_pixel;
                } 
            }
        System.out.println(image.getRGB(123, 135));
        //System.out.println(image.getRGB(image.getWidth()/2, image.getHeight()/2));
        return null;
    }

}