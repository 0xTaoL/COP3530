public class HuffTree implements Comparable {

    private HuffNode root;

    HuffTree() {
        this(null);
    }

    HuffTree(HuffNode root) {
        this.root = root;
    }

    public HuffNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int getFreq() {
        return this.root.getFreq();
    }

    @Override
    public int compareTo(Object o) {
        HuffTree tree = (HuffTree) o;

        return root.getFreq() - tree.getFreq();
    }
}