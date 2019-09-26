package edu.bsu.cs222;

class Revision {
    private String user;
    private String timestamp;
    private boolean anonymous;


    Revision(String userElement, String timestampElement, boolean anonymous) {
        this.user = userElement;
        this.timestamp = timestampElement;
        this.anonymous = anonymous;
    }

    String getUser() {
        return user;
    }

    String getTimestamp() {
        return timestamp;
    }

    boolean isAnonymous() {
        return anonymous;
    }

}
