package com.example.module03_basicgui_db_interface;

public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String dept;
    private String major;
    private String course;
    private String studentYear;

    public Person() {
    }

    /**
     * @param id gets the id of the person
     * @param firstName gets the first name of the person
     * @param lastName gets the last name of the person
     * @param dept gets the department of the person
     * @param major gets the major of the person
     * @param course gets the course of the person
     * @param studentYear gets the student year of the person
     */
    public Person(Integer id, String firstName, String lastName, String dept, String major, String course, String studentYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.dept = dept;
        this.course = course;
        this.studentYear = studentYear;
    }

    /**
     * Gets the ID of the person.
     * @return The ID of the person.
     */
    public Integer getId() { return id; }
    /**
     * Sets the ID of the person.
     * @param id The ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the first name of the person.
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the person.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the person.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the major of the person.
     * @return The major of the person.
     */
    public String getMajor() {
        return major;
    }
    /**
     * Sets the major of the person.
     * @param major The major to set.
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Gets the department of the person.
     * @return The department of the person.
     */
    public String getDept() {
        return dept;
    }
    /**
     * Sets the department of the person.
     * @param dept The department to set.
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * Gets the course of the person.
     * @return The course of the person.
     */
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getStudentYear() { return studentYear; }
    public void setStudentYear(String studentYear) { this.studentYear = studentYear; }
}