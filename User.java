/* User.java
 * cs230 final project
 * written by: mshen4 & sjoshipu & elennonj
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

/* user object (for every user associated with client's account)
 * that will contain a username, name, and status
 */

public class User {
  private String username;
  private String name;
  /* status is status of following relationship
   * -1 for not following you back
   * 0 for mutual follow
   * 1 for not following them back
   */
  private int status;
  
  public User(String username, String name, int status) {
    this.username = username;
    this.name = name;
    this.status = status;
  }
  
  // getters and setters
  public String getUsername() {
    return username;
  }
  
  public String getName() {
    return name;
  }
  
  public int getStatus() {
    return status;
  }
  
  public void setUsername(String newUsername) {
    username = newUsername;
  }
  
  public void setName(String newName) {
    name = newName;
  }
  
  public void setStatus(int newStatus) {
    status = newStatus;
  }

  // original toString for testing
//  public String toString() {
//    String s = (username+" ("+name+"): "+status);
//    return s;
//  }
  
  // alternate toString method to be used in the GUI
  public String toString() {
    String s = (name+": "+username);
    return s;
  }
  
  // method to return a string of user's status that is a sentence (to be used in GUI)
  public String returnStatus() {
    String s;
    if (status < 0) {
      s = (username+" does not follow you back.");
    }
    else if (status > 0) {
      s = ("You do not follow "+username+" back.");
    }
    else {
      s = ("You and "+username+" are mutual followers.");
    }
    return s;
  }
}  