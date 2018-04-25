import java.util.LinkedList;

public class SparseMatrix implements SparseInterface{

    private int rows;
    private int cols;
    private LinkedList<matrixNode> matrix;

    SparseMatrix() {
        this.matrix = new LinkedList<>();
        this.rows = 5;
        this.cols = 5;
    }

    public void clear() {
        matrix.clear();
    }

    public void setSize(int numRows, int numCols) {
        this.rows = numRows;
        this.cols = numCols;
        matrix.clear();
    }

    public int getNumRows() {
        return this.rows;
    }

    public int getNumCols() {
        return this.cols;
    }

    public void addElement(int row, int col, int data) {
        if ((row > rows - 1) || (col > cols - 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + (rows-1) + " by " + (cols-1) +" matrix. Nothing has been added.");
            return;
        }
        if (data == 0){
            removeElement(row, col);
        }
        else {
            //makes it so that nodes are put in order from left to right top to bottom
            //printing is easier with this method
            int index = 0;
            //remove existing entry if there is one
            matrix.removeIf(matrix -> row == matrix.getRow() && col == matrix.getCol());
            //calculates index
            for(matrixNode i : matrix){
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

    public void removeElement(int row, int col) {
        //checks if in bounds
        if ((row > rows - 1) || (col > cols - 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + (rows-1) + " by " + (cols-1) +" matrix. Nothing has been removed.");
        }
        else {
            //if the if statement is never fulfilled then the value already was zero, thus no further coding needed.
            matrix.removeIf(matrix -> row == matrix.getRow() && col == matrix.getCol());

        }
    }

    public int getElement(int row, int col) {
        //checks if in bounds
        if ((row > rows - 1) || (col > cols - 1) || (col < 0) || (row < 0)) {
            System.err.println("Error: Not within " + (rows-1) + " by " + (cols-1) +" matrix. Nothing has been removed.");
            throw new java.lang.Error();
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

    public String toString() {
        //creates requested string using stringbuilder by iterating through the matrix and appending for each element
        StringBuilder matrixElements = new StringBuilder();
        for (matrixNode i : matrix) {
            matrixElements.append(i.getRow()).append(" ").append(i.getCol()).append(" ").append(i.getValue()).append("\n");
        }
        return matrixElements.toString();
    }

    public SparseInterface addMatrices(SparseInterface matrixToAdd) {

        if((matrixToAdd.getNumCols() != getNumCols()) || (matrixToAdd.getNumRows() != getNumRows())){
            return null;
        }

        LinkedList<matrixNode> matrixB = ((SparseMatrix)matrixToAdd).matrix;

        SparseInterface summedMatrix = new SparseMatrix();
        summedMatrix.setSize(getNumRows(), getNumCols());

        int a = 0, b = 0;
        while (a < matrix.size() && b < matrixB.size()) {

            // if b's row and col is smaller
            if (matrix.get(a).getRow() > matrixB.get(b).getRow() ||
                    (matrix.get(a).getRow() == matrixB.get(b).getRow() &&
                            matrix.get(a).getCol() > matrixB.get(b).getCol()))

            {

                // insert smaller value into result
                summedMatrix.addElement(matrixB.get(b).getRow(),
                        matrixB.get(b).getCol(),
                        matrixB.get(b).getValue());

                b++;
            }

            // if a's row and col is smaller
            else if (matrix.get(a).getRow() < matrixB.get(b).getRow() ||
                    (matrix.get(a).getRow() == matrixB.get(b).getRow() &&
                            matrix.get(a).getCol() < matrixB.get(b).getCol()))

            {

                // insert smaller value into result
                summedMatrix.addElement(matrix.get(a).getRow(),
                        matrix.get(a).getCol(),
                        matrix.get(a).getValue());

                a++;
            }

            else {

                // add the values as row and col is same
                int addedval = matrix.get(a).getValue() + matrixB.get(b).getValue();

                if (addedval != 0)
                    summedMatrix.addElement(matrix.get(a).getRow(),
                            matrix.get(a).getCol(),
                            addedval);
                // then insert
                a++;
                b++;
            }
        }

        // insert remaining elements
        while (a < matrix.size()) {
            summedMatrix.addElement(matrix.get(a).getRow(), matrix.get(a).getCol(), matrix.get(a++).getValue());
        }

        while (b < matrixB.size()) {
            summedMatrix.addElement(matrixB.get(b).getRow(), matrixB.get(b).getCol(), matrixB.get(b++).getValue());
        }

        return summedMatrix;
    }

    public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {

        if (this.getNumCols() != matrixToMultiply.getNumRows()){
            return null;
        }

        LinkedList<matrixNode> matrixB = ((SparseMatrix)matrixToMultiply).matrix;

        SparseInterface multipliedMatrix = new SparseMatrix();
        multipliedMatrix.setSize(this.getNumRows(), matrixToMultiply.getNumCols());

        for (matrixNode MatrixA : this.matrix) {
            for (matrixNode MatrixB : matrixB) {
                if (MatrixA.getCol() == MatrixB.getRow()) {
                    int current = multipliedMatrix.getElement(MatrixA.getRow(), MatrixB.getCol());
                    int addToCurrent = MatrixA.getValue() * MatrixB.getValue();
                    multipliedMatrix.addElement(MatrixA.getRow(), MatrixB.getCol(), current + addToCurrent);
                }
            }
        }
        return multipliedMatrix;
    }
}