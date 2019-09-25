package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class RevisionTest {
    @Test
    public void testRevision() {
        Revision revision = new Revision("User", "2019-09-14T21:14:54Z", false);
        Assert.assertEquals("User", revision.getUser());
        Assert.assertEquals("2019-09-14T21:14:54Z", revision.getTimestamp());
        Assert.assertEquals(false, revision.isAnonymous());
    }

    @Test
    public void testRedirectRevision() {
        Revision redirectRevision = new Revision("Zappa", "Frank Zappa");
        Assert.assertEquals("");
    }
}
