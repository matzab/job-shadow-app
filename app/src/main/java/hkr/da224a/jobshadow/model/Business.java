package hkr.da224a.jobshadow.model;

public class Business extends User {
    String businessID;
    String businessName;
    String password;
    String hqAddress;
    int contactphone;
    String contactName;
    String conctactEmail;
    String website;
    boolean verified;
    String dateCreated;

    public Business() {
    }

    public Business(String businessID, String businessName, String password, String hqAddress,
                    int contactphone, String contactName, String conctactEmail, String website,
                    boolean verified, String dateCreated) {
        this.businessID = businessID;
        this.businessName = businessName;
        this.password = password;
        this.hqAddress = hqAddress;
        this.contactphone = contactphone;
        this.contactName = contactName;
        this.conctactEmail = conctactEmail;
        this.website = website;
        this.verified = verified;
        this.dateCreated = dateCreated;
    }

    public String getBusinessID() {
        return businessID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getPassword() {
        return password;
    }

    public String getHqAddress() {
        return hqAddress;
    }

    public int getContactphone() {
        return contactphone;
    }

    public String getContactName() {
        return contactName;
    }

    public String getConctactEmail() {
        return conctactEmail;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getDateCreated() {
        return dateCreated;
    }
}
