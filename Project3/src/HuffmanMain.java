import java.io.File;

public class HuffmanMain {
    public static void main(String[] args) {



        try {
            // Please change test to an instance of your class that implements the HuffmanCoding interface
            HuffmanEncoder test = new HuffmanEncoder();

            // Make sure the sample.txt is in the same folder as this class, it should be in your project folder
            File file = new File("./sample.txt");

            System.out.println("getFrequencies: " + test.getFrequencies(file));

            // I can't think of a better way to test this other than to pass it into the encode
            HuffTree tree = test.buildTree(file);


            String encodeTest = "011011000010110111111110011101010000010011000000011110011111111101101100110010111101011001011010011000000110010001011011100010000110000110100010010000110000101101001011010011011110110011011101000010000011101111100001110100110001000100111110111010111101001101100111111011010101010001000101100010011010001100100101011001010001111110000100100010000011110101101101010001110110000010010010100010011011010011001101110111111001011000111100101101111111101000000110111011100100011001010101010010001010111001111";
            System.out.println("encodeFile: " + encodeTest.equals(test.encodeFile(file, tree)));

            String decodeTest = "=a=az=a=aaaaaaaQaa==wQa_aaaaQaaaaaaaa=a=aQaQ=aaa==aQ=a=QaGaQ1=a=aa1zaza=1Qzaz=a=Q=a=Qa=aaa=aQa=aa=zwaa=aaaazaa=Qa11Qaaaa=aa==aaa=Qa=aQaaaaa=a====11=a1Qa=1aQQ==aQ=1aaaaazQ1waaa==a=a==1aa=awQQ=1Qa=a=QaQa=aa=aaaaaQ=a1aaaQ=a=aaaaaaa=Ga=aa=aaQ1aQ====Q1==aaQaaa";
            System.out.println("decodeFile: " + decodeTest.equals(test.decodeFile(encodeTest, tree)));

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
