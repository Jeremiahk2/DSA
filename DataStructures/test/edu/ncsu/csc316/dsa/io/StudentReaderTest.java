package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

public class StudentReaderTest {
	
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber",contents[0].getFirst());
		assertEquals("Ara",contents[1].getFirst());
		assertEquals("Lacie",contents[2].getFirst());
		assertEquals("Idalia",contents[3].getFirst());
		assertEquals("Evelin",contents[4].getFirst());
		assertEquals("Lewis",contents[5].getFirst());
		assertEquals("Alicia",contents[6].getFirst());
		assertEquals("Tyree",contents[7].getFirst());
		assertEquals("Loise",contents[8].getFirst());
		assertEquals("Roxann",contents[9].getFirst());
		assertEquals("Nichole",contents[10].getFirst());
		assertEquals("Charlene",contents[11].getFirst());
		assertEquals("Shanti",contents[12].getFirst());
		assertEquals("Cristine",contents[13].getFirst());
		assertEquals("Tanner",contents[14].getFirst());
		assertEquals("Dante",contents[15].getFirst());
	}
	
	// TODO: Add test cases
	// Suggestions:
	// -> Test that each line processes the data in the correct order
}
