package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Sets up the frame of the game, like a helper method.
 */
public class FrameSetup {

  private JFrame frame;

  /**
   * Constructor for the setting up of the frame. It returns a JLabel, so we can
   * access the score for score updating purposes.
   */
  public static JLabel setupFrame(JFrame frame, DrawUtils view,
                                  String playerTypeLabel, String score) {
    JLabel playerType = new JLabel(playerTypeLabel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(view, BorderLayout.CENTER);
    JLabel scoreLabel = new JLabel(score);
    frame.add(scoreLabel, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(playerType);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    return scoreLabel;
  }

}


