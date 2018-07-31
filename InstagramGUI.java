/* InstagramGUI.java
 * cs230 final project
 * written by: mshen4 & sjoshipu & elennonj
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

// GUI for the program!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class InstagramGUI {
  
  public static void main(String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Instagram Followers");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // adding tabs to frame
    TabbedPanel tabs = new TabbedPanel();
    frame.getContentPane().add(tabs);
    
    frame.pack();
    frame.setVisible(true);
  }
}