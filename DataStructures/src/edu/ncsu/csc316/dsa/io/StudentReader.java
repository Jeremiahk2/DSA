package edu.ncsu.csc316.dsa.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import edu.ncsu.csc316.dsa.data.Student;

/**
 * StudentReader processes input CSV files that contain
 * student information.
 * 
 * Input CSV files should be in the following format:
 * 
 *    FIRST_NAME,LAST_NAME,UNITY_ID,STUDENT_ID,GPA,CREDIT_HOURS
 * 
 * @author Dr. King
 *
 */
public class StudentReader {

	/**
	 * Returns the input CSV file as an array of Student objects
	 * @param filePath - the path to the input CSV file
	 * @return an array of Student objects
	 */
	public static Student[] readInputAsArray(String filePath)
	{
		Student[] list = new Student[10];
		try(Scanner scan = new Scanner(new FileInputStream(filePath), "UTF8"))
		{
			scan.nextLine(); // SKIP HEADER LINE
			int index = 0;
			while(scan.hasNextLine())
			{
				if(index >= list.length)
				{
					list = Arrays.copyOf(list, list.length * 2 + 1);
				}
				list[index] = processLine(scan.nextLine());
				index++;
			}
			list = Arrays.copyOf(list, index);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + e.getMessage());
		}
		return list;
	}

	/**
	 * Processes a single line from the input file to construct a Student.
	 * @param line - the input line from the input file
	 * @return a Student representation of the input line
	 */
	private static Student processLine(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String first = lineScanner.next();
		String last = lineScanner.next();
		String unityID = lineScanner.next();
		int id = lineScanner.nextInt();
		double gpa = lineScanner.nextDouble();
		int hours = lineScanner.nextInt();
		lineScanner.close();
		return new Student(first, last, id, hours, gpa, unityID);
	}
}
