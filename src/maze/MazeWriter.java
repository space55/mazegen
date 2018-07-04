/*******************************************************************************
 * Copyright (C) Eamonn Nugent - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Eamonn Nugent <elg.nugent@gmail.com>, 2016
 *******************************************************************************/

package maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MazeWriter
{

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static ArrayList<String> lines;

	public static String filename = "maze.js";

	public MazeWriter(boolean[][] c_maze)
	{
		init();
		lines.add("var arr = [\n");
		for (int i = 0; i < c_maze.length; i++)
		{
			String line = "[";
			for (int j = 0; j < c_maze[i].length; j++)
			{
				String strspot = "" + c_maze[i][j];
				line += strspot;
				if (j < c_maze[i].length - 1)
				{
					line += ", ";
					if (strspot.length() == 4)
					{
						line += " ";
					}
				}
			}
			line += "]";
			if (i < c_maze.length - 1)
			{
				line += ",\n";
			}
			lines.add(line);
		}
		lines.add("];");
		write();
	}

	public void run()
	{

	}

	public void init()
	{
		lines = new ArrayList<String>();
		try
		{
			// br = new BufferedReader(new FileReader(filename));
			bw = new BufferedWriter(new FileWriter(filename));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write()
	{
		for (String s : lines)
		{
			try
			{
				bw.write(s);
				bw.flush();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void read()
	{
		String temp = "";
		try
		{
			while ((temp = br.readLine()) != null)
			{
				lines.add(temp);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
