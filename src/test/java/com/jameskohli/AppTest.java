package com.jameskohli;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testExtractor() {
        try {
            Extractor e = new Extractor(getClass().getResource("/pdf-sample.pdf"));
            assertNotNull(e.getText());
            LOGGER.info(e.getText());
        } catch (Exception e) {
            LOGGER.error("Error running Extractor test", e);
        }
    }

    public void testTextParserRead() {

        TextParser tp = new TextParser("Kohli..........James");
        assertEquals("Kohli,James", tp.getText());
    }
}
