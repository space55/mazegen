package maze;

import java.util.Random;

public class MazeGenerator
{
	protected boolean[][] maze;
	protected int x;
	protected int y;
	protected Random rand = new Random();

	protected boolean[][] generateMaze(int c_x, int c_y)
	{
		x = c_x;
		y = c_y;
		maze = new boolean[x][y];
		// Maze.printMaze(maze);
		// setWalls();
		// Maze.printMaze(maze);
		// generateEdge();
		// Maze.printMaze(maze);
		generate();
		// Maze.printMaze(maze);

		return maze;
	}

	private void generate()
	{
		boolean done = false;
		boolean[][] de = new boolean[maze.length][maze[0].length];
		int locx = 1;
		int locy = 1;
		while (!done)
		{
			maze[locx][locy] = true;
			boolean[] canTurn = canTurn(locx, locy);
			if (canTurn[4])
			{
				int dir = getDir(canTurn);
				if (dir == 0)
				{
					locx++;
					maze[locx][locy] = true;
					locx++;
					maze[locx][locy] = true;
				}
				else if (dir == 1)
				{
					locx--;
					maze[locx][locy] = true;
					locx--;
					maze[locx][locy] = true;
				}
				else if (dir == 2)
				{
					locy++;
					maze[locx][locy] = true;
					locy++;
					maze[locx][locy] = true;
				}
				else if (dir == 3)
				{
					locy--;
					maze[locx][locy] = true;
					locy--;
					maze[locx][locy] = true;
				}
				else
				{
					System.out.println("Something Even More Broke!");
				}
			}
			else
			{
				boolean[] canBack = canMove(locx, locy, de);
				if (canBack[4])
				{
					if (canBack[0])
					{
						de[locx][locy] = true;
						++locx;
						de[locx][locy] = true;
						++locx;
					}
					else if (canBack[1])
					{
						de[locx][locy] = true;
						--locx;
						de[locx][locy] = true;
						--locx;
					}
					else if (canBack[2])
					{
						de[locx][locy] = true;
						++locy;
						de[locx][locy] = true;
						++locy;
					}
					else if (canBack[3])
					{
						de[locx][locy] = true;
						--locy;
						de[locx][locy] = true;
						--locy;
					}
					else
					{
						System.out.println("Something Broke!");
					}
				}
				else
				{
					done = true;
				}
			}
			// Maze.printMaze(maze, de);
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void generateEdge()
	{
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				maze[i][(j == 0) ? 0 : y - 1] = false;
			}
		}
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < y; j++)
			{
				maze[(i == 0) ? 0 : x - 1][j] = false;
			}
		}
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void setWalls()
	{
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < x; j++)
			{
				maze[i][j] = true;
			}
		}
	}

	private int getDir(boolean[] a)
	{
		int out = -1;
		while (out == -1)
		{
			int ind = rand.nextInt(4);
			if (a[ind])
			{
				out = ind;
			}
		}
		return out;
	}

	private boolean[] canTurn(int locx, int locy)
	{
		boolean[] out = new boolean[5];
		out[0] = (locx < x - 3) ? !maze[locx + 2][locy] && !maze[locx + 1][locy] : false;
		out[1] = (locx > 2) ? !maze[locx - 2][locy] && !maze[locx - 1][locy] : false;
		out[2] = (locy < y - 3) ? !maze[locx][locy + 2] && !maze[locx][locy + 1] : false;
		out[3] = (locy > 2) ? !maze[locx][locy - 2] && !maze[locx][locy - 1] : false;
		out[4] = out[0] || out[1] || out[2] || out[3];
		return out;
	}

	private boolean[] canMove(int locx, int locy, boolean[][] de)
	{
		boolean[] out = new boolean[5];

		// boolean[] canTurn = canTurn(locx, locy);
		out[0] = ((locx < x - 3) ? !de[locx + 1][locy] : false) && maze[locx + 2][locy] && maze[locx + 1][locy];
		out[1] = ((locx > 2) ? !de[locx - 1][locy] : false) && maze[locx - 2][locy] && maze[locx - 1][locy];
		out[2] = ((locy < y - 3) ? !de[locx][locy + 1] : false) && maze[locx][locy + 2] && maze[locx][locy + 1];
		out[3] = ((locy > 2) ? !de[locx][locy - 1] : false) && maze[locx][locy - 2] && maze[locx][locy - 1];
		out[4] = out[0] || out[1] || out[2] || out[3];
		/*out[0] = (locx < x - 4) ? !de[locx + 1][locy] : false;
		out[1] = (locx > 2) ? !de[locx - 1][locy] : false;
		out[2] = (locy < y - 4) ? !de[locx][locy + 1] : false;
		out[3] = (locy > 2) ? !de[locx][locy - 1] : false;
		out[4] = out[0] || out[1] || out[2] || out[3];*/
		return out;
	}
}
