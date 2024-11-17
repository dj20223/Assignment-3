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

class RedBlackTree {
    private RedBlackTreeNode root;
    private final RedBlackTreeNode nil;

    public RedBlackTree() {
        this.nil = new RedBlackTreeNode(-1, null, null, 0);
        this.nil.isRed = false;
        this.root = this.nil;
    }

    public void insert(int key, String name, String category, double price) {
        RedBlackTreeNode newNode = new RedBlackTreeNode(key, name, category, price);
        newNode.left = newNode.right = newNode.parent = nil;

        RedBlackTreeNode parent = nil;
        RedBlackTreeNode current = root;

        // Standard BST insertion
        while (current != nil) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                System.out.println("Error: Product with ID " + key + " already exists.");
                return;
            }
        }

        newNode.parent = parent;
        if (parent == nil) {
            root = newNode; // Tree was empty
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // Fix tree balance
        insertFix(newNode);
    }

    private void insertFix(RedBlackTreeNode node) {
        while (node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                RedBlackTreeNode uncle = node.parent.parent.right;
                if (uncle.isRed) { // Case 1
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) { // Case 2
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isRed = false; // Case 3
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                RedBlackTreeNode uncle = node.parent.parent.left;
                if (uncle.isRed) { // Symmetric to Case 1
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) { // Symmetric to Case 2
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isRed = false; // Symmetric to Case 3
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    private void leftRotate(RedBlackTreeNode node) {
        RedBlackTreeNode temp = node.right;
        node.right = temp.left;
        if (temp.left != nil) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == nil) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    private void rightRotate(RedBlackTreeNode node) {
        RedBlackTreeNode temp = node.left;
        node.left = temp.right;
        if (temp.right != nil) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == nil) {
            root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }

    public RedBlackTreeNode search(int key) {
        RedBlackTreeNode current = root;
        while (current != nil && key != current.key) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current != nil ? current : null;
    }

    public void displayNode(RedBlackTreeNode node) {
        if (node != null) {
            System.out.println("ID: " + node.key + ", Name: " + node.name +
                               ", Category: " + node.category + ", Price: " + node.price);
        } else {
            System.out.println("Product not found.");
        }
    }
}
