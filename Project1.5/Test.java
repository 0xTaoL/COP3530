public class Test {



    public static void main(String[] args){
        SparseInterface three = new SparseMatrix();

        three.setSize(3, 3);

        SparseInterface add = new SparseMatrix();

        add.setSize(3, 4);

        SparseInterface mult = new SparseMatrix();

        mult.setSize(3, 4);

        System.out.println("Num Rows is 3: " + (three.getNumRows() == 3));
        System.out.println("Num Cols is 3: " + (three.getNumCols() == 3));
        System.out.println("Add violation detection: " + three.addMatrices(mult));
        System.out.println("Multiply violation detection: " + three.multiplyMatrices(mult));

        three.addElement(0, 0, 16);

        three.addElement(0, 1, 4);

        three.removeElement(0,1);

        String correctString = "0 0 16\n";

        System.out.println("toString is correct: " + correctString.equals(three.toString()));

        three.removeElement(0,0);


        three.addElement(2,2,4);

        three.addElement(1,0,-3);

        correctString = "1 0 -3\n2 2 4\n";

        System.out.println("toString is correct: " + correctString.equals(three.toString()));


        three.removeElement(2, 2);

        three.removeElement(1, 0);

        three.addElement(0, 0, 0);

        correctString = "";

        //Because we are not storing 0 values in the matrix the toString should reflect an "empty" (all 0) matrix.
        System.out.println("toString is correct: " + correctString.equals(three.toString()));

        three.addElement(0, 1, 3);

        three.addElement(0, 1, 0);

        correctString = "";

        //Note that adding 0 to the matrix overwrites the data at that position to 0 as defined in the interface description
        //Because we are not storing 0 values, we can remove the element at that position.
        System.out.println("toString is correct: " + correctString.equals(three.toString()));

        three.addElement(0, 0, 16);
        three.addElement(0, 1, 4);
        three.addElement(1, 1, 9);
        three.addElement(2, 2, 7);

        System.out.println("three \n" + three.toString());

        SparseInterface addTest1 = new SparseMatrix();
        addTest1.setSize(300000000, 300000000);
        addTest1.addElement(0, 0, 1);
        addTest1.addElement(1, 0, 6);
        addTest1.addElement(1, 1, 2);

        System.out.println("AddTest1");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + addTest1.getElement(i, j));
            }
            System.out.println();
        }

        SparseInterface addTest2 = new SparseMatrix();
        addTest2.setSize(300000000, 300000000);
        addTest2.addElement(0, 0, 3);
        addTest2.addElement(0, 0, 2);
        addTest2.addElement(0, 0, 1);
        addTest2.addElement(0, 1, 1);
        addTest2.addElement(2, 2, 4);

        System.out.println("AddTest2");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + addTest2.getElement(i, j));
            }
            System.out.println();
        }

        SparseInterface addTest3 = addTest1.addMatrices(addTest2);

        System.out.println("Add result");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + addTest3.getElement(i, j));
            }
            System.out.println();
        }

        SparseInterface multiplyTest1 = new SparseMatrix();
        multiplyTest1.setSize(3, 3);
        multiplyTest1.addElement(0, 0, 1);
        multiplyTest1.addElement(0, 1, 2);
        multiplyTest1.addElement(0, 2, 3);
        multiplyTest1.addElement(1, 0, 4);
        multiplyTest1.addElement(1, 1, 5);
        multiplyTest1.addElement(1, 2, 6);
        multiplyTest1.addElement(2, 0, 7);
        multiplyTest1.addElement(2, 1, 8);
        multiplyTest1.addElement(2, 2, 9);

        System.out.println("MultiplyTest1");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + multiplyTest1.getElement(i, j));
            }
            System.out.println();
        }

        SparseInterface multiplyTest2 = new SparseMatrix();
        multiplyTest2.setSize(3, 3);
        multiplyTest2.addElement(0, 0, 1);
        multiplyTest2.addElement(0, 1, -1);
        multiplyTest2.addElement(0, 2, 3);

        multiplyTest2.addElement(1, 0, -2);
        multiplyTest2.addElement(1, 1, 4);
        multiplyTest2.addElement(1, 2, -1);

        multiplyTest2.addElement(2, 0, 1);
        multiplyTest2.addElement(2, 1, -3);
        multiplyTest2.addElement(2, 2, 2);

        System.out.println("MultiplyTest2");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + multiplyTest2.getElement(i, j));
            }
            System.out.println();
        }

        SparseInterface multiplyTest3 = multiplyTest1.multiplyMatrices(multiplyTest2);

        System.out.println("MultiplyTest3");
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + multiplyTest3.getElement(i, j));
            }
            System.out.println();
        }

    }
}
