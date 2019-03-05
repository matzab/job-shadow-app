package hkr.da224a.jobshadow.model;

public class Student extends User {
    String studentID;
    String password;
    String firstname;
    String Lastname;
    int phone;
    String email;
    String city;
    String dob;
    String emerencyContact;
    int emergencyContactphone;
    String degreeProgram;
    String searchPrefs;
    String description;
    String dateCreated;

    public Student() {
    }

    public Student(String studentID, String password, String firstname, String lastname, int phone,
                   String email, String city, String dob, String emerencyContact,
                   int emergencyContactphone, String degreeProgram, String searchPrefs,
                   String description, String dateCreated) {
        this.studentID = studentID;
        this.password = password;
        this.firstname = firstname;
        Lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.dob = dob;
        this.emerencyContact = emerencyContact;
        this.emergencyContactphone = emergencyContactphone;
        this.degreeProgram = degreeProgram;
        this.searchPrefs = searchPrefs;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getDob() {
        return dob;
    }

    public String getEmerencyContact() {
        return emerencyContact;
    }

    public int getEmergencyContactphone() {
        return emergencyContactphone;
    }

    public String getDegreeProgram() {
        return degreeProgram;
    }

    public String getSearchPrefs() {
        return searchPrefs;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
