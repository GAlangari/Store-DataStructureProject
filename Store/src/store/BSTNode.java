//Ghaida Alangari, 439017383, 374
package store;

class BSTNode<Key, T> {

    private Key key;
    private T data;
    private BSTNode<Key, T> left;
    private BSTNode<Key, T> right;
    private int depth;
    private int rank;

    public BSTNode() {
        left = right = null;
        depth = -1;
        rank = -1;
    }

    public BSTNode(Key key, T data) {
        this.key = key;
        this.data = data;
        left = right = null;
        depth = -1;
        rank = -1;
    }

    public BSTNode(Key key, T data, BSTNode<Key, T> left, BSTNode<Key, T> right) {
        this.key = key;
        this.data = data;
        this.left = left;
        this.right = right;

        depth = -1;
        rank = -1;
    }

    /**
     * Get and set the key value
     */
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Get and set the data value
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get and set the left child
     */
    public BSTNode<Key, T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<Key, T> left) {
        this.left = left;
    }

    /**
     * Get and set the right child
     */
    public BSTNode<Key, T> getRight() {
        return right;
    }

    public void setRight(BSTNode<Key, T> right) {
        this.right = right;
    }

    /**
     * @return True if a leaf node, false otherwise
     */
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public void setDepth(int d) {
        depth = d;
    }

    public void setRank(int r) {
        rank = r;
    }

    public int getDepth() {
        return depth;
    }

    public int getRank() {
        return rank;
    }

}