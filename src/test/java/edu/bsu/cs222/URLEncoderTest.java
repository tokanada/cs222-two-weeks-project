package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class URLEncoderTest {

    @Test
    public void testWikiURLEncoderWithSpace() {
        String encodeURL = WikiURLEncoder.encode("Frank Zappa");
        Assert.assertEquals(encodeURL, "Frank+Zappa");
    }
}
