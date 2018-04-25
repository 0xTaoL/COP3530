public class matrixNode {

    private int row;
    private int col;
    private int value;

    public matrixNode (int row, int col, int value){
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getValue() {
        return this.value;
    }
}
