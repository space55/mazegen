package maze;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeGui extends JPanel
{
	// Formulas, graphics, logic written by Daniel
	// Small visibility modifications by Eamonn
	private static final long serialVersionUID = 1L;
	private Color wall = Color.black;
	private Color path = Color.gray;
	private Color piece = Color.red;
	private Color goal = Color.yellow;

	private static MazeGui gui;
	private static JFrame frame;

	private static int lenx;
	private static int leny;
	private boolean[][] maze;
	private int pieceX;
	private int pieceY;

	private int goalX;
	private int goalY;

	protected static void init(boolean[][] c_maze)
	{
		lenx = c_maze.length;
		leny = c_maze[0].length;
		gui = new MazeGui(c_maze);
		frame = new JFrame();
		frame.setSize(lenx * 20, leny * 20);
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		movePiece(Direction.DOWN);
	}

	private MazeGui(boolean[][] c_maze)
	{
		int pX = 1;
		int pY = 0;
		int gX = lenx - 2;
		int gY = leny - 1;
		maze = c_maze;
		pieceX = pX;
		pieceY = pY;
		goalX = gX;
		goalY = gY;
		// setVisible(true);
		// setSize(lenx * 20, leny * 20);
	}

	protected static boolean movePiece(Direction dir)
	{
		return gui.movePieceI(dir);
	}

	protected static boolean foundGoal()
	{
		return gui.foundGoalI();
	}

	protected boolean movePieceI(Direction dir)
	{
		switch (dir)
		{
		case DOWN:
			if (!maze[pieceX][pieceY + 1])
			{
				pieceY++;
				return true;
			}
			break;
		case UP:
			if (!maze[pieceX][pieceY - 1])
			{
				pieceY--;
				return true;
			}
			break;
		case LEFT:
			if (!maze[pieceX - 1][pieceY])
			{
				pieceX--;
				return true;
			}
			break;
		case RIGHT:
			if (!maze[pieceX + 1][pieceY])
			{
				pieceX++;
				return true;
			}
			break;
		}
		return false;
	}

	protected boolean foundGoalI()
	{
		return pieceX == goalX && pieceY == goalY;

	}

	protected void paintComponent(Graphics g)
	{
		int h = this.getHeight() / maze.length;
		int w = this.getWidth() / maze[0].length;

		// System.out.println(h);
		// System.out.println(w);
		for (int x = 0; x < maze.length; x++)
		{
			for (int y = 0; y < maze[0].length; y++)
			{
				if (maze[x][y])
				{
					g.setColor(wall);
				}
				else
				{
					g.setColor(path);
				}
				// System.out.println(w * x);
				// System.out.println(h * y);
				g.fillRect(w * x, h * y, h, w);
			}
		}
		g.setColor(piece);
		g.fillRect(w * pieceX, h * pieceY, w, h);
		g.setColor(goal);
		g.fillRect(w * goalX, h * goalY, w, h);
	}
}
