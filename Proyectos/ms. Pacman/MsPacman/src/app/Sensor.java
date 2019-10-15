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

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Sensor extends Application implements Initializable {

    @FXML
    private ImageView display;

    private BufferedImage image;
    private Mat mat_image;

    public void main(String[] args) throws Exception {
		launch(args);
	}

    @FXML
    private void doTakeScreenShot(){
        try {
            //display = new ImageView();
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            this.image = robot.createScreenCapture(rectangle);
            //this.mat_image = bufferedImageToMat(this.image);
            //Image my_image = SwingFXUtils.toFXImage(image, null);
            ImageIO.write(image, "jpg", new File("out.jpg"));
            //display.setImage(my_image);
        } catch (Exception e) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("asd");
		Button button = new Button();
		button.setText("click");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//System.out.println("Click");
				doTakeScreenShot();
				primaryStage.close();
			}
		});

		StackPane layout = new StackPane();
		layout.getChildren().add(button);

		Scene scene = new Scene(layout,50,50);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
    
    public Mat BufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_COLOR );
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
      }

    public BufferedImage getImage(){
        return this.image;
    }

    public Mat getMatImage(){
        return this.mat_image;
    }
}