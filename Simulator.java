/**
 * Driver class creates a simple Discrete Event Simulator
 * @author CS230 Spring 2017 Staff
 */

public class Simulator {
 //current time, to manage decisions about what should happen next
 private static int currentTime;
 private static final int SIM_STOP = 1000;
 
 //for keeping statistics
 private static int numShoppers;
 private static int totalShopperWaitTime;
 
 
 public static void main(String[] args){
  GroceryStore store = new GroceryStore();
  
  /* The first shopper will just get in a line
   * Nothing interesting happens between time 0 and when this shopper arrives,
   * so advance clock to their join line time 
   */
  Shopper temp = Shopper.getNextShopper(currentTime);
  store.pickLane(temp);
  currentTime = temp.getJoinTime();
  
  do{
   /* We always know who the next shopper is, even if they haven't showed up yet
    * (if another event should happen before they join the line)
    */
   temp = Shopper.getNextShopper(currentTime);
   
   /* Simulator should decide which event happens next in time (which event has the lowest clock time)
    * Could be 1) the shopper joins a line, 2) customer in one of the lanes finishes and leaves
    * This code does not assume and set number of lanes. Figure out which lane has the customer that will finish
    * first (if there is a customer there), and compare that to the time the shopper arrives. Do whichever is lower.
    * Update currentTime to the time of whichever event gets chosen.
       */
   int min = 0;
   for(int i = 0; i < store.getNumLanes(); i++){
    if(!store.getLane(i).isEmpty()){
     if(store.getLane(min).isEmpty()) min = i;
     else if(store.getLane(i).first().getLeaveTime() < store.getLane(min).first().getLeaveTime())
      min = i;
    }
   }
   if(store.getLane(min).isEmpty() || store.getLane(min).first().getLeaveTime() >= temp.getJoinTime()){
    store.pickLane(temp);
    currentTime = temp.getJoinTime();
    temp = null;
   }
   else{
    Shopper s = store.finalizeTransaction(store.getLane(min), currentTime);
    //update simulation time and statistics
    currentTime = s.getLeaveTime();
    numShoppers++;
    totalShopperWaitTime += (s.getLeaveTime() - s.getJoinTime());
   }
   
   //if shopper joined queue, get a shopper, repeat process
   if(temp == null) temp = Shopper.getNextShopper(currentTime);
   
  }while(currentTime < SIM_STOP);
  
  //when final simulation time is reached, print statistics
  System.out.println("Total customers: " + numShoppers);
  System.out.println("Average wait time: " + totalShopperWaitTime/numShoppers);
  
 }

}