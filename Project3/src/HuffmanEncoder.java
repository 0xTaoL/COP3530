import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanEncoder implements HuffmanCoding{

    public String getFrequencies(File inputFile) {
        StringBuilder freqString = new StringBuilder();
        int[] freqTable = readFrequencies(inputFile);

        for (int i = 0; i < freqTable.length; i++ ){
            if(freqTable[i] != 0) {
                freqString.append((char) (i)).append(" ").append(freqTable[i]).append("\n");
            }
        }
        return freqString.toString();
    }

    private int[] readFrequencies(File inputFile){

        int[] freqTable = new int[255];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            int asciiValue;

            while ((asciiValue = reader.read())!=-1){
                char character = (char) asciiValue;
                freqTable[character]++;
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.err.println("File not found.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return freqTable;
    }

    public HuffTree buildTree(File inputFile) {
        PriorityQueue<HuffTree> heap = new PriorityQueue<>();
        int[] freqTable = readFrequencies(inputFile);
        HuffTree tree;

        for(int i = 0; i < freqTable.length; i++){
            if(freqTable[i] > 0){
                HuffNode node = new HuffNode((char) i, freqTable[i]);
                heap.add(new HuffTree(node));
            }
        }

        HuffTree tmp1, tmp2;

        while (heap.size() > 1){
            tmp1 = heap.poll();
            tmp2 = heap.poll();
            assert tmp1 != null;
            assert tmp2 != null;
            HuffNode nodeToAdd = new HuffNode(tmp1.getRoot(), tmp2.getRoot());
            heap.add(new HuffTree(nodeToAdd));
        }

        tree = heap.poll();

        return tree;
    }

    public String encodeFile(File inputFile, HuffTree huffTree) {
        String[] table = new String[255];
        StringBuilder encode = new StringBuilder();

        createCodeIndex(table, huffTree.getRoot(), "");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            int asciiValue;

            while ((asciiValue = reader.read()) != -1) {
                char c = (char) asciiValue;
                encode.append(table[c]);
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found.");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return encode.toString();
    }

    private void createCodeIndex(String[] table, HuffNode node, String encoded) {
        if (node != null) {
            //base case
            if (node.isLeaf()) {
                table[node.getChar()] = encoded;
            }
            //recursive case
            else {
                createCodeIndex(table, node.getLeft(), encoded + "0"); //Go down left
                createCodeIndex(table, node.getRight(), encoded + "1"); //Go down right
            }
        }
    }

    public String decodeFile(String code, HuffTree huffTree) {
        StringBuilder decoded = new StringBuilder();
        HuffNode head = huffTree.getRoot();
        HuffNode tempNode = huffTree.getRoot();

        for(int i = 0; i < code.length(); i++){
            char temp = code.charAt(i);

            if(temp == '0'){
                tempNode = tempNode.getLeft();
                if(tempNode.isLeaf()){
                    decoded.append(tempNode.getChar());
                    tempNode = head;
                }
            }
            if(temp == '1'){
                tempNode = tempNode.getRight();
                if(tempNode.isLeaf()){
                    decoded.append(tempNode.getChar());
                    tempNode = head;
                }
            }
        }
        return decoded.toString();
    }

    public String traverseHuffmanTree(HuffTree huffTree) {
        String[] table = new String[255];
        StringBuilder traversed= new StringBuilder();
        createCodeIndex(table, huffTree.getRoot(), "");

        for (int i = 0; i < table.length; i++ ){
            if(table[i] != null) {
                traversed.append((char) (i)).append(" ").append(table[i]).append("\n");
            }
        }
        return traversed.toString();
    }
}