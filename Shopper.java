import java.util.Random;

/**
* Shopper.java represents a customer in a grocery store
* @author CS230 Spring 2017 staff
*/
public class Shopper {

 private int numItems, joinTime, leaveTime;
 
 public Shopper(int itemsInCart, int time){
  numItems = itemsInCart;
  joinTime = time;
  leaveTime = -1;
 }

 /**
  * @return the next shopper to join the queue, at random future time and with random number of items in cart
  */
 public static Shopper getNextShopper(int currentTime){
  Random r = new Random();
  return new Shopper(r.nextInt(50) + 1, r.nextInt(10) + currentTime);
 }
 
 public int getNumItems(){
  return numItems;
 }
 
 public int getJoinTime(){
  return joinTime;
 }
 
 public int getLeaveTime(){
  return leaveTime;
 }
 
 public void setLeaveTime(int time){
  leaveTime = time;
 }
 
 public String toString() {
   return ("Items in cart: "+numItems+"\nJoin time: "+joinTime+"\nLeave time: "+leaveTime);
 }
}