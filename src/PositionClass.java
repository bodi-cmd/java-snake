public class PositionClass{
    int x,y;
    public PositionClass(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PositionClass clone(){
        return new PositionClass(this.x,this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionClass that = (PositionClass) o;
        return x == that.x && y == that.y;
    }

    public String positionRelativeToObject(PositionClass position){
        if(position == null)
            return "NULL_POSITION_ERR";

        if(this.x == position.x){
            if(this.y > position.y){
                return "UNDER";
            }
            if(this.y < position.y){
                return "ABOVE";
            }
        }
        if(this.y == position.y){
            if(this.x < position.x){
                return "LEFT";
            }
            if(this.x > position.x){
                return "RIGHT";
            }
        }
        return "NOT_ON_SAME_AXIS";
    }

    @Override
    public String toString() {
        return "PositionClass{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
