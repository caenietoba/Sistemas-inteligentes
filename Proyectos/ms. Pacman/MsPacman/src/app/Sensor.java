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

public class Sensor{

    private BufferedImage image;
    private static BufferedImage _image;

    public void main(String[] args) throws Exception {
        //launch(args);
        Ui ui = new Ui();
        ui.setBounds(100,150,100,90);
        ui.setVisible(true);
        ui.setResizable(false);
    }
    
    private class Ui extends JFrame implements ActionListener{
        
        private static final long serialVersionUID = 1L;

        private JButton button;

        public Ui(){
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

    public static void capturarPantalla(){
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(rectangle);
            ImageIO.write(image, "jpg", new File("out.jpg"));
            Sensor._image = image;
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