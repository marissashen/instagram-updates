/* TabbedPanel.java
 * cs230 final project
 * written by: mshen4 & ihussain
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

// panel that will contain the 4 tabs in the GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TabbedPanel extends JPanel {
  
  // creating the panel containing the 4 tabs
  public TabbedPanel() {
    
    JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
    tabs.setFont(new Font("InaiMathi", Font.PLAIN, 16));
    
    // creating account to put into panels
    Account client = new Account();
    
    // creating panels
    AboutPanel about = new AboutPanel(client);
    FollowersPanel followers = new FollowersPanel(client);
    FollowingPanel following = new FollowingPanel(client);
    SearchPanel search = new SearchPanel(client);
    
    // adding panels to the tabs
    tabs.add("ABOUT", about);
    tabs.add("FOLLOWERS", followers);
    tabs.add("FOLLOWING", following);
    tabs.add("SEARCH", search);
    
    this.add(tabs);
    setBackground(new Color(55,55,55));
  }
}