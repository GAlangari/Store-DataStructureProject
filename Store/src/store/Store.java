//Ghaida Alangari, 439017383, 374
package store;

public class Store {
    
    /* STORE CLASS FUNCTIONS AND DATA FIELDS SHOULD BE HERE */
    /* AFTER COMPLETING THE REQUIREMENTS ALL ERRORS SHOULD BE RESOLVED */
    
    private Queue<Order> newOrders = new Queue<>();
    private Queue<Order> processedOrders = new Queue<>();
    private Queue<Order> removedOrders = new Queue<>();
    private DLL<Item> items = new DLL<>();
    private double sales = 0.0;           
    
    private Item [] sortedArray;   
    
    public void resetOrders(){
        while (!newOrders.isEmpty())
            newOrders.dequeue();
        while (!processedOrders.isEmpty())
            processedOrders.dequeue();
        while (!removedOrders.isEmpty())
            removedOrders.dequeue();
    }
    public void addNewOrder(Order o){ 
        if(o != null)
            newOrders.enqueue(o); 
    }
    public void processOrder (){
        Order o = newOrders.dequeue();
        if (o != null){
            processedOrders.enqueue(o);
            sales += o.calculateOrederSale();     
        }
    }
    public Order removeOrder(int n){                
        Order res = null;
        Order o;
        int s = newOrders.size();
        for (int i = 0; i < s; i++) {
           o = newOrders.dequeue();
           if( o.orderNumber == n){
               res = o;
               removedOrders.enqueue(o);
            }
            else
               newOrders.enqueue(o);
        }
        
        if(res == null)
            System.out.println("Order not found");
        return res;
    }
    public void printAllOrders(Queue<Order> orders){
        Order o;
        for (int i = 0; i < orders.size(); i++) {
            o = orders.dequeue();
            o.printOrder();
            orders.enqueue(o);
        }
    }
    public double calculateSales(){             
        double s = 0;
        Order o;
        for (int i = 0; i < processedOrders.size(); i++) {
            o = processedOrders.dequeue();
            s += o.calculateOrederSale();
            processedOrders.enqueue(o);
        }
        return s;    
    }
    public void printStoreItems(){
        System.out.println("Store items are: ");
        Node<Item> e = items.getHead();
        while(e !=null){
            System.out.println("\t"+ e.getItem().toString1()); 
            e = e.getNext();
        }
    }
    public void addItemToOrder(Item e, Order o){
        if(inNewOrders(o)){
            o.addItem(e);
            System.out.println("Added successfully, order details: ");
            o.printOrder();
        }
        else
            System.out.println("order can't be modified (not in newOrders)");
    }
    public void removeItemFromOrder(Item e, Order o){
        if(inNewOrders(o)){
            o.removeItem(e.getItemNumber());
            System.out.println("item removed successfully, order detail: ");
            o.printOrder();
        }
        else
            System.out.println("order can't be modified (not in newOrders)");
    }
    
    
    //-------------------------------------------------------------------------- the bonus questions

    public void generateItemsTree(){
        BinaryTree<Item> t = new BinaryTree<>();
        Node<Item> curr = items.getHead();
        if(curr != null)
            t.addRoot(curr.getItem());
        curr = curr.getNext();
        while(curr != null){
            TNode<Item> neww = new TNode<>();
            neww.setItem(curr.getItem());
            generateItemsTreeHelp(t.getRoot(),neww);
            curr = curr.getNext();
        }
        t.draw(t.getRoot());       
    }
    private void generateItemsTreeHelp(TNode<Item> r, TNode<Item> x){     
        Queue<TNode> q =new Queue<>();
        q.enqueue(r);
        
        while(! q.isEmpty()){
            r = q.dequeue();
            if(r.getLeft() == null){
                r.setLeft(x);
                x.setParent(r);
                break;
            }
            else
                q.enqueue(r.getLeft());
            if(r.getRight() == null){
                r.setRight(x);
                x.setParent(r);
                break;
            }
            else
                q.enqueue(r.getRight());
        }
    }
    public void generateItemsBSTree(){
        BST<Integer, Item> t = new BST<>();
        Node<Item> curr = items.getHead();
        while(curr != null){
            t.insert(curr.getItem().getItemNumber(), curr.getItem());
            curr = curr.getNext();
        }
        t.draw(t.getRoot());
        generateSortedArray(t.getRoot());
    }
    public Item binarySearchItems(int target){
         return binarySearchItemsHelp(target, 0, sortedArray.length);
    }
    private Item binarySearchItemsHelp(int target, int low, int high){
        if(low > high)
            return null;
        else {
            int mid = (low + high)/2;
            if(target == sortedArray[mid].getItemNumber())
                return sortedArray[mid];
            else if(target < sortedArray[mid].getItemNumber())
                return binarySearchItemsHelp(target, low, mid-1);
            else
                return binarySearchItemsHelp(target, mid + 1, high);
        }
    }   
    //-------------------------------------------------------------------------- additional methods
    private boolean inNewOrders(Order o){
        boolean f = false;
        Order s;
        for (int i = 0; i < newOrders.size(); i++) {
            s = newOrders.dequeue();
            if(s.equals(o))
                f = true;
            newOrders.enqueue(s);
        }
        return f;
    }
    private void generateSortedArray(BSTNode<Integer,Item> p){
        sortedArray = new Item [items.getSize()];
        generateSortedArrayHelp(p, 0);
        System.out.println("\nSorted items array:");
        for (int i = 0; i < sortedArray.length; i++) {
            System.out.print(sortedArray[i] +" ");
        }
        System.out.println("");
    }    
    private int generateSortedArrayHelp(BSTNode<Integer, Item> p, int i){
        if(p != null){
            if( p.getLeft() != null)
                i = generateSortedArrayHelp(p.getLeft(), i);
            sortedArray[i++] = p.getData();
            if(p.getRight() != null)
                i = generateSortedArrayHelp(p.getRight(), i);
        }
        return i;
    }
    
    
    
