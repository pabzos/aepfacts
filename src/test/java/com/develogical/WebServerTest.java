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

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessPlusPlus() throws Exception {
        assertEquals("23", server.process("8a9a2c70: what is 8 plus 14 plus 1"));
    }

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessMinus() throws Exception {
        assertEquals("6", server.process("8a9a2c70: what is 8 minus 2"));
    }

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessMultiply() throws Exception {
        assertEquals("38", server.process("b4a119b0: what is 19 multiplied by 2"));
    }

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessPrim() throws Exception {
        assertEquals("1, 3, 5, 7", server.process("22a41050: which of the following numbers are primes: 1, 3, 5, 6, 7"));
    }

    /**
     * Ensure that a 'bad' request gets "Unknown" as a response.
     */
    @org.junit.Test
    public void testProcessPower() throws Exception {
        assertEquals("4", server.process("f8cde210: what is 2 to the power of 2"));
    }


}
