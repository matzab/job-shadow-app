package hkr.da224a.jobshadow.model;

import java.io.Serializable;

/**
 * The type Student.
 */
public class Student implements Serializable {
    private static final String accountType = "Student";
    private int studentID;
    private String password;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String emailAddress;
    private String city;
    private String dateOfBirth;
    private long inCaseOfEmergency;
    private String degreeProgram;
    private String searchPrefs;
    private String description;

    /**
     * Instantiates a new Student.
     */
    public Student() {
    }

    /**
     * Instantiates a new Student.
     *
     * @param studentID         the student id
     * @param password          the password
     * @param firstName         the first name
     * @param lastName          the last name
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param city              the city
     * @param dateOfBirth       the date of birth
     * @param inCaseOfEmergency the in case of emergency
     * @param degreeProgram     the degree program
     * @param searchPrefs       the search prefs
     * @param description       the description
     */
    public Student(int studentID, String password, String firstName, String lastName, long phoneNumber,
                   String emailAddress, String city, String dateOfBirth, long inCaseOfEmergency,
                   String degreeProgram, String searchPrefs, String description) {
        this.studentID = studentID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.inCaseOfEmergency = inCaseOfEmergency;
        this.degreeProgram = degreeProgram;
        this.searchPrefs = searchPrefs;
        this.description = description;
    }


    /**
     * Gets account type.
     *
     * @return the account type
     */
    public static String getAccountType() {
        return accountType;
    }

    /**
     * Gets student id.
     *
     * @return the student id
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Sets student id.
     *
     * @param studentID the student id
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets in case of emergency.
     *
     * @return the in case of emergency
     */
    public long getInCaseOfEmergency() {
        return inCaseOfEmergency;
    }

    /**
     * Sets in case of emergency.
     *
     * @param inCaseOfEmergency the in case of emergency
     */
    public void setInCaseOfEmergency(long inCaseOfEmergency) {
        this.inCaseOfEmergency = inCaseOfEmergency;
    }

    /**
     * Gets degree program.
     *
     * @return the degree program
     */
    public String getDegreeProgram() {
        return degreeProgram;
    }

    /**
     * Sets degree program.
     *
     * @param degreeProgram the degree program
     */
    public void setDegreeProgram(String degreeProgram) {
        this.degreeProgram = degreeProgram;
    }

    /**
     * Gets search prefs.
     *
     * @return the search prefs
     */
    public String getSearchPrefs() {
        return searchPrefs;
    }

    /**
     * Sets search prefs.
     *
     * @param searchPrefs the search prefs
     */
    public void setSearchPrefs(String searchPrefs) {
        this.searchPrefs = searchPrefs;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", city='" + city + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", inCaseOfEmergency=" + inCaseOfEmergency +
                ", degreeProgram='" + degreeProgram + '\'' +
                ", searchPrefs='" + searchPrefs + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
