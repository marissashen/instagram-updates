/* Account.java
 * cs230 final project
 * written by: mshen4 & sjoshipu & elennonj
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 5/1/2017
 */

/* main class that contains everything about the client's Instagram 
 * account (followers/following wise)
 */

import java.util.*;
import java.io.*;

public class Account {
  
  private String username;
  private String password;
  // boolean for if access is granted (username and password correct)
  private boolean access;
  private Hashtable<String, Integer> hash;
  // all of client's followers
  private Vector<User> followers;
  // all users client is following
  private Vector<User> following;
  private Vector<User> mutuals;
  private Vector<User> all;
  // index for array of all users
  private int index = 0;
  // not following client back (nfcb)
  private Vector<User> nfcb;
  private int nfcbPercent;
  // client not following back (cnfb)
  private Vector<User> cnfb;
  private int cnfbPercent;
  
  public Account() {
    followers = new Vector<User>();
    following = new Vector<User>();
    mutuals = new Vector<User>();
    all = new Vector<User>();
    nfcb = new Vector<User>();
    cnfb = new Vector<User>();
  }
  
  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    followers = new Vector<User>();
    following = new Vector<User>();
    mutuals = new Vector<User>();
    all = new Vector<User>();
    nfcb = new Vector<User>();
    cnfb = new Vector<User>();
  }
  
  // setters for username and password
  public void setUsername(String newUsername) {
    username = newUsername;
  }
  
  public void setPassword(String newPassword) {
    password = newPassword;
  }
  
  public void getInfo() {
    try {
      readDatabase();
    }
    catch (WrongAccountInfoException ex) {
      System.out.println("Account info does not exist in our database.");
    }
    catch (FileNotFoundException ex) {
      System.out.println("File(s) could not be found.");
    }
  }
  
  // reading database and seeing if account exists (phase 2)
  private void readDatabase() throws FileNotFoundException, WrongAccountInfoException {
    boolean exists = false;
    Scanner scan = new Scanner(new File("database.txt"));
    // each account is split by #
    scan.useDelimiter("#\n");
    while (scan.hasNext()) {
      // checking each account in database to current account
      String[] accountInfo = scan.next().split("\n");
      // account info is correct
      if (accountInfo[0].equals(username) && accountInfo[1].equals(password)) {
        exists = true;
        access = true;
        readFiles(accountInfo);
      }
    }
    // account doesn't exist then throw exception
    if (!exists) {
      access = false;
      empty();
      // print out info
      System.out.println("Followers: "+followers);
      System.out.println("Following: "+following);
      System.out.println("Mutuals: "+mutuals);
      System.out.println("Hash: "+hash);
      System.out.println("All: "+all);
      System.out.println("NFCB: "+nfcb);
      System.out.println("CNFB: "+cnfb);
      System.out.println("NFCB Percent: "+nfcbPercent);
      System.out.println("CNFB Percent: "+cnfbPercent);
      throw new WrongAccountInfoException();
    }
  }
  
  // reading followers and following from txt file (phase 2)
  private void readFiles(String[] info) {
    // length of info - 2 = total num of following connections
    hash = new Hashtable<String, Integer>((int) ((info.length-2)*1.5));
    // resetting index to 0
    index = 0;
    // starting at index 2 because 0 and 1 are username and password
    for (int i=2; i<info.length; i++) {
      readUser(info[i]);
    }
    // helper method to add users to nfcb  and cnfb
    addToVectors();
    // helper method to get nfcb and cnfb percents
    calculatePercent();
    // print out info
    System.out.println("Followers: "+followers);
    System.out.println("Following: "+following);
    System.out.println("Mutuals: "+mutuals);
    System.out.println("Hash: "+hash);
    System.out.println("All: "+all);
    System.out.println("NFCB: "+nfcb);
    System.out.println("CNFB: "+cnfb);
    System.out.println("NFCB Percent: "+nfcbPercent);
    System.out.println("CNFB Percent: "+cnfbPercent);
  }
  
  // helper method to read users and add to hash and correct vectors (phase 2)
  private void readUser(String userInfo) {
    // splitting userInfo line into username, name, status
    String[] info2 = userInfo.split(",");
    String u = info2[0].trim();
    String n = info2[1].trim();
    int s = Integer.parseInt(info2[2].trim());
    // creating new user
    User user = new User(u, n, s);
    // adding user to vector depending on status
    if (s == 1) {
      followers.add(user);
    }
    if (s == -1) {
      following.add(user);
    }
    // checking if user is already in hash
    if (hash.containsKey(u)) {
      user.setStatus(0);
      mutuals.add(user);
      // getting index of username
      int i = hash.get(u);
      // replacing old user with new user (updated status)
      all.setElementAt(user, i);
    }
    else {
      // adding user to hash and followers and all
      hash.put(u, index);
      all.add(user);
      // increment index after adding to all
      index++;
    }
  }
  
  // helper method to "empty" all the ADTs and percents and index if wrong account info is inputed
  private void empty() {
    hash = new Hashtable<String, Integer>();
    followers = new Vector<User>();
    following = new Vector<User>();
    mutuals = new Vector<User>();
    all = new Vector<User>();
    nfcb = new Vector<User>();
    cnfb = new Vector<User>();
    nfcbPercent = 0;
    cnfbPercent = 0;
    index = 0;
  }
  
  // reading followers and following txt files (phase 1)
  private void readFiles() throws FileNotFoundException {
    Scanner scan1 = new Scanner(new File(username+"_followers.txt"));
    int total1 = Integer.valueOf(scan1.nextLine().trim());
    Scanner scan2 = new Scanner(new File(username+"_following.txt"));
    int total2 = Integer.valueOf(scan2.nextLine().trim());
    hash = new Hashtable<String, Integer>((int) ((total1+total2)*1.5));
    readUsers(scan1, scan2);
    // helper method to add users to nfcb  and cnfb vectors
    addToVectors();
    // helper method to get nfcb and cnfb percents
    calculatePercent();
    // print out info
    System.out.println("Followers: "+followers);
    System.out.println("Following: "+following);
    System.out.println("Mutuals: "+mutuals);
    System.out.println("Hash: "+hash);
    System.out.println("All: "+all);
    System.out.println("NFCB: "+nfcb);
    System.out.println("CNFB: "+cnfb);
    System.out.println("NFCB Percent: "+nfcbPercent);
    System.out.println("CNFB Percent: "+cnfbPercent);
  }   
    
  /* helper method to read the users and add them to the ADT
   * only adds to hash, followers, following, and mutuals
   * first param is for scanner of followers text file
   * second param is for scanner of following text file
   * (phase 1)
   */
  private void readUsers(Scanner scan1, Scanner scan2) {
    // reading followers text file
    while (scan1.hasNextLine()) {
      // getting info of user
      String info1 = scan1.nextLine();
      String[] info2 = info1.split(",");
      String username = info2[0].trim();
      String name = info2[1].trim();
      int status = Integer.parseInt(info2[2].trim());
      // creating new user
      User user = new User(username, name, status);
      // adding user to hash and followers and all
      hash.put(username, index);
      followers.add(user);
      all.add(user);
      // increment index after adding to all
      index++;
    }
    // reading following text file
    while (scan2.hasNextLine()) {
      // getting info of user
      String info1 = scan2.nextLine();
      String[] info2 = info1.split(",");
      String username = info2[0].trim();
      String name = info2[1].trim();
      int status = Integer.parseInt(info2[2].trim());
      // creating new user
      User user = new User(username, name, status);
      // adding user to following
      following.add(user);
      // checking if user is already in hash
      if (hash.containsKey(username)) {
        // will change the status to 0 because on both lists
        user.setStatus(0);
        mutuals.add(user);
        // getting index of username
        int i = hash.get(username);
        // replacing old user with new user (updated status)
        all.setElementAt(user, i);
      }
      else {
        hash.put(username, index);
        all.add(user);
        index++;
      }
    }
  }
  
  // helper method to calculate percent of non follow-backs
  private void calculatePercent() {
    // dividing nfcb by total followings and multiplying by 100 to get %
    nfcbPercent = ((int) Math.round((nfcb.size()*1.0/following.size())*100));
    // dividing cnfb by total followers and multiplying by 100 to get %
    cnfbPercent = ((int) Math.round((cnfb.size()*1.0/followers.size())*100));
  }

  // search method to look up a specific username
  public String search(String searchUser) {
    // check if user exists/is associated with client
    if (hash.containsKey(searchUser)) {
      // index of user in all vector
      int i = hash.get(searchUser);
      User searched = all.get(i);
      // return string status (sentence version) of user and relationship
      return searched.returnStatus();
    }
    // user is not in hash and so is not associated with client
    else {
      String s = (searchUser+" is not associated with your account.");
      return s;
    }
  }
  
  // helper method adding users to the nfcb and cnfb vectors
  private void addToVectors() {
    for (int i=0; i<all.size(); i++) {
      // not following client back
      if (all.elementAt(i).getStatus() == -1) {
        nfcb.add(all.elementAt(i));
      }
      // client not following back
      if (all.elementAt(i).getStatus() == 1) {
        cnfb.add(all.elementAt(i));
      }
    }
  }
    
  // setter for access
  public void setAccess(boolean newAccess) {
    access = newAccess;
  }
  
  // getters  
  public boolean getAccess() {
    return access;
  }
  
  public Hashtable getHash() {
    return hash;
  }
  
  public Vector<User> getFollowers() {
    return followers;
  }
  
  public Vector<User> getFollowing() {
    return following;
  }
  
  public Vector<User> getMutuals() {
    return mutuals;
  }
  
  public Vector<User> getAll() {
    return all;
  }
  
  public Vector<User> getNFCB() {
    return nfcb;
  }
  
  public int getNFCBPercent() {
    return nfcbPercent;
  }
  
  public Vector<User> getCNFB() {
    return cnfb;
  }
   
  public int getCNFBPercent() {
    return cnfbPercent;
  } 
  
  public String toString() {
    String s = ("Username: "+username+"\tPassword: "+password);
    return s;
  }

}
  
  