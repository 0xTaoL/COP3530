public class HuffNode {

    private HuffNode left;
    private HuffNode right;
    private int freq;
    private char ch;

    HuffNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    HuffNode(HuffNode l, HuffNode r){
        this.left = l;
        this.right = r;
        this.freq = l.getFreq() + r.getFreq();
    }

    public char getChar() {
        return ch;
    }

    public boolean isLeaf() {
        return left == null || right == null;
    }

    public int getFreq() {
        return freq;
    }

    public void setLeft(HuffNode left) {
        this.left = left;
    }

    public void setRight(HuffNode right) {
        this.right = right;
    }

    public HuffNode getLeft() {
        return left;
    }

    public HuffNode getRight() {
        return right;
    }
}

