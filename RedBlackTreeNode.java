class RedBlackTreeNode {
    int key;
    String name;
    String category;
    double price;
    RedBlackTreeNode left, right, parent;
    boolean isRed;

    public RedBlackTreeNode(int key, String name, String category, double price) {
        this.key = key;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isRed = true; // New nodes are initially red
        this.left = this.right = this.parent = null;
    }
}
