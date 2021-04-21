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

public class LoginPanel extends JPanel {
  // Private data fields for the important GUI components.
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JLabel errorLabel;

  public String getUsername() {
    return usernameField.getText();
  }

  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  public void setError(String error) {
    errorLabel.setText(error);
  }


  public LoginPanel(LoginControl lc) {

    // Style Constants
    Color color = new Color(215, 45, 53);
    Font font = new Font("Arial", Font.PLAIN, 20);

    JPanel layoutPanel = new JPanel(new BorderLayout(0, 50));
    JPanel grid = new JPanel(new GridLayout(3, 1, 50, 25));
    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 20));
    JPanel buttonPanel = new JPanel();

    errorLabel = new JLabel("", JLabel.CENTER);
    errorLabel.setForeground(Color.CYAN);
    JLabel instructionLabel =
        new JLabel("Enter your username and password to log in.", JLabel.CENTER);
    labelPanel.add(errorLabel);
    instructionLabel.setFont(font);
    instructionLabel.setForeground(Color.WHITE);
    labelPanel.add(instructionLabel);

    // Create a panel for the login information form.
    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
    usernameLabel.setForeground(Color.WHITE);
    usernameLabel.setFont(font);
    usernameField = new JTextField(10);
    usernameField.setText("");

    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
    passwordLabel.setForeground(Color.WHITE);
    passwordLabel.setFont(font);
    passwordField = new JPasswordField(10);
    passwordField.setText("");


    loginPanel.add(usernameLabel);
    loginPanel.add(usernameField);
    loginPanel.add(passwordLabel);
    loginPanel.add(passwordField);



    // Submit Button
    JButton submitButton = new JButton("Submit");
    submitButton.setFont(font);
    submitButton.setPreferredSize(new Dimension(150, 40));
    submitButton.addActionListener(lc);
    buttonPanel.add(submitButton);

    // Cancel Button
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(lc);
    cancelButton.setPreferredSize(new Dimension(150, 40));
    cancelButton.setFont(font);
    buttonPanel.add(cancelButton);

    // Yahtzee logo
    JLabel logo = new JLabel("");
    ImageIcon LogoIcon = new ImageIcon(this.getClass().getResource("/yahtzee/images/logo_m.png"));
    logo.setIcon(LogoIcon);
    logo.setHorizontalAlignment(JLabel.CENTER);



    // grid.add(labelPanel);
    grid.add(loginPanel);
    grid.add(buttonPanel);
    grid.add(logo);
    layoutPanel.add(labelPanel, BorderLayout.PAGE_START);
    layoutPanel.add(grid, BorderLayout.CENTER);
    // layoutPanel.add(logo, BorderLayout.PAGE_END);
    this.add(layoutPanel);
  }
}
