import model.HexBoard;
import model.HexShape;

import org.junit.Assert;
import org.junit.Test;

import view.DrawUtils;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Tests the draw states of the board.
 */
public class ExampleDrawTests {

  HexBoard board = new HexBoard();
  DrawUtils draw = new DrawUtils(board);

  @Test
  public void testSize() {
    draw.setSize(new Dimension(600, 600));
    Assert.assertEquals(draw.getSize(), new Dimension(600, 600));
  }

  @Test
  public void testInitialization() {
    JFrame frame = new JFrame();
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));
    frame.add(draw);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
    Assert.assertNotNull(draw);
  }

  @Test
  public void testWidthAndHeight() {
    draw.setSize(600, 600);
    Assert.assertEquals(draw.getWindowWidth(), 600);
    Assert.assertEquals(draw.getHeight(), 600);
    draw.setBackground(Color.GRAY);
    Assert.assertEquals(Color.GRAY, draw.getBackground());
  }

  @Test
  public void testFindHex() {
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;
    HexShape hex = draw.findHex(x, y);
    Assert.assertNotNull(hex);
  }

  @Test
  public void testGetHexSize() {
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));
    draw.setSize(600, 600);
    int hexSize = draw.getHexSize();
    System.out.print(draw.getHexSize());
    Assert.assertTrue(hexSize > 0 && hexSize % 2 == 0);
  }

  @Test
  public void testIsPointInHex() {
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;

    int hexSize = draw.getHexSize();
    boolean isPointInHex = draw.isPointInHex(x, y, x, y, hexSize);
    Assert.assertTrue(isPointInHex);
  }

  @Test
  public void testGetWindowWidth() {
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(draw);

    draw.setPreferredSize(new Dimension(650, 650));

    frame.pack();
    frame.setVisible(true);

    int windowWidth = draw.getWindowWidth();
    System.out.println(draw.getWindowWidth());

    Assert.assertEquals(650, windowWidth);
  }


  @Test
  public void testGetWindowHeight() {
    DrawUtils draw = new DrawUtils(new HexBoard(11, false));
    draw.setSize(600, 600);
    int windowHeight = draw.getWindowHeight();
    Assert.assertEquals(windowHeight, 600);
  }


  @Test
  public void testDraw() {
    draw.setSize(600, 600);
    Assert.assertEquals(new Color(0, 34, 83), draw.getBackground());
  }
}