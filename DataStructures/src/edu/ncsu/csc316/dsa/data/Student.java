package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Jeremiah Knizley
 *
 */
public class Student implements Comparable<Student> {
	/** The student's first name */
	private String first;
	/** the student's last name */
	private String last;
	/** the student's id */
	private int id;
	/** the student's number of credit hours */
	private int creditHours;
	/** the student's gpa */
	private double gpa;
	/** the student's Unity ID */
	private String unityID;
	/**
	 * Constructor for Student objects, which represent a Student in the system.
	 * @param first the student's first name
	 * @param last the student's last name
	 * @param id the student's id
	 * @param creditHours the student's number of credit hours
	 * @param gpa the student's gpa
	 * @param unityID the student's unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}
	/**
	 * returns the first name of the student
	 * @return the first name of the student
	 */
	public String getFirst() {
		return first;
	}
	/**
	 * sets the first name of the student
	 * @param first the first name to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	/**
	 * returns the last name of the student
	 * @return the last name of the student
	 */
	public String getLast() {
		return last;
	}
	/**
	 * sets the last name of the student
	 * @param last the last name to set
	 */
	public void setLast(String last) {
		this.last = last;
	}
	/**
	 * returns the numerical ID of the student
	 * @return id the numerical ID of the student
	 */
	public int getId() {
		return id;
	}
	/**
	 * sets the ID of the student
	 * @param id the ID of the student
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * returns the number of credit hours the student has
	 * @return creditHours the number of credit hours the student has.
	 */
	public int getCreditHours() {
		return creditHours;
	}
	/**
	 * sets the number of credit hours the student has.
	 * @param creditHours the number of credit hours the student has
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	/**
	 * returns the GPA of the student
	 * @return gpa the GPA of the student
	 */
	public double getGpa() {
		return gpa;
	}
	/**
	 * sets the GPA the student has
	 * @param gpa the GPA the student has
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	/**
	 * returns the student's unityID
	 * @return the student's unityID
	 */
	public String getUnityID() {
		return unityID;
	}
	/**
	 * sets the Unity ID of the student
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	
	/**
	 * Default override of hashCode using all fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}
	/**
	 * Overrides equals in order to compare two students on the basis of having the same first name, last name, and ID
	 * @param obj the Object to be tested for equality with this Student
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}
	
	/**
	 * Compares student objects. Compares the last names of students alphabetically first.
	 * If last names are equal, first names are compared. 
	 * If first names are equal, ID is compared.
	 * If this student comes before student o, -1 is returned.
	 * If this student comes after student 0, 1 is returned.
	 * If this student is the same as student o, 0 is returned. 
	 */
	@Override
	public int compareTo(Student o) {
		if (equals(o)) {
			return 0;
		}
		if (last.compareTo(o.getLast()) < 0) {
			return -1;
		}
		else if (last.compareTo(o.getLast()) > 0) {
			return 1;
		}
		if (first.compareTo(o.getFirst()) < 0) {
			return -1;
		}
		else if (first.compareTo(o.getFirst()) > 0) {
			return 1;
		}
		if (id < o.getId()) {
			return -1;
		}
		return 1;
	}
	/**
	 * Returns a string representation of the Student
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}
	
	
}
