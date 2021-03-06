package hkr.da224a.jobshadow.model;

import java.sql.Timestamp;

/**
 * The type Application.
 */
public class Application {

    private int applicationID;
    private int studentID;
    private int offerID;
    private Timestamp applicationDate;

    /**
     * Instantiates a new Application.
     */
    public Application() {
    }

    /**
     * Instantiates a new Application.
     *
     * @param applicationID   the application id
     * @param studentID       the student id
     * @param offerID         the offer id
     * @param applicationDate the application date
     */
    public Application(int applicationID, int studentID, int offerID, Timestamp applicationDate) {
        this.applicationID = applicationID;
        this.studentID = studentID;
        this.offerID = offerID;
        this.applicationDate = applicationDate;
    }

    /**
     * Gets application id.
     *
     * @return the application id
     */
    public int getApplicationID() {
        return applicationID;
    }

    /**
     * Sets application id.
     *
     * @param applicationID the application id
     */
    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
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
     * Gets offer id.
     *
     * @return the offer id
     */
    public int getOfferID() {
        return offerID;
    }

    /**
     * Sets offer id.
     *
     * @param offerID the offer id
     */
    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    /**
     * Gets application date.
     *
     * @return the application date
     */
    public Timestamp getApplicationDate() {
        return applicationDate;
    }

    /**
     * Sets application date.
     *
     * @param applicationDate the application date
     */
    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     *
     * @return a string representation of object
     */
    @Override
    public String toString() {
        return "Application{" +
                "applicationID=" + applicationID +
                ", studentID=" + studentID +
                ", offerID=" + offerID +
                ", applicationDate=" + applicationDate +
                '}';
    }
}
