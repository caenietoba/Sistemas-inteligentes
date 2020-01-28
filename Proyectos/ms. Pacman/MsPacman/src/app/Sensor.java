package app;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.ColorConvertOp;
import java.awt.color.ColorSpace;


public class Sensor{

    private BufferedImage image;

    //private final static String path = "./C:/Users/cenb/Documents/Camilo/Proyectos/\"Sistemas Inteligentes\"/Proyectos/\"ms. Pacman\"/MsPacman/";
    private final static String path = "";

    public void main(String[] args) throws Exception {
        Ui ui = new Ui();
        ui.setBounds(100, 150, 100, 90);
        ui.setVisible(true);
        ui.setResizable(false);
    }

    private class Ui extends JFrame implements ActionListener {

        private static final long serialVersionUID = 1L;

        private JButton button;

        public Ui() {
            setLayout(null);
            button = new JButton("Captura");
            button.setBounds(45, 0, 100, 50);
            button.addActionListener(this);
            add(button);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button) {
                Sensor.capturarPantalla();
            }
        }
    }

    public static void capturarPantalla() {
        try {
            Robot robot = new Robot();
            BufferedImage image = null, dstImage = null, blackWhite = null, gris_bordes = null, gray = null;
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = robot.createScreenCapture(rectangle);
            ImageIO.write(image, "jpg", new File(path + "original.jpg"));
            Util.findFirstPixel(image);

            // Automatic converstion....
            ColorConvertOp ccop = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            gray = ccop.filter(image, null);

            float[] sharpen = new float[] {
                0.0f, -1.0f, 0.0f,
                -1.0f, 4.0f, -1.0f,
                0.0f, -1.0f, 0.0f
            };
            Kernel kernel = new Kernel(3, 3, sharpen);
            ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
            dstImage = op.filter(image, null);

            blackWhite = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g2d = blackWhite.createGraphics();
            g2d.drawImage(dstImage, 0, 0, null);
            g2d.dispose();

            gris_bordes = op.filter(gray, gris_bordes);

            /* for(int i=0; i<image.getWidth(); i++)
                for(int j=0; j<image.getHeight(); j++){
                    if(image.getRGB(i, j) < -8388608) image.setRGB(i, j, 0x000000);
                    else image.setRGB(i, j, 0xFFFFFF);
                } */

            Util.findFirstPixel(gray);
            Util.findFirstPixel(image);
            Util.findFirstPixel(blackWhite);
            Util.findFirstPixel(gris_bordes);
            Util.findFirstPixel(dstImage);

            ImageIO.write(dstImage, "jpg", new File(path + "bordes.jpg"));
            ImageIO.write(image, "jpg", new File(path + "bw_original.jpg"));
            ImageIO.write(gray, "jpg", new File(path + "gris.jpg"));
            ImageIO.write(blackWhite, "jpg", new File(path + "binario.jpg"));
            ImageIO.write(gris_bordes, "jpg", new File(path + "gris_bordes.jpg"));
        } catch (Exception e) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }
}