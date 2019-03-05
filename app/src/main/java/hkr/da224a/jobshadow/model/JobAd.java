package hkr.da224a.jobshadow.model;

public class JobAd {
    String offerID;
    String offerTitle;
    String offerLength;
    String offerPositionQuals;
    String dateCreated;
    String offerLocation;
    String description;

    public JobAd() {
    }

    public JobAd(String offerID, String offerTitle, String offerLength, String offerPositionQuals,
                 String dateCreated, String offerLocation, String description) {
        this.offerID = offerID;
        this.offerTitle = offerTitle;
        this.offerLength = offerLength;
        this.offerPositionQuals = offerPositionQuals;
        this.dateCreated = dateCreated;
        this.offerLocation = offerLocation;
        this.description = description;
    }

    public String getOfferID() {
        return offerID;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public String getOfferLength() {
        return offerLength;
    }

    public String getOfferPositionQuals() {
        return offerPositionQuals;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getOfferLocation() {
        return offerLocation;
    }

    public String getDescription() {
        return description;
    }
}
