package cowsay.andriod.tutkowskim.androidcowsay;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CowsayTest {
    @Test
    public void testSingleLineMessage() {
        final String messageToSay = "Moo!";
        final String expectedValue = " ____\n" +
                "(Moo!)\n" +
                " ----\n" +
                "    \\   ^__^\n" +
                "     \\  (oo)\\_______\n" +
                "        (__)\\       )\\/\\\n" +
                "            ||----w |\n" +
                "            ||     ||\n";

        assertEquals(expectedValue, Cowsay.say(messageToSay));
    }

    @Test
    public void testMultipleLineMessage() {
        final String messageToSay = "Moo!\nI'm a cow.";
        final String expectedValue = " __________\n" +
                "(Moo!      )\n" +
                "(I'm a cow.)\n" +
                " ----------\n" +
                "    \\   ^__^\n" +
                "     \\  (oo)\\_______\n" +
                "        (__)\\       )\\/\\\n" +
                "            ||----w |\n" +
                "            ||     ||\n";

        assertEquals(expectedValue, Cowsay.say(messageToSay));
    }
}