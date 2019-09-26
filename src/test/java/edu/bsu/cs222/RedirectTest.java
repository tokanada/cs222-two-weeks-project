package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class RedirectTest {
    @Test
    public void testGetRedirectSearchTerm() {
        Redirect redirect = new Redirect("Zappa", "Frank Zappa");
        Assert.assertEquals("Frank Zappa", redirect.getRedirectSearchTerm());
    }

    @Test
    public void testGetOriginalSearchTerm() {
        Redirect redirect = new Redirect("Zappa", "Frank Zappa");
        Assert.assertEquals("Zappa", redirect.getOriginalSearchTerm());
    }
}
