//Ghaida Alangari, 439017383, 374
package store;
 
public class Order {
    /* COMPLETE ORDER CLASS */
    int orderNumber;
    DLL<Item> items = new DLL<>();

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    
    public void addItem(Item e){
        items.addLast(e);
    }
    public Item removeItem(int n){
        Node<Item> p = searchByNumberNode(n);
        Item e = null;
        if(p != null){
            e = p.getItem();
            Node<Item> prev = p.getPrev();
            if(p.getNext() != null)
                p.getNext().setPrev(prev);
            prev.setNext(p.getNext());
            items.setSize(items.getSize()-1);
        }
        return e;
    }
    public Item searchByNumber(int n){
        Item e = null;
        Node<Item> p = items.getHead();
        while (p != null){
            if (p.getItem().getItemNumber() == n) {     
                e = p.getItem();
                return e;
            } 
            p = p.getNext();
        }
        return e;
    }
       public Node<Item> searchByNumberNode(int n){
        Node<Item> p = items.getHead();
        while (p != null){
            if (p.getItem().getItemNumber() == n) {     
                return p;
            } 
            p = p.getNext();
        }
       return null;
    }
    public Item searchByName(String n){    
        Item e = null;
        Node<Item> p = items.getHead();
        while (p != null){
            String s = p.getItem().getItemName();
            if (s.contains(n)) {     
                e = p.getItem();
                return e;
            }  
            p = p.getNext();
        }
        return e;
    }
    
    public Item searchByPrice(int n){
        Item e = null;
        Node<Item> p = items.getHead();
        while (p != null){
            if (p.getItem().getItemPrice() == n) {     
                e = p.getItem();
                return e;
            } 
            p = p.getNext();
        }
        return e;
    }
    public void printOrder(){
        System.out.printf("Order #%d\n",orderNumber);
        Node<Item> cu = items.getHead();
        int i = 1;
        while(cu !=null){
            System.out.println("\tItem #"+i);
            cu.getItem().print();
            cu = cu.getNext();
            i++;
        }
    }
    //------------------------------------------------------------------ helping method
    public double calculateOrederSale(){
        Node<Item> cu = items.getHead();
        double sum = 0;
        while (cu != null){
            sum += cu.getItem().getItemPrice();
            cu = cu.getNext();
        }
        return sum;
    }
}
