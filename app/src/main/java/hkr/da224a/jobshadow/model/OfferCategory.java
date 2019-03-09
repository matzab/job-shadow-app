package hkr.da224a.jobshadow.model;

/**
 * The type Offer category.
 */
public class OfferCategory {

    private int categoryID;
    private String categoryName;

    /**
     * Instantiates a new Offer category.
     */
    public OfferCategory() {
    }

    /**
     * Instantiates a new Offer category.
     *
     * @param categoryID   the category id
     * @param categoryName the category name
     */
    public OfferCategory(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
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
     * Gets category name.
     *
     * @return the category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets category name.
     *
     * @param categoryName the category name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     *
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return "OfferCategory{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
