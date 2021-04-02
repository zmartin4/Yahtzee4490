package yahtzee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGUI extends JFrame {

  private JButton listen; // From Lab 1 In
  private JButton close; // From Lab 1 In
  private JButton stop; // From Lab 1 In
  private JButton quit; // From Lab 1 In
  private JLabel status; // Initialized to “Not Connected”
  private String[] labels = {"Port #", "Timeout"};
  private JTextField[] textFields = new JTextField[labels.length];
  private JTextArea log;
  private JPanel panel;


  private ChatServer server;


  public ServerGUI() {
    int i = 0;
    final int midBound0 = 175;
    final int midBound1 = 275;

    server = new ChatServer();
    setTitle("server");
    setSize(600, 500);
    setResizable(false);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);


    panel = new JPanel();
    panel.setLayout(null);
    add(panel);

    // Logo
    JLabel logo = new JLabel("");
    ImageIcon LogoIcon = new ImageIcon(this.getClass().getResource("/yahtzee/images/uca.png"));
    logo.setIcon(LogoIcon);
    logo.setBounds(25, 25, LogoIcon.getIconWidth(), LogoIcon.getIconHeight()); // can use
                                                                               // setLocation() and
                                                                               // setSize() as well
    panel.add(logo);


    // JLabel
    status = new JLabel("Status:");
    status.setBounds(midBound0, 30, 75, 25);
    panel.add(status);

    // JLabel Status
    status = new JLabel("Not Connected");
    status.setBounds(midBound1, 30, 125, 25);
    status.setForeground(Color.red);
    panel.add(status);

    JPanel textFieldPanel = new JPanel();
    BoxLayout layout = new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS);
    textFieldPanel.setLayout(layout);
    textFieldPanel.setBounds(midBound1, 50, 150, 120);

    JPanel labelPanel = new JPanel();
    layout = new BoxLayout(labelPanel, BoxLayout.Y_AXIS);
    labelPanel.setLayout(layout);
    labelPanel.setBounds(midBound0, 40, 150, 120);
    labelPanel.setAlignmentX(RIGHT_ALIGNMENT);



    // JTextField
    JLabel[] infoLabels = new JLabel[2];
    for (int j = 0; j < labels.length; j++) {

      textFields[j] = new JTextField();
      textFieldPanel.add(Box.createRigidArea(new Dimension(0, 30)));
      textFieldPanel.add(textFields[j]);

      infoLabels[j] = new JLabel(labels[j]);

      labelPanel.add(Box.createRigidArea(new Dimension(0, 45)));
      labelPanel.add(infoLabels[j]);
      textFields[j].setVisible(true);
    }
    panel.add(textFieldPanel);
    panel.add(labelPanel);
    textFieldPanel.revalidate();



    JLabel clientLabel = new JLabel("Server Log");
    clientLabel.setBounds(midBound0 + 50, 225, 150, 25);
    panel.add(clientLabel);

    log = new JTextArea();
    log.setBounds(150, 250, 300, 100);
    JScrollPane scrollLog = new JScrollPane(log);
    scrollLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollLog.setBounds(150, 250, 300, 100);
    panel.add(scrollLog);
    panel.repaint();

    // JButtons
    listen = new JButton("Listen");
    listen.setBounds(100, 400, 75, 30);
    listen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {

        int port = Integer.parseInt(textFields[0].getText());
        int timeout = Integer.parseInt(textFields[1].getText());

        server.setPort(port);
        server.setTimeout(timeout);
        try {
          server.listen();
        } catch (IOException e) {
          e.printStackTrace();
        }

        server.setStatus(status);
        server.setLog(log);
      }
    });

    panel.add(listen);


    close = new JButton("Close");
    close.setBounds(200, 400, 75, 30);
    close.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        server.setStatus(status);
        server.setLog(log);
      }
    });
    panel.add(close);


    stop = new JButton("Stop");
    stop.setBounds(300, 400, 75, 30);
    stop.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        server.stopListening();
        server.setStatus(status);
        server.setLog(log);
      }
    });
    panel.add(stop);


    quit = new JButton("Quit");
    quit.setBounds(400, 400, 75, 30);
    quit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        System.exit(0);
      }
    });
    panel.add(quit);
    panel.repaint();



  }

  public static void main(String[] args) {
    new ServerGUI(); // args[0] represents the title of the GUI
  }

}
