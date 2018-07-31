/* AboutPanel.java
 * cs230 final project
 * written by: mshen4 & ihussain
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

/* panel with basic instructions for the client and also where 
 * client will input his/her Instagram login info
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font.*;
import java.awt.GraphicsEnvironment.*;

public class AboutPanel extends JPanel {
  private String username;
  private String password;
  private Account client;
  JTextField enterUsername, enterPassword;
  JLabel messageLabel;
  
  public AboutPanel(Account client) {
    this.client = client;
    JPanel aboutPanel = new JPanel();
    aboutPanel.setLayout(new GridLayout(2, 1));
  
    // creating about's text label & adding to About panel
    String s = "<html><p>Want to learn more about your Instagram followers?</p><p>The next killer app designed by Marissa, Sannidhi, and Dee Dee.</p>";
    s += "<p>-----</p><p>Select the [FOLLOWERS] tab to see who follows you.</p><p>Select the [FOLLOWING] tab to see who you follow.</p>";
    s += "Select the [SEARCH] tab to look up a specific user and see their relationship to your account.</p><p>But first, enter your Instagram details below.</p>";
    JLabel aboutLabel = new JLabel(s);
    aboutLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));

    // creating label and gets user input on username
    JLabel cUsername = new JLabel("Username: ");
    cUsername.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    enterUsername = new JTextField(10);
    enterUsername.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    enterUsername.addActionListener(new UsernameListener());
    
    // creating label and gets user input on username
    JLabel cPassword = new JLabel("Password: ");
    cPassword.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    enterPassword = new JTextField(10);
    enterPassword.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    enterPassword.addActionListener(new PasswordListener());
    
    // creating a panel for getting input from client
    JPanel infoPanel = new JPanel();
    infoPanel.add(cUsername);
    infoPanel.add(enterUsername);
    infoPanel.add(cPassword);
    infoPanel.add(enterPassword);
    
    // creating a login button
    JButton loginButton = new JButton("Login");
    loginButton.addActionListener(new ButtonListener());
    // creating a message label
    messageLabel = new JLabel("");
    messageLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    
    // adding password/password/login/message into one panel
    JPanel loginPanel = new JPanel(new GridLayout(4, 1));
    loginPanel.add(infoPanel);
    loginPanel.add(loginButton);
    loginPanel.add(messageLabel);
    loginPanel.setBackground(new Color(213, 213, 213));
    
    // adding everything to aboutPanel
    aboutPanel.add(aboutLabel);
    aboutPanel.add(loginPanel);
    aboutPanel.setBackground(new Color(213, 213, 213));
    
    this.add(aboutPanel);
    setBackground(new Color(213, 213, 213));
  }
  
  // listener only called if you press the enter/return key
  private class UsernameListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      username = enterUsername.getText();
      System.out.println("Username: "+username);
      client.setUsername(username);
    }
  }
  
  // listener only called if you press the enter/return key
  private class PasswordListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      password = enterPassword.getText();
      System.out.println("Password: "+password);
      client.setPassword(password);
    }
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      System.out.println("Login button pressed");
      // entered info for username and password
      if (username != null && password != null) {
        client.getInfo();
        // need to check if username and password exist in database
        if (client.getAccess()) {
          messageLabel.setText("Go refresh the other tabs for more infomation.");
        }
        else {
          messageLabel.setText("The username and password you entered does not exist in our database.");
        }
        System.out.println("Entered account info: "+client);
      }
      // user didn't input all necessary info (most likely forgot to press enter/return key)
      else {
        // missing username
        if (username == null) {
          messageLabel.setText("Please enter a username.");
          System.out.println("Missing a username");
        }
        // missing password
        else {
          messageLabel.setText("Please enter a password.");
          System.out.println("Missing a password");
        }
      }
    }
  }
}