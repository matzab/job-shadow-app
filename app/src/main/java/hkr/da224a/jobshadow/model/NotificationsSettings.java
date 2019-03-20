package hkr.da224a.jobshadow.model;

/**
 * The type Notifications settings.
 */
public class NotificationsSettings {

    private String messageNotifications;
    private String ringtoneName;
    private String vibration;

    public NotificationsSettings() {
    }

    public NotificationsSettings(String messageNotifications, String ringtoneName, String vibration) {
        this.messageNotifications = messageNotifications;
        this.ringtoneName = ringtoneName;
        this.vibration = vibration;
    }

    public String getMessageNotifications() {
        return messageNotifications;
    }

    public void setMessageNotifications(String messageNotifications) {
        this.messageNotifications = messageNotifications;
    }

    public String getRingtoneName() {
        return ringtoneName;
    }

    public void setRingtoneName(String ringtoneName) {
        this.ringtoneName = ringtoneName;
    }

    public String getVibration() {
        return vibration;
    }

    public void setVibration(String vibration) {
        this.vibration = vibration;
    }
}
