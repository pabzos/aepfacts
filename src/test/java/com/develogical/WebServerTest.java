package com.develogical;

import static junit.framework.Assert.assertEquals;

public class WebServerTest {
    private WebServer server = new WebServer();

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessUnknown() throws Exception {
        assertEquals("Unknown", server.process("74e9afc0: What is my favourite colour?"));
    }


    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessLargestNumber() throws Exception {
        assertEquals("503", server.process("74e9afc0: which of the following numbers is the largest: 60, 503"));
    }

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessPlus() throws Exception {
        assertEquals("22", server.process("8a9a2c70: what is 8 plus 14"));
    }

}
