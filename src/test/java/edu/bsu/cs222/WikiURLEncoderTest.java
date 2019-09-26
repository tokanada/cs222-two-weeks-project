package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class WikiURLEncoderTest {

    @Test
    public void testWikiURLEncoderWithSpace() {
        String encodeURL = WikiURLEncoder.encode("Frank Zappa");
        Assert.assertEquals(encodeURL, "Frank+Zappa");
    }
}