    public static void main(String[] args) {
        Store store = new Store();
        
        store.items.addLast(new Item(111,"iPhone X",3750));
        store.items.addLast(new Item(133,"Bose Headphone",1099));
        store.items.addLast(new Item(122,"iPhone X Max",5550));
        store.items.addLast(new Item(231,"Samsung Galaxy Note 10",2500));
        store.items.addLast(new Item(131,"Apple AirPods",599));
                
        Order o4 = new Order(4);
        o4.addItem(new Item(111,"iPhone X",3750));
        o4.addItem(new Item(133,"Bose Headphone",1099));
        store.addNewOrder(o4);
        Order o5 = new Order(5);
        o5.addItem(new Item(122,"iPhone X Max",5550));
        store.addNewOrder(o5);
        Order o1 = new Order(1);
        o1.addItem(new Item(111,"iPhone X",3750));
        o1.addItem(new Item(122,"iPhone X Max",5550));
        o1.addItem(new Item(231,"Samsung Galaxy Note 10",2500));
        store.addNewOrder(o1);
        Order o2 = new Order(2);
        o2.addItem(new Item(122,"iPhone X Max",5550));
        o2.addItem(new Item(131,"Apple AirPods",599));
        store.addNewOrder(o2);
        Order o3 = new Order(3);
        o3.addItem(new Item(231,"Samsung Galaxy Note 10",2500));
        store.addNewOrder(o3);
        
        store.removeOrder(5);
        store.processOrder();
        System.out.println("New:");
        store.printAllOrders(store.newOrders);
        System.out.println("Processed:");
        store.printAllOrders(store.processedOrders);
        System.out.println("Removed:");
        store.printAllOrders(store.removedOrders);
        
        
        
        
        //---------------------------------------------------------------------- 
        System.out.println("\n-----------------------------Methods test-----------------------------");
        System.out.println("\nsales using calculateSales method: "+ store.calculateSales());
        System.out.println("sales using sales attribute: "+ store.sales);
        System.out.println("");
        store.printStoreItems();
        System.out.println("\nOrder details: ");
        System.out.print("\t");
        o4.printOrder();
        
        System.out.println("\nSearching in order #4 by item's number 133: ");
        Item n = o4.searchByNumber(133);
        n.print();
        
        System.out.println("\nSearching in order #4 by item's name iPhone X: ");
        n = o4.searchByName("iPhone X");
        n.print();
        
        System.out.println("\nSearching in order #1 by item's name(substring) iPhone: ");
        n = o1.searchByName("iPhone");
        n.print();
        
        System.out.println("\nSearching in order #3 by item's price 2500: ");
        n = o3.searchByPrice(2500);
        n.print();
        
        System.out.println("\nAdd an item to order #3 in newOrders: ");
        store.addItemToOrder(new Item(122,"iPhone X Max",5550), o3);

        System.out.println("\nAdd an item to order #4 in processedOrders: ");
        store.addItemToOrder(new Item(122,"iPhone X Max",5550), o4);
        
        System.out.println("\nRemoving item #122 from order #3 in newOrders: ");
        store.removeItemFromOrder(new Item(122,"iPhone X Max",5550), o3);
        
        System.out.println("\nRemoving an item from order #4 in processedOrders: ");
        store.removeItemFromOrder(new Item(122,"iPhone X Max",5550), o4);
        
        //-------------------------- bonus test
        System.out.println("\ntree of store items: ");
        store.generateItemsTree();
        
        System.out.println("\nBSTree of store items: ");
        store.generateItemsBSTree();
        
        System.out.println("\nBinary Search for item number 111: ");
        System.out.println(store.binarySearchItems(111).toString1());
        
        System.out.println("\nBinary Search for item number 133:");
        System.out.println(store.binarySearchItems(133).toString1());
        
    }
}
