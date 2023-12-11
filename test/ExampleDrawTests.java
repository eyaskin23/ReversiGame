import model.HexBoard;
import model.HexShape;

import org.junit.Assert;
import org.junit.Test;

import view.DrawUtils;
import view.SquareView;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Tests the draw states of the board.
 */
public class ExampleDrawTests {
  HexBoard board = new HexBoard();
  DrawUtils draw = new DrawUtils(board);
  SquareView square = new SquareView(board);

  @Test
  public void testSize() {
    draw.setSize(new Dimension(600, 600));
    square.setSize(new Dimension(600, 600));
    Assert.assertEquals(draw.getSize(), new Dimension(600, 600));
    Assert.assertEquals(square.getSize(), new Dimension(600, 600));
  }

  @Test
  public void testInitialization() {
    JFrame frame = new JFrame();
    DrawUtils draw = new DrawUtils(new HexBoard(11));
    SquareView square = new SquareView(new HexBoard(8));
    frame.add(draw);
    frame.add(square);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
    Assert.assertNotNull(draw);
  }

  @Test
  public void testInitializationSquare() {
    JFrame frame = new JFrame();
    SquareView draw = new SquareView(new HexBoard(8));
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
    square.setSize(600, 600);
    Assert.assertEquals(square.getWindowWidth(), 600);
    Assert.assertEquals(square.getHeight(), 600);
    square.setBackground(Color.GRAY);
    Assert.assertEquals(Color.GRAY, square.getBackground());
  }

  @Test
  public void testFindHex() {
    DrawUtils draw = new DrawUtils(new HexBoard(11));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;
    HexShape hex = draw.findHex(x, y);
    Assert.assertNotNull(hex);
  }

  @Test
  public void testGetHexSize() {
    DrawUtils draw = new DrawUtils(new HexBoard(11));
    draw.setSize(600, 600);
    int hexSize = draw.getHexSize();
    System.out.print(draw.getHexSize());
    Assert.assertTrue(hexSize > 0 && hexSize % 2 == 0);
    SquareView square = new SquareView(new HexBoard(8));
    square.setSize(600, 600);
    int hexSizeSquare = square.getHexSize();
    System.out.print(draw.getHexSize());
    Assert.assertTrue(hexSizeSquare > 0 && hexSizeSquare % 2 == 0);
  }


  @Test
  public void testIsPointInHex() {
    DrawUtils draw = new DrawUtils(new HexBoard(11));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;

    int hexSize = draw.getHexSize();
    boolean isPointInHex = draw.isPointInHex(x, y, x, y, hexSize);
    Assert.assertTrue(isPointInHex);
  }

  @Test
  public void testIsPointInSquare() {
    SquareView draw = new SquareView(new HexBoard(8));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;

    int hexSize = draw.getHexSize();
    boolean isPointInHex = draw.isPointInHex(x, y, x, y, hexSize);
    Assert.assertTrue(isPointInHex);
  }

  @Test
  public void testGetWindowWidth() {
    DrawUtils draw = new DrawUtils(new HexBoard(11));

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
  public void testGetWindowWidthSquare() {
    SquareView draw = new SquareView(new HexBoard(8));

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
    DrawUtils draw = new DrawUtils(new HexBoard(11));
    draw.setSize(600, 600);
    int windowHeight = draw.getWindowHeight();
    Assert.assertEquals(windowHeight, 600);
  }

  @Test
  public void testGetWindowHeightSquare() {
    SquareView draw = new SquareView(new HexBoard(8));
    draw.setSize(600, 600);
    int windowHeight = draw.getWindowHeight();
    Assert.assertEquals(windowHeight, 600);
  }


  @Test
  public void testDraw() {
    draw.setSize(600, 600);
    Assert.assertEquals(new Color(0, 34, 83), draw.getBackground());
  }

  @Test
  public void testDrawSquare() {
    square.setSize(600, 600);
    Assert.assertEquals(new Color(0, 34, 83), square.getBackground());
  }
}