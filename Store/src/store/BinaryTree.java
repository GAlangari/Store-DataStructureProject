//Ghaida Alangari, 439017383, 374
package store;

public class BinaryTree<E> {

    private TNode<E> root;
    public int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public TNode<E> getRoot() {
        return root;
    }

    public TNode<E> addRoot(E e) {
        if (root == null) {
            root = new TNode<E>();
            root.setItem(e);
            size++;
        }
        return root;
    }
   
public TNode<E> parent(TNode<E> p) {
        if (p != null) {
            TNode<E> n = p.getParent();
            return n;
        }
        return null;
    }

    public TNode<E> left(TNode<E> p) {
        if (p != null) {
            TNode<E> n = p.getLeft();
            return n;
        }
        return null;
    }

    public TNode<E> right(TNode<E> p) {
        if (p != null) {
            TNode<E> n = p.getRight();
            return n;
        }
        return null;
    }

 
public boolean isRoot(TNode<E> p) {
        if (p != null) {
            if (p.getParent() == null) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

 

  
public TNode<E> addLeft(TNode<E> p, E e) {
        if (p != null && p.getLeft() == null) {
            TNode<E> n = new TNode<E>();
            n.setItem(e);
            p.setLeft(n);
            n.setParent(p);
            size++;
            return n;
        } else {
            return null;
        }
    }

    public TNode<E> addRight(TNode<E> p, E e) {
        if (p != null && p.getRight() == null) {
            TNode<E> n = new TNode<E>();
            n.setItem(e);
            p.setRight(n);
            n.setParent(p);
            size++;
            return n;
        } else {
            return null;
        }
    }

  
public boolean isInternal(TNode<E> p) {
        if (p != null) {
            if (p.getLeft() != null || p.getRight() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isExternal(TNode<E> p) {
        if (p != null) {
            if (p.getLeft() == null && p.getRight() == null) {
                return true;
            }
        }
        return false;
    }
    
    public void inOrder(TNode<E> p) {
	if (p != null) {
            inOrder(left(p));
            System.out.print(p.getItem());
            inOrder(right(p));
	}
    }
    
    public void preOrder(TNode<E> p) {
		if (p != null) {
			System.out.println(p.getItem());
			preOrder(left(p));
			preOrder(right(p));
		}
	}

	public int depth(TNode<E> p) {
		if (p != null) {
			TNode<E> n = p.getParent();
			int d = 0;
			while (n != null) {
				d++;
				n = n.getParent();
			}
			return d;
		}
		return -1;
	}

	public int height(TNode<E> p) {
		if (p != null) {
			if (p.getLeft() == null && p.getRight() == null) {
				return 0;
			}
			else {
				int hl = height(p.getLeft());
				int hr = height(p.getRight());
				if (hl >= hr)
					return hl + 1;
				else 
					return hr + 1;
			}
		}
		return 0;
	}


        
        
//Below methods used to print BT
	public int layout(TNode<E> p, int d, int x) {
		if (p != null) {
			if (left(p) != null)
				x = layout(left(p), d + 1, x);
			p.setX(x++);
			p.setY(d);
			if (right(p) != null)
				x = layout(right(p), d + 1, x);
			return x;
		}
		return x;
	}

	public void draw(TNode<E> p) {
		if (p != null) {
			int x, y;
			int i = 0, j = 0;
			System.out.print("   ");
			int m = layout(p, 0, 0);
			for (int k = 0; k <= m; k++) {
				System.out.printf("%3d", k);
			}
			System.out.println();
			System.out.printf("%3d", i);
			TQueue<TNode<E>> tq = new TQueue<TNode<E>>();
			tq.enqueue(p);
			while (!tq.isEmpty()) {
				TNode<E> n = tq.dequeue();
				if (n != null) {
					x = n.getX();
					y = n.getY();
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
					System.out.print(String.format("%3s", n.getItem()).replace(' ', '.'));
					j++;
					if (n.getLeft() != null) {
						tq.enqueue(n.getLeft());
					}
					if (n.getRight() != null) {
						tq.enqueue(n.getRight());
					}
				}
			}
			for (; j <= m; j++) {
				System.out.printf("...");
			}
			System.out.println();
		}
	
	}
}
