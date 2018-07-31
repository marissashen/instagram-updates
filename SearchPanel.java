/* SearchPanel.java
 * cs230 final project
 * written by: mshen4 & ihussain
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

/* panel where the client will be able to look up a specific username's 
 * relationship to his/her account
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchPanel extends JPanel {
  private String search;
  private Account client;
  private JTextField searchInput;
  private JLabel resultsLabel;
  
  public SearchPanel(Account client) {
    this.client = client;
    
    JPanel searchPanel = new JPanel(new GridLayout(3,1));
    
    // creating label and gets user input on username to search
    JLabel searchUsername = new JLabel("Enter the username you would like to search for.");
    searchUsername.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // text box for client to enter username to search
    searchInput = new JTextField(10);
    searchInput.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    searchInput.addActionListener(new SearchListener());

    // label for the results of the search
    resultsLabel = new JLabel("Your search result will appear here.");
    resultsLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    
    // adding everything to the searchPanel
    searchPanel.add(searchUsername);
    searchPanel.add(searchInput);
    searchPanel.add(resultsLabel);
    
    this.add(searchPanel);
  }
  
  // listener only called if you press the enter/return key
  private class SearchListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      search = searchInput.getText();
      System.out.println("Search: "+search);
      // look up search username in client's database
      resultsLabel.setText(client.search(search));
    }
  }
}