import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeBlock{

    public PositionClass position;
    public static int size = 50;
    private boolean isHead;

    public SnakeBlock(PositionClass pos, boolean isHead) {
        this.position = pos;
    }




    public void draw(java.awt.Graphics g, int spriteId){
        //System.out.println(position);
        if(GameStatus.isRunning()){
                int sx = (spriteId%6) * GameConfig.UNIT_SIZE;
                int sy = GameConfig.UNIT_SIZE * (spriteId/6);
                g.drawImage(GameStatus.texture, position.x, position.y, position.x + GameConfig.UNIT_SIZE,
                        position.y + GameConfig.UNIT_SIZE, sx, sy, sx + GameConfig.UNIT_SIZE, sy + GameConfig.UNIT_SIZE,
                        null);
//            g.setColor(java.awt.Color.green);
//            g.fillRect(position.x, position.y, size, size);
        }
    }
}
