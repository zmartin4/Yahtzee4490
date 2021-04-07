package yahtzee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialPanel extends JPanel {

  public InitialPanel(InitialControl ic) {

    // Style Constants
    Font buttonFont = new Font("Arial", Font.PLAIN, 28);

    // Layout Initialization
    JPanel layoutPanel = new JPanel(new BorderLayout(0, 75));
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 50, 40));
    JLabel bufferPanel = new JLabel();
    bufferPanel.setPreferredSize(new Dimension(0, 10));

    // Create Login Button
    JButton loginButton = new JButton("Login");
    loginButton.setFont(buttonFont);
    loginButton.setPreferredSize(new Dimension(250, 75));
    loginButton.addActionListener(ic);
    JPanel loginButtonBuffer = new JPanel();
    loginButtonBuffer.add(loginButton);
    buttonPanel.add(loginButtonBuffer);

    // Create Account Button
    JButton createButton = new JButton("Create Account");
    createButton.setFont(buttonFont);
    createButton.setPreferredSize(new Dimension(250, 75));

    createButton.addActionListener(ic);
    JPanel createButtonBuffer = new JPanel();
    createButtonBuffer.add(createButton);
    buttonPanel.add(createButtonBuffer);

    // Yahtzee logo
    JLabel logo = new JLabel("");
    ImageIcon LogoIcon =
        new ImageIcon(this.getClass().getResource("/yahtzee/images/yahtzee_logo.png"));
    logo.setIcon(LogoIcon);


    // add items to layouts
    layoutPanel.add(bufferPanel, BorderLayout.PAGE_START);
    layoutPanel.add(buttonPanel, BorderLayout.CENTER);
    layoutPanel.add(logo, BorderLayout.PAGE_END);
    this.add(layoutPanel);


  }
}
