import java.util.LinkedList;

public class SparseMatrix implements SparseInterface {

    private int size;
    private LinkedList<matrixNode> matrix;

    //default 5 x 5 matrix
    SparseMatrix() {
        this.size = 5;
        this.matrix = new LinkedList<>();

    }

    //constructor when n value is given for a n by n matrix
    public SparseMatrix(int matrixSize) {
        this.size = matrixSize;
        this.matrix = new LinkedList<>();
    }

    //clears matrix using the built in linkedlist function
    @Override
    public void clear() {
        matrix.clear();
    }

    //sets size of the matrix
    @Override
    public void setSize(int size) {
        this.size = size;
        matrix.clear();
    }

    //addes an element to matrix
    @Override
    public void addElement(int row, int col, int data) {
        //checks if in bounds
        if ((row > size - 1) || (col > size - 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + size + " by " + size +" matrix. Nothing has been added.");
        }
        else {
            //makes it so that nodes are put in order from left to right top to bottom
            //printing is easier with this method
            int index = 0;
            for(matrixNode i : matrix){
                if((i.getRow() == row) && (i.getCol() == col)) {
                    System.err.println("Error: A value already exists here! Nothing has been added.");
                    return;
                }
                if(row > i.getRow()){
                    index++;
                }
                if(row == i.getRow()){
                    if(col > i.getCol()){
                        index++;
                    }
                }
            }
            //add node at the given index with the given values
            matrix.add(index,new matrixNode(row, col, data));
        }
    }

    @Override
    public void removeElement(int row, int col) {
        //checks if in bounds
        if ((row > size - 1) || (col > size - 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + size + " by " + size +" matrix. Nothing has been removed.");
        }
        else {
            //if the if statement is never fulfilled then the value already was zero, thus no further coding needed.
            for (matrixNode i : matrix) {
                if (row == i.getRow() && col == i.getCol()) {
                    matrix.remove(i);
                }
            }
        }
    }

    @Override
    public int getElement(int row, int col) {
        //checks if in bounds
        if ((row > size- 1) || (col > size- 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + size + " by " + size + " matrix.");
            return -1;
        } 
        else {
            //iterates through the linked list until value is found
            for (matrixNode i : matrix) {
                if (row == i.getRow() && col == i.getCol()) {
                    return i.getValue();
                }
            }
            //if can't find a node with these coordinates then by default it is zero
            //already checked that it is within the matrix with the initial if statement
            return 0;
        }
    }

    @Override
    public int determinant() {
        //base cases
        if (size == 1) {
            return (getElement(0,0));
        } 
        else if (size == 2) {
            return (getElement(0,0) * getElement(1,1) - getElement(0,1) * getElement(1,0));
        }

        //recursion
        int determinant = 0;
        for (int i = 0; i < size; i++) {
            determinant += Math.pow(-1,i) * getElement(0,i) * minor(0,i).determinant();
        }
        return determinant;
    }

    @Override
    public SparseInterface minor(int row, int col) {
        //checks if in bounds
        if ((row > size-1) || (col > size-1) || (col < 0) || (row <0)){
            System.err.println("Error: Not within " + size + " by " + size +" matrix. Failed to generate minor.");
            return null;
        }
        else {
            //creates new matrix
            SparseMatrix minor = new SparseMatrix(size-1);
            for (matrixNode i : matrix) {
                //adds elements in in the given row or column, while taking into account that col and row values need to be shifted
                if ((i.getRow() != row) && (i.getCol() != col)) {
                    if((i.getRow() > row) && (i.getCol() > col)){
                        minor.addElement(i.getRow()-1,i.getCol()-1,i.getValue());
                    }
                    else if (i.getRow() > row){
                        minor.addElement(i.getRow()-1,i.getCol(),i.getValue());
                    }
                    else if (i.getCol() > col){
                        minor.addElement(i.getRow(),i.getCol()-1,i.getValue());
                    }
                    else{
                        minor.addElement(i.getRow(),i.getCol(),i.getValue());
                    }
                }
            }
            return minor;
        }
    }

    @Override
    public String toString() {
        //creates requested string using stringbuilder by iterating through the matrix and appending for each element
        StringBuilder matrixElements = new StringBuilder();
        for (matrixNode i : matrix) {
            matrixElements.append(i.getRow()).append(" ").append(i.getCol()).append(" ").append(i.getValue()).append("\n");
        }
        return matrixElements.toString();
    }

    @Override
    public int getSize() {
        //returns size of matrix
        return this.size;
    }
}