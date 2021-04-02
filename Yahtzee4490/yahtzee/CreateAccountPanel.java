package yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel {
  // Private data fields for the important GUI components.
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JPasswordField verifyPasswordField;
  private JLabel errorLabel;

  // Getter for the text in the username field.
  public String getUsername() {
    return usernameField.getText();
  }

  // Getter for the text in the password field.
  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  public String getVerifyPassword() {
    return new String(verifyPasswordField.getPassword());
  }

  // Setter for the error text.
  public void setError(String error) {
    errorLabel.setText(error);
  }

  // Constructor for the login panel.
  public CreateAccountPanel(CreateAccountControl cac) {

    // Style Constants
    Color color = new Color(215, 45, 53);
    Font font = new Font("Arial", Font.PLAIN, 20);


    // Create a panel for the labels at the top of the GUI.
    // Arrange the three panels in a grid.
    JPanel layoutPanel = new JPanel(new BorderLayout(0, 50)); // Overall Panel
    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    JPanel createAccountPanel = new JPanel(new GridLayout(3, 3, 5, 5));
    JPanel buttonPanel = new JPanel();


    errorLabel = new JLabel("", JLabel.CENTER);
    errorLabel.setForeground(Color.CYAN);
    JLabel instructionLabel =
        new JLabel("   Passwords must be over 6 characters long   ", JLabel.CENTER);
    instructionLabel.setForeground(Color.WHITE);
    instructionLabel.setFont(font);
    labelPanel.add(errorLabel);
    labelPanel.add(instructionLabel);

    // Labels and text fields for account info
    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
    usernameLabel.setFont(font);
    usernameLabel.setForeground(Color.WHITE);
    usernameField = new JTextField(10);
    usernameField.setPreferredSize(new Dimension(100, 35));

    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
    passwordLabel.setFont(font);
    passwordLabel.setForeground(Color.WHITE);
    passwordField = new JPasswordField(10);

    JLabel verifyPasswordLabel = new JLabel("Verify Password:", JLabel.RIGHT);
    verifyPasswordLabel.setFont(font);
    verifyPasswordLabel.setForeground(Color.WHITE);
    verifyPasswordField = new JPasswordField(10);

    createAccountPanel.add(usernameLabel);
    createAccountPanel.add(usernameField);
    createAccountPanel.add(passwordLabel);
    createAccountPanel.add(passwordField);
    createAccountPanel.add(verifyPasswordLabel);
    createAccountPanel.add(verifyPasswordField);

    // Submit Button
    JButton submitButton = new JButton("Submit");
    submitButton.setBackground(Color.WHITE);
    submitButton.setFont(font);
    submitButton.setPreferredSize(new Dimension(150, 40));
    submitButton.addActionListener(cac);
    buttonPanel.add(submitButton);

    // Cancel Button
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBackground(Color.WHITE);
    cancelButton.addActionListener(cac);
    cancelButton.setPreferredSize(new Dimension(150, 40));
    cancelButton.setFont(font);
    buttonPanel.add(cancelButton);

    // Yahtzee logo
    JLabel logo = new JLabel("");
    ImageIcon LogoIcon = new ImageIcon(this.getClass().getResource("/yahtzee/images/logo_m.png"));
    logo.setIcon(LogoIcon);
    logo.setHorizontalAlignment(JLabel.CENTER);

    // Set background colors
    layoutPanel.setBackground(color);
    labelPanel.setBackground(color);
    createAccountPanel.setBackground(color);
    buttonPanel.setBackground(color);
    grid.setBackground(color);
    this.setBackground(color);

    // Add items to layout
    grid.add(createAccountPanel);
    grid.add(buttonPanel);
    grid.add(logo);
    layoutPanel.add(labelPanel, BorderLayout.PAGE_START);
    layoutPanel.add(grid, BorderLayout.CENTER);
    this.add(layoutPanel);
  }
}
