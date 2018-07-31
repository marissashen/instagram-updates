import javafoundations.LinkedQueue;
import javafoundations.Queue;
import java.util.Vector;
/**
* GroceryStore.java Maintains queues of shoppers to represent the checkout lanes at
* a grocery store. 
*/
public class GroceryStore{
  //Layout of the grocery store
  private static final int NUM_REGISTERS = 3;
  private Vector<LinkedQueue<Shopper>> registers;
  
  
  public GroceryStore(){
    registers = new Vector<LinkedQueue<Shopper>>(3);
    for (int i=0; i<NUM_REGISTERS; i++) {
      registers.insertElementAt(new LinkedQueue<Shopper>(), i);
    }
  }
  
  public Queue<Shopper> getLane(int i){
    return registers.get(i);
  }
  
  public int getNumLanes(){
    return registers.size();
  }
  
  /**
   * Adds a shopper object to one of the queues stored in registers. If any queue is empty,
   * shopper joins the empty queue. If all queues are non-empty, shopper joins the shortest queue.
   * If two queues are of equal length, the shopper joins the queue with the fewest items on the 
   * check out belt.
   */
  public void pickLane(Shopper shopper){
    int minLane = 0; // index of register with shortest line
    int minLaneNum = registers.get(0).size(); // number of people at register
    int minLaneNumItems = 0; // number of items for person
    if (minLaneNum != 0) {
      minLaneNumItems = registers.get(0).peek().getNumItems();
    }
    // comparing registers
    for (int i=1; i<NUM_REGISTERS; i++) {
      // register shorter than register 0
      if (registers.get(i).size() < minLaneNum) {
        minLane = i;
        minLaneNum = registers.get(i).size();
        // if register is not empty
        if (minLaneNum != 0) {
          minLaneNumItems = registers.get(i).peek().getNumItems();
        } else minLaneNumItems = 0;
      } 
      // if number people at nonempty registers are same then compare number of items
      else if (minLaneNum!=0 && registers.get(i).size()==minLaneNum && registers.get(i).peek().getNumItems()<minLaneNumItems) {
          minLane = i;
          minLaneNumItems = registers.get(i).peek().getNumItems();
        }
    }
    // if enqueueing to empty register
    if (minLaneNum == 0) {
      shopper.setLeaveTime(shopper.getJoinTime()+shopper.getNumItems());
    }
    // enqueueing shopper to correct register
    registers.get(minLane).enqueue(shopper);
  }
    
  /**
   * As a side effect, the customer that is the new head of Queue q should have their 
   * leaveTime set to the sum of the currentTime and the number of items in their cart
   * @return the shopper at the head of Queue q
   */
  public Shopper finalizeTransaction(Queue<Shopper> q, int currentTime){
    Shopper head = q.dequeue();
    /* need to check that there is someone behind the current head of the queue
     * before we calculate the next head's leave time
     */
    if (!q.isEmpty()) {
      q.first().setLeaveTime(currentTime+q.first().getNumItems());
    }
    return head;
  }
  
  public String toString() {
    String s = "";
    for (int i=0; i<NUM_REGISTERS; i++) {
      s += "Register "+(i+1)+":\n";
      s += registers.get(i)+"\n\n";
    }
    return s;
  }
  
  public static void main(String[] args) {
    GroceryStore test = new GroceryStore();
    Shopper shopper1 = new Shopper(10,0);
    Shopper shopper2 = new Shopper(20,0);
    Shopper shopper3 = new Shopper(15,0);
    Shopper shopper4 = new Shopper(5,5);
    Shopper shopper5 = new Shopper(5,10);
    // testing pickLane
    test.pickLane(shopper1);
    test.pickLane(shopper2);
    test.pickLane(shopper3);
    test.pickLane(shopper4);
    test.pickLane(shopper5);
    System.out.println("Testing pickLane\n");
    System.out.println("Expected:\nRegister 1: shopper1 and shopper4\nRegister 2: shopper2\nRegister 3: shopper3 and shopper5\n");
    System.out.println(test);
    // testing finalizeTransaction
    test.finalizeTransaction(test.getLane(0),10);
    test.finalizeTransaction(test.getLane(1),20);
    test.finalizeTransaction(test.getLane(2),30);
    System.out.println("Testing finalizeTransaction\n");
    System.out.println("Expected:\nRegister 1: shopper4 (Leave time: 15)\nRegister 2: empty\nRegister 3: shopper5 (Leave time: 35)\n");
    System.out.println(test);
    
  }
}