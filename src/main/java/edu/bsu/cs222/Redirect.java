package edu.bsu.cs222;

class Redirect {
    private String originalSearchTerm;
    private String redirectSearchTerm;
    private boolean isRedirected;

    Redirect(String originalSearchTerm, String redirectSearchTerm) {
        this.originalSearchTerm = originalSearchTerm;
        this.redirectSearchTerm = redirectSearchTerm;
        this.isRedirected = originalSearchTerm != null && redirectSearchTerm != null;
    }

    String getOriginalSearchTerm() { return originalSearchTerm; }

    String getRedirectSearchTerm() { return redirectSearchTerm; }

    boolean isRedirected() {
        return isRedirected;
    }
}
