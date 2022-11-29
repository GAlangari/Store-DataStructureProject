//Ghaida Alangari, 439017383, 374
package store;

public class BST<Key extends Comparable<? super Key>, T> {

    private BSTNode<Key, T> root; // Root of the BST
    int size; // Number of nodes in the BST

    BST() {
        root = null;
        size = 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * @return The number of records in the dictionary.
     */
    public int size() {
        return size;
    }

    /**
     * Insert a record into the tree.
     *
     * @param k Key value of the record.
     * @param e The record to insert.
     */
    
    
    public void insert(Key k, T e) {
        root = inserthelp(root, k, e);
        size++;
    }

    /**
     * @return The current subtree, modified to contain the new item
     */
    private BSTNode<Key, T> inserthelp(BSTNode<Key, T> rt, Key k, T e) {
        if (rt == null) {
            return new BSTNode<Key, T>(k, e);
        }
        if (rt.getKey().compareTo(k) > 0) {
            rt.setLeft(inserthelp(rt.getLeft(), k, e));
        } else {
            rt.setRight(inserthelp(rt.getRight(), k, e));
        }
        return rt;
    }

    public T find(Key k) {
        return findhelp(root, k);
    }

    private T findhelp(BSTNode<Key, T> rt, Key k) {
        if (rt == null) {
            return null;
        }
        if (rt.getKey().compareTo(k) > 0) {
            return findhelp(rt.getLeft(), k);
        } else if (rt.getKey().compareTo(k) == 0) {
            return rt.getData();
        } else {
            return findhelp(rt.getRight(), k);
        }
    }

    private BSTNode<Key, T> getmin(BSTNode<Key, T> rt) {
        if (rt.getLeft() == null) {
            return rt;
        }
        return getmin(rt.getLeft());
    }

    private BSTNode<Key, T> deletemin(BSTNode<Key, T> rt) {
        if (rt.getLeft() == null) {
            return rt.getRight();
        }

        rt.setLeft(deletemin(rt.getLeft()));
        return rt;
    }

    public T remove(Key k) {
        T temp = findhelp(root, k);   // First find it
        if (temp != null) {
            root = removehelp(root, k); // Now remove it
            size--;
        }
        return temp;
    }

    private BSTNode<Key, T> removehelp(BSTNode<Key, T> rt, Key k) {
        if (rt == null) {
            return null;
        }
        if (rt.getKey().compareTo(k) > 0) {
            rt.setLeft(removehelp(rt.getLeft(), k));
        } else if (rt.getKey().compareTo(k) < 0) {
            rt.setRight(removehelp(rt.getRight(), k));
        } else { // Found it 
            if (rt.getLeft() == null) {
                return rt.getRight();
            } else if (rt.getRight() == null ) {
                return rt.getLeft();
            } else { // Two children
                BSTNode<Key, T> temp = getmin(rt.getRight());
                rt.setData(temp.getData());
                rt.setKey(temp.getKey());
                rt.setRight(deletemin(rt.getRight()));
            }
        }
        return rt;
    }

    public BSTNode getRoot() {
        return root;
    }

    public boolean isInternal(BSTNode t) {
        if (t != null) {
            if (t.getLeft() != null) {
                return true;
            }
            if (t.getRight() != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void inOrder(BSTNode p) {
        if (p != null) {
            inOrder(p.getLeft());
                System.out.print(p.getData() + " ");
            inOrder(p.getRight());
        }
    }
        public void draw(BSTNode<Key, T> r) {
        if (r != null) {
            int x, y;
            int i = 0, j = 0;
            System.out.print("   ");
            depth(r, 0);
            int m = rank(r, 0);
            for (int k = 0; k <= m; k++) {
                System.out.printf("%3d", k);
            }
            System.out.println();
            System.out.printf("%3d", i);
            Queue<BSTNode> q = new Queue<>();
            q.enqueue(r);
            while (!q.isEmpty()) {
                BSTNode p = q.dequeue();
                if (p != null) {
                    x = p.getRank();
                    y = p.getDepth();
                    while (y > i) {
                        for (; j <= m; j++) {
                            System.out.printf("...");
                        }
                        System.out.println();
                        i++;
                        j = 0;
                        System.out.printf("%3d", i);
                    }
                    while (x > j - 1) {
                        System.out.print("...");
                        j++;
                    }
                    System.out.print(String.format("%3s", p.getData()).replace(' ', '.'));
                    j++;
                    if (p.getLeft() != null) {
                        q.enqueue(p.getLeft());
                    }
                    if (p.getRight() != null) {
                        q.enqueue(p.getRight());
                    }
                }
            }
            for (; j <= m; j++) {
                System.out.printf("...");
            }
            System.out.println();
        }
    }

    public void depth(BSTNode<Key, T> n, int d) {
        if (n != null) {
            n.setDepth(d);
            if (n.getLeft() != null) {
                depth(n.getLeft(), d + 1);
            }
            if (n.getRight() != null) {
                depth(n.getRight(), d + 1);
            }
        }
    }

    public int rank(BSTNode<Key, T> n, int r) {
        if (n != null) {
            if (n.getLeft() != null) {
                r = rank(n.getLeft(), r);
            }
            n.setRank(r++);
            if (n.getRight() != null) {
                r = rank(n.getRight(), r);
            }
            return r;
        }
        return r;
    }

} // class

