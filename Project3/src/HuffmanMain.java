import java.io.File;

public class HuffmanMain {
    public static void main(String[] args) {



        try {
            // Please change test to an instance of your class that implements the HuffmanCoding interface
            HuffmanEncoder test = new HuffmanEncoder();

            // Make sure the sample.txt is in the same folder as this class, it should be in your project folder
            File file = new File("./illiad.txt");

            System.out.println("getFrequencies: " + test.getFrequencies(file));

            // I can't think of a better way to test this other than to pass it into the encode
            HuffTree tree = test.buildTree(file);


            String e = test.encodeFile(file, tree);

            System.out.println("encodeFile: " + e);

            System.out.println("decodeFile: " + test.decodeFile(e, tree));

            String traverseTest = "  01\n* 10011\n@ 10010\nB 000\nU 110\ni 1000\nk 111\nn 101\nw 001\n";
            System.out.println("traverseHuffmanTree: " + test.traverseHuffmanTree(tree));



            HuffTree badtree = new HuffTree();
            test.encodeFile(file, badtree);




        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
