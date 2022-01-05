import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GameStatus {

    public enum StatusOptions {RUNNING, OVER};
    public enum Direction {UP, DOWN, LEFT, RIGHT};

    public static int Score = 0;

    public static BufferedImage texture;

    public static void loadTexture(){
        try{
            texture = ImageIO.read(new File("src/Sprites2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StatusOptions status = StatusOptions.OVER;
    private static Direction direction = Direction.RIGHT;
    private static Direction tempDirection = direction;

    public static void reInitDirection(){
        direction = Direction.RIGHT;
        tempDirection = Direction.RIGHT;
    }

    public static void changeDirection(Direction newDirection){
        if(direction == Direction.UP && newDirection == Direction.DOWN)
            return;
        if(direction == Direction.DOWN && newDirection == Direction.UP)
            return;
        if(direction == Direction.LEFT && newDirection == Direction.RIGHT)
            return;
        if(direction == Direction.RIGHT && newDirection == Direction.LEFT)
            return;
        tempDirection = newDirection;
    }

    public static void updateDirection(){
        direction = tempDirection;
    }

    public static Direction getDirection() {
        return direction;
    }

    public static void setStatus(StatusOptions newStatus){
        status = newStatus;
    }

    public static boolean isRunning(){
        return status == StatusOptions.RUNNING;
    }

}
