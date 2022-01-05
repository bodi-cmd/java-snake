import java.util.ArrayList;
import java.awt.*;

public class Snake {
    ArrayList<SnakeBlock> body = new ArrayList<>();
    private PositionClass lastTailPosition = new PositionClass(0,0);

    Snake(int startSize, PositionClass pos){
        for(int i = 0; i < startSize; i++){
            body.add(new SnakeBlock(new PositionClass(pos.x - i * SnakeBlock.size, pos.y), i == 0));
        }
    }


    public void draw(Graphics g){
        for(int i = 0; i < body.size(); i++){
            SnakeBlock block = body.get(i);
            SnakeBlock nextBlock = body.get(Math.max(i - 1, 0));
            SnakeBlock prevBlock = body.get(Math.min(i + 1, body.size()-1));

            if(i == 0){
                String relativePosition = block.position.positionRelativeToObject(prevBlock.position);
                switch (relativePosition){
                    case "UNDER":
                        block.draw(g,15);
                        break;
                    case "ABOVE":
                        block.draw(g,3);
                        break;
                    case "LEFT":
                        block.draw(g,9);
                        break;
                    case "RIGHT":
                        block.draw(g,2);
                        break;
                }
            }
            else if(i == body.size()-1){
                String relativePosition = block.position.positionRelativeToObject(nextBlock.position);
                switch (relativePosition){
                    case "UNDER":
                        block.draw(g,17);
                        break;
                    case "ABOVE":
                        block.draw(g,16);
                        break;
                    case "LEFT":
                        block.draw(g,10);
                        break;
                    case "RIGHT":
                        block.draw(g,4);
                        break;
                }
            }
            else{
                String nextRelativePosition = block.position.positionRelativeToObject(nextBlock.position);
                String prevRelativePosition = block.position.positionRelativeToObject(prevBlock.position);

                if(nextRelativePosition.equals("ABOVE") && prevRelativePosition.equals("UNDER")
                || prevRelativePosition.equals("ABOVE") && nextRelativePosition.equals("UNDER"))
                {
                    if(i % 2 == 0)
                        block.draw(g, 8);
                    else
                        block.draw(g,14);
                }
                else if(nextRelativePosition.equals("LEFT") && prevRelativePosition.equals("RIGHT")
                        || prevRelativePosition.equals("LEFT") && nextRelativePosition.equals("RIGHT"))
                {
                    if(i % 2 == 0)
                        block.draw(g, 0);
                    else
                        block.draw(g,1);
                }
                else if(nextRelativePosition.equals("ABOVE") && prevRelativePosition.equals("RIGHT")
                        || nextRelativePosition.equals("RIGHT") && prevRelativePosition.equals("ABOVE")){
                    block.draw(g,7);
                }
                else if(nextRelativePosition.equals("ABOVE") && prevRelativePosition.equals("LEFT")
                        || nextRelativePosition.equals("LEFT") && prevRelativePosition.equals("ABOVE")){
                    block.draw(g,6);
                }
                else if(nextRelativePosition.equals("UNDER") && prevRelativePosition.equals("RIGHT")
                        || nextRelativePosition.equals("RIGHT") && prevRelativePosition.equals("UNDER")){
                    block.draw(g,13);
                }
                else if(nextRelativePosition.equals("UNDER") && prevRelativePosition.equals("LEFT")
                        || nextRelativePosition.equals("LEFT") && prevRelativePosition.equals("UNDER")){
                    block.draw(g,12);
                }
            }
        }
    }

    public void move(GameStatus.Direction direction){
        lastTailPosition = body.get(body.size() - 1).position.clone();

        for(int i = body.size()-1; i > 0; i--) {
            body.get(i).position = body.get(i-1).position.clone();
        }

        if(direction == GameStatus.Direction.UP){
            body.get(0).position.y = body.get(0).position.y - GameConfig.UNIT_SIZE;
        }
        else if(direction == GameStatus.Direction.DOWN){
            body.get(0).position.y = body.get(0).position.y + GameConfig.UNIT_SIZE;
        }
        else if(direction == GameStatus.Direction.LEFT){
            body.get(0).position.x = body.get(0).position.x - GameConfig.UNIT_SIZE;
        }
        else if(direction == GameStatus.Direction.RIGHT){
            body.get(0).position.x = body.get(0).position.x + GameConfig.UNIT_SIZE;
        }
    }

    public PositionClass getHeadPosition(){
        return body.get(0).position;
    }

    public void eat(){
        SnakeBlock newBlock = new SnakeBlock(lastTailPosition, false);
        body.add(newBlock);
    }

    public boolean selfEaten(){
        for(SnakeBlock block : body){
            if(block.position.equals(body.get(0).position) && block != body.get(0)){
                return true;
            }
        }
        return false;
    }

}
