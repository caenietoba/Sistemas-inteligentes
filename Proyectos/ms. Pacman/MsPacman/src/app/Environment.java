package app;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Environment {

    public void run(String[] args) throws Exception {

        Sensor sensor = new Sensor();
        sensor.main(args);

        findMsPacman(sensor.getImage());

    }

    private void findMsPacman(BufferedImage image){
        
    }

    private void findMsPacman( Mat image ){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source = image;
        Mat template = null;
        String filePath = "C:\\Users\\cenb\\Pictures\\ms-pacman\\";
        //Load image file
        source=Imgcodecs.imread("out.jpg");
        template=Imgcodecs.imread(filePath+"right1.png");
    
        Mat outputImage = new Mat();    
        int machMethod = Imgproc.TM_CCOEFF;
        //Template matching method
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
 
    
        MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;
        //Draw rectangle on result image
        Imgproc.rectangle(source, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(255, 255, 255));
 
        Imgcodecs.imwrite(filePath+"sonuc.jpg", source);
        System.out.println("Complated.");
    }

}