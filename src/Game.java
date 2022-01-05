import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener{

    Timer timer;

    Snake player = new Snake(GameConfig.START_SIZE,new PositionClass(500,500));
    Apple apple = new Apple();

    Game(){

        GameStatus.loadTexture();
        this.setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        this.setBackground(new Color(150, 206, 143));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        timer = new Timer(GameConfig.DELAY,this);
        startGame();
    }

    public void startGame() {

        GameStatus.setStatus(GameStatus.StatusOptions.RUNNING);
        GameStatus.Score = 0;
        player = new Snake(GameConfig.START_SIZE,new PositionClass(500,500));
        apple = new Apple();
        GameStatus.reInitDirection();
        timer.start();
    }

    public void gameOver(){
        GameStatus.setStatus(GameStatus.StatusOptions.OVER);
        //timer.stop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        apple.draw(g, 11);
        drawHUD(g);
    }
    public void drawHUD(Graphics g) {
        if(GameStatus.isRunning()){
            g.setColor(new Color(2, 96, 2, 255));
            g.setFont(new Font("Helvetica", Font.BOLD, 40));
            int xCoord = 100;
            int yCoord = 50;
            g.drawString("Score: " + GameStatus.Score, xCoord, yCoord);
        }
        else{
            g.setColor(new Color(2, 96, 2, 255));
            g.setFont(new Font("Helvetica", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            int xCoord = (GameConfig.SCREEN_WIDTH - metrics.stringWidth("Your score was: " + GameStatus.Score)) / 2;
            int yCoord = (GameConfig.SCREEN_HEIGHT - g.getFont().getSize()) / 2;
            g.drawString("Your score was: " + GameStatus.Score, xCoord, yCoord);
            g.drawString("Press any key to restart", xCoord - 50, yCoord+50);
        }
      }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameStatus.isRunning()) {
            GameStatus.updateDirection();
            player.move(GameStatus.getDirection());
            checkCollisionWithApple();
            checkIfLost();
        }
        repaint();
    }

    public void checkCollisionWithApple(){
        //System.out.println(player.getHeadPosition()+" == "+apple.position);
        if(apple.position.equals(player.getHeadPosition())){
            player.eat();
            apple.newApple(player);
            GameStatus.Score++;
        }
    }

    public void checkIfLost(){
        //System.out.println(player.getHeadPosition());
        if(player.selfEaten()
        || player.getHeadPosition().x > GameConfig.SCREEN_WIDTH - GameConfig.UNIT_SIZE
        || player.getHeadPosition().x < 0
        || player.getHeadPosition().y > GameConfig.SCREEN_HEIGHT - GameConfig.UNIT_SIZE
        || player.getHeadPosition().y < 0)
        {
            gameOver();
        }

    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(GameStatus.isRunning()) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        GameStatus.changeDirection(GameStatus.Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        GameStatus.changeDirection(GameStatus.Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        GameStatus.changeDirection(GameStatus.Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        GameStatus.changeDirection(GameStatus.Direction.DOWN);
                        break;
                }
            }
            else{
                startGame();
                System.out.println(GameStatus.isRunning());
            }
        }
    }
}