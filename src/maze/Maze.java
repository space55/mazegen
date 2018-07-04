package maze;

import java.util.Scanner;

public class Maze
{
	public Scanner in = new Scanner(System.in);

	public char wall = (char) 219;
	public char dend = (char) 176;

	protected MazeGenerator mg;

	protected boolean[][] maze;
	private static Maze o_maze;

	private static final boolean USE_GUI = true;

	public static void main(String[] args)
	{
		o_maze = new Maze();
		o_maze.init();
	}

	public Maze()
	{
		init();
	}

	private void init()
	{
		int x = 41;
		int y = 41;
		mg = new MazeGenerator();

		maze = mg.generateMaze(x, y);
		if (USE_GUI)
		{
			MazeGui.init(maze);
		}
		else
		{
			new MazeWriter(maze);
		}
		// printMaze(maze);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void printMaze(boolean[][] maze)
	{
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				System.out.print(maze[i][j] ? " " : "#");
			}
			System.out.println();
		}
		System.out.println();
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void printMaze(boolean[][] maze, boolean[][] de)
	{
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				System.out.print(maze[i][j] ? (de[i][j] ? dend : " ") : wall);
			}
			System.out.println();
		}
		System.out.println();
	}

	protected void waitF()
	{
		in.nextLine();
	}

	public boolean movePiece(Direction dir)
	{
		return MazeGui.movePiece(dir);
	}

	public boolean foundGoal()
	{
		return MazeGui.foundGoal();
	}

	public boolean[][] getMaze()
	{
		return maze;
	}
}
