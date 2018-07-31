/* FollowingPanel.java
 * cs230 final project
 * written by: mshen4 & ihussain
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

/* panel to display who the client follows and 
 * who isn't following the client back
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FollowingPanel extends JPanel {
  private Account client;
  private JPanel followingPanel, allFollowingPanel, nfcbPanel, textPanel, listPanel;
  private JLabel followingLabel, nfcbLabel;
  private JList allFollowing, nfcb;
  private JScrollPane scroll1, scroll2;
  
  public FollowingPanel(Account client) {
    this.client = client;
        
    followingPanel = new JPanel();
    followingPanel.setLayout(new BorderLayout());
    
    // label displaying all users client follows
    followingLabel = new JLabel("You follow "+client.getFollowing().size()+" users.");
    followingLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // adding followings to a list
    allFollowing = new JList(client.getFollowing());
    allFollowing.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // putting list into a scrollable panel
    scroll1 = new JScrollPane(allFollowing);
    scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // label displaying people who don't follow the client back
    nfcbLabel = new JLabel(client.getNFCBPercent()+"% of users you follow do not follow you back.");
    nfcbLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // adding users who don't follow to a list
    nfcb = new JList(client.getNFCB());
    nfcb.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // putting list into a scrollable panel
    scroll2 = new JScrollPane(nfcb);
    scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // text panel to hold the two labels
    textPanel = new JPanel(new GridLayout(1, 2));
    textPanel.add(followingLabel);
    textPanel.add(nfcbLabel);
    
    // scroll panel to hold the two lists of users
    listPanel = new JPanel(new GridLayout(1, 2));
    listPanel.add(scroll1);
    listPanel.add(scroll2);
    
    // refresh button to refresh info
    JButton refreshButton = new JButton("Refresh");
    refreshButton.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    refreshButton.addActionListener(new ButtonListener());

    // adding everything to followersPanel
    followingPanel.add(textPanel, BorderLayout.NORTH);
    followingPanel.add(listPanel, BorderLayout.CENTER);
    followingPanel.add(refreshButton, BorderLayout.SOUTH);
    
    this.add(followingPanel);
  }
   
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      // always refresh account even if doesn't belong in database (will display empty ADTs)
      System.out.println("Following refresh button pressed");
      followingLabel.setText("You follow "+client.getFollowing().size()+" users.");
      allFollowing.setListData(client.getFollowing());
      nfcbLabel.setText(client.getNFCBPercent()+"% of users you follow do not follow you back.");
      nfcb.setListData(client.getNFCB());
    }
  }
}