package LRS2_0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SequenceNum
{

	public static String nextSequenceNumber()
	{
		try
		{
			String next = readFromFile();
			// System.out.println("number"+next);
			int value = 0;
			if (next != null)
			{
				value = Integer.parseInt(next) + 1;
				// System.out.println("next number" +value);
				writetoFileAfterIncreament(value);
			}
			return String.valueOf(value);
		}
		catch (IOException e)
		{
			System.out.println("error in nextSequenceNumber() : "
								+ e.getMessage());
			e.printStackTrace();
		}
		return "0";
	} // End of nextSequenceNumber() method

	public static String currentSequenceNumber()
	{

		String current = readFromFile();
		// System.out.println("number"+next);

		return String.valueOf(current);

	} // End of nextSequenceNumber() method

	public static void writetoFileAfterIncreament(int value) throws IOException
	{
		String outputFileName = "sequence.txt";
		FileWriter outputFileReader = new FileWriter(outputFileName);
		PrintWriter outputStream = new PrintWriter(outputFileReader);
		outputStream.println(String.valueOf(value));

		outputStream.close();
	}

	public static String readFromFile()
	{
		String inLine = null;
		try
		{
			// input/output file names
			String inputFileName = "sequence.txt";
			// Create FileReader Object
			FileReader inputFileReader = new FileReader(inputFileName);
			// Create Buffered/PrintWriter Objects
			BufferedReader inputStream = new BufferedReader(inputFileReader);
			inLine = inputStream.readLine();
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return inLine;
	}

} // End of FileUtil class

