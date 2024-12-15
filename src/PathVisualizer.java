import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PathVisualizer extends Application
{
    public List<Point> path = new ArrayList<Point>();
    @Override
    public void start (Stage primaryStage)
    {
        // Load the supermarket image with a proper file URL
        String imagePath =
                "file:products with names lowres.jpg";
        Image supermarketImage = new Image(imagePath);
        ImageView imageView = new ImageView(supermarketImage);

        // Create a pane to hold the image and path
        Pane pane = new Pane();
        pane.getChildren().add(imageView);

        ObjectMapper mapper = new ObjectMapper();
        try
        {
            // Read the JSON file and map it to a List of Points
            path = mapper.readValue(new File("points.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Point.class));

            // Print loaded points
            for (Point point : path)
            {
                System.out.println("Point: (" + point.x + ", " + point.y + ")");
            }
            // Use the points in your JavaFX code or other logic
            // e.g., draw the points in a pane or process them further
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Draw the path
        int count = path.size() - 1;
        double height = 996d, ratio = 100f / 7974f * height;
        System.out.println(ratio + " " + height);
        for (int i = 0; i < count; i++)
        {
            double startX = path.get(i).x * ratio;
            double startY = height - path.get(i).y * ratio;
            double endX = path.get(i + 1).x * ratio;
            double endY = height - path.get(i + 1).y * ratio;

            // Draw a line between points
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.DARKBLUE);
            line.setStrokeWidth(4);
            pane.getChildren().add(line);

            // Draw a circle at each point
            Circle circle = new Circle(startX, startY, 5, Color.BLUE);
            pane.getChildren().add(circle);
        }

        // Add a circle for the last point
        double lastPointX = path.getLast().x * ratio;
        double lastPointY = height - path.getLast().y * ratio;
        Circle lastCircle = new Circle(lastPointX, lastPointY, 5, Color.BLUE);
        pane.getChildren().add(lastCircle);

        // Display the scene
        Scene scene = new Scene(pane, supermarketImage.getWidth(), supermarketImage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Navigation");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
