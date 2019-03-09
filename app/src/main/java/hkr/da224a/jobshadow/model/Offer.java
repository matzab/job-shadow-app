package hkr.da224a.jobshadow.model;

import java.sql.Timestamp;

/**
 * The type Offer.
 */
public class Offer {

    private int offerID;
    private int businessID;
    private int categoryID;
    private String offerTitle;
    private String offerLength;
    private String offerPositionQuals;
    private String offerLocation;
    private String description;
    private Timestamp dateCreated;

    /**
     * Instantiates a new Offer.
     */
    public Offer() {
    }

    /**
     * Instantiates a new Offer.
     *
     * @param offerID            the offer id
     * @param businessID         the business id
     * @param categoryID         the category id
     * @param offerTitle         the offer title
     * @param offerLength        the offer length
     * @param offerPositionQuals the offer position quals
     * @param offerLocation      the offer location
     * @param description        the description
     * @param dateCreated        the date created
     */
    public Offer(int offerID, int businessID, int categoryID, String offerTitle, String offerLength,
                 String offerPositionQuals, String offerLocation, String description, Timestamp dateCreated) {
        this.offerID = offerID;
        this.businessID = businessID;
        this.categoryID = categoryID;
        this.offerTitle = offerTitle;
        this.offerLength = offerLength;
        this.offerPositionQuals = offerPositionQuals;
        this.offerLocation = offerLocation;
        this.description = description;
        this.dateCreated = dateCreated;
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
     * Gets category id.
     *
     * @return the category id
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Sets category id.
     *
     * @param categoryID the category id
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Gets offer title.
     *
     * @return the offer title
     */
    public String getOfferTitle() {
        return offerTitle;
    }

    /**
     * Sets offer title.
     *
     * @param offerTitle the offer title
     */
    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    /**
     * Gets offer length.
     *
     * @return the offer length
     */
    public String getOfferLength() {
        return offerLength;
    }

    /**
     * Sets offer length.
     *
     * @param offerLength the offer length
     */
    public void setOfferLength(String offerLength) {
        this.offerLength = offerLength;
    }

    /**
     * Gets offer position quals.
     *
     * @return the offer position quals
     */
    public String getOfferPositionQuals() {
        return offerPositionQuals;
    }

    /**
     * Sets offer position quals.
     *
     * @param offerPositionQuals the offer position quals
     */
    public void setOfferPositionQuals(String offerPositionQuals) {
        this.offerPositionQuals = offerPositionQuals;
    }

    /**
     * Gets offer location.
     *
     * @return the offer location
     */
    public String getOfferLocation() {
        return offerLocation;
    }

    /**
     * Sets offer location.
     *
     * @param offerLocation the offer location
     */
    public void setOfferLocation(String offerLocation) {
        this.offerLocation = offerLocation;
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

    /**
     * Gets date created.
     *
     * @return the date created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets date created.
     *
     * @param dateCreated the date created
     */
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *
     * @return string representation of object
     */
    @Override
    public String toString() {
        return "Offer{" +
                "offerID=" + offerID +
                ", businessID=" + businessID +
                ", categoryID=" + categoryID +
                ", offerTitle='" + offerTitle + '\'' +
                ", offerLength='" + offerLength + '\'' +
                ", offerPositionQuals='" + offerPositionQuals + '\'' +
                ", offerLocation='" + offerLocation + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
