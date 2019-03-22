package hkr.da224a.jobshadow.model;

/**
 * The type Business.
 */
public class Business {
    private static final String accountType = "Business";
    private int businessID;
    private String businessName;
    private String password;
    private String hqAddress;
    private long contactPhone;
    private String contactName;
    private String contactEmail;
    private boolean verified;
    private String website;

    /**
     * Instantiates a new Business.
     */
    public Business() {
    }

    /**
     * Instantiates a new Business.
     *
     * @param businessID   the business id
     * @param businessName the business name
     * @param password     the password
     * @param hqAddress    the hq address
     * @param contactPhone the contact phone
     * @param contactName  the contact name
     * @param contactEmail the contact email
     * @param verified     the verified
     * @param website      the website
     */
    public Business(int businessID, String businessName, String password, String hqAddress, long contactPhone,
                    String contactName, String contactEmail, boolean verified, String website) {
        this.businessID = businessID;
        this.businessName = businessName;
        this.password = password;
        this.hqAddress = hqAddress;
        this.contactPhone = contactPhone;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.verified = verified;
        this.website = website;
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
     * Gets business id.
     *
     * @return the business id
     */
    public int getBusinessID() {
        return businessID;
    }

    /**
     * Sets business id.
     *
     * @param businessID the business id
     */
    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    /**
     * Gets business name.
     *
     * @return the business name
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets business name.
     *
     * @param businessName the business name
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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
     * Gets hq address.
     *
     * @return the hq address
     */
    public String getHqAddress() {
        return hqAddress;
    }

    /**
     * Sets hq address.
     *
     * @param hqAddress the hq address
     */
    public void setHqAddress(String hqAddress) {
        this.hqAddress = hqAddress;
    }

    /**
     * Gets contact phone.
     *
     * @return the contact phone
     */
    public long getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets contact phone.
     *
     * @param contactPhone the contact phone
     */
    public void setContactPhone(long contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Gets contact name.
     *
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name.
     *
     * @param contactName the contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets contact email.
     *
     * @return the contact email
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contact email.
     *
     * @param contactEmail the contact email
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Is verified boolean.
     *
     * @return the boolean
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Sets verified.
     *
     * @param verified the verified
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets website.
     *
     * @param website the website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessID='" + businessID + '\'' +
                ", businessName='" + businessName + '\'' +
                ", password='" + password + '\'' +
                ", hqAddress='" + hqAddress + '\'' +
                ", contactPhone=" + contactPhone +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", verified=" + verified +
                ", website='" + website + '\'' +
                '}';
    }
}
