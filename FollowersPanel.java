/* FollowersPanel.java
 * cs230 final project
 * written by: mshen4 & ihussain
 * modified by: mshen4 & sjoshipu & elennonj
 * modified date: 4/25/2017
 */

/* panel to display who follows the client and 
 * who the client doesn't follow back
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FollowersPanel extends JPanel {
  private Account client;
  private JPanel followersPanel, allFollowersPanel, cnfbPanel, textPanel, listPanel;
  private JLabel followersLabel, cnfbLabel;
  private JList allFollowers, cnfb;
  private JScrollPane scroll1, scroll2;
  
  public FollowersPanel(Account client) {
    this.client = client;
    
    followersPanel = new JPanel();
    followersPanel.setLayout(new BorderLayout());
    
    // label displaying all followers
    followersLabel = new JLabel("You have "+client.getFollowers().size()+" followers.");
    followersLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // adding followers to a list
    allFollowers = new JList(client.getFollowers());
    allFollowers.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // putting list into a scrollable panel
    scroll1 = new JScrollPane(allFollowers);
    scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    // label displaying people the client doesn't follow back
    cnfbLabel = new JLabel("You do not follow back "+client.getCNFBPercent()+"% of your followers.");
    cnfbLabel.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // adding users who aren't followed back to a list
    cnfb = new JList(client.getCNFB());
    cnfb.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    // putting list into a scrollable panel
    scroll2 = new JScrollPane(cnfb);
    scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    // text panel to hold the two labels
    textPanel = new JPanel(new GridLayout(1, 2));
    textPanel.add(followersLabel);
    textPanel.add(cnfbLabel);
    
    // scroll panel to hold the two lists of users
    listPanel = new JPanel(new GridLayout(1, 2));
    listPanel.add(scroll1);
    listPanel.add(scroll2);
    
    // refresh button to refresh info
    JButton refreshButton = new JButton("Refresh");
    refreshButton.setFont(new Font("InaiMathi", Font.PLAIN, 15));
    refreshButton.addActionListener(new ButtonListener());
    
    // adding everything to followersPanel
    followersPanel.add(textPanel, BorderLayout.NORTH);
    followersPanel.add(listPanel, BorderLayout.CENTER);
    followersPanel.add(refreshButton, BorderLayout.SOUTH);
    
    this.add(followersPanel);
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      // always refresh account even if doesn't belong in database (will display empty ADTs)
      System.out.println("Follwers refresh button pressed");
      followersLabel.setText("You have "+client.getFollowers().size()+" followers.");
      allFollowers.setListData(client.getFollowers());
      cnfbLabel.setText("You do not follow back "+client.getCNFBPercent()+"% of your followers.");
      cnfb.setListData(client.getCNFB());
    }
  }
}