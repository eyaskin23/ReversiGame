package view;


import javax.swing.JButton;

import controller.players.PlayerType;

import java.awt.Dimension;
import java.awt.Graphics;

import model.HexShape;

/**
 * Represents a player's button (PlayerType).
 */
public class PlayerButton extends JButton {

  /**
   * Constructors a player button on a hex.
   */
  public PlayerButton(HexShape hex) {
    PlayerType type = PlayerType.EMPTY;
    hex.setButton(this);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setOpaque(false);
    setPreferredSize(new Dimension(100, 100));
  }

  /**
   * Paints a button for the player.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
