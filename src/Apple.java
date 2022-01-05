import java.awt.*;
import java.util.Random;

public class Apple {
    public PositionClass position = new PositionClass(0,0);

    Random random = new Random();

    public Apple() {
        position.x = random.nextInt((int)(GameConfig.SCREEN_WIDTH/GameConfig.UNIT_SIZE))*GameConfig.UNIT_SIZE;
        position.y = random.nextInt((int)(GameConfig.SCREEN_HEIGHT/GameConfig.UNIT_SIZE))*GameConfig.UNIT_SIZE;
    }

    public void newApple(Snake player){
        position.x = random.nextInt((GameConfig.SCREEN_WIDTH/GameConfig.UNIT_SIZE))*GameConfig.UNIT_SIZE;
        position.y = random.nextInt((GameConfig.SCREEN_HEIGHT/GameConfig.UNIT_SIZE))*GameConfig.UNIT_SIZE;
        for(SnakeBlock block : player.body){
            if(block.position.equals(position)){
                newApple(player);
            }
        }
    }

    public void draw(Graphics g, int spriteId){
        if(GameStatus.isRunning()) {
            int sx = (spriteId%6) * GameConfig.UNIT_SIZE;
            int sy = GameConfig.UNIT_SIZE * (spriteId/6);
            g.drawImage(
                    GameStatus.texture,
                    position.x, position.y,
                    position.x + GameConfig.UNIT_SIZE,
                    position.y + GameConfig.UNIT_SIZE,
                    sx,
                    sy,
                    sx + GameConfig.UNIT_SIZE,
                    sy + GameConfig.UNIT_SIZE,
                    null
            );

        }
    }

}
