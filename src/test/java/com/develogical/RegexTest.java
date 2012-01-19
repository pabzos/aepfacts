package com.develogical;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class RegexTest {

    @Test
    public void usingRegex() {

        String str = "My favourite numbers are 7 and 23";

        String regex = "My favourite numbers are (\\d+) and (\\d+)";

        Matcher matcher = Pattern.compile(regex).matcher(str);

        if (matcher.matches()) {
            assertEquals("7", matcher.group(1));
            assertEquals("23", matcher.group(2));
        } else {
            fail("Should have matched.");
        }
    }

    @Test
    public void splittingOnSpaces() {

        String str = "My favourite numbers are 7 and 23";

        String[] strings = str.split(" ");

        assertEquals("7", strings[4]);
        assertEquals("23", strings[6]);
    }

}