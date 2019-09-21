package edu.bsu.cs222;

public class Revision {
    private String user;
    private String timestamp;
    private boolean anonymous;

    public Revision(String userElement, String timestampElement, boolean anonymous) {
        this.user = userElement;
        this.timestamp = timestampElement;
        this.anonymous = anonymous;
    }

    public String getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isAnonymous() {
        return anonymous;
    }
}
