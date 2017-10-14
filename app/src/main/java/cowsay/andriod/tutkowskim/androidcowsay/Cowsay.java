package cowsay.andriod.tutkowskim.androidcowsay;

/**
 * Java implementation of cowsay
 */
public class Cowsay {

    /**
     * Create ASCII art of a cowsaying the supplied text
     * @param text Text for the cow to say
     * @return Cowsay ASCII art
     */
    public static String say(String text) {
        String cowsay = "";

        // Determine quote line length
        int longestLineLength = -1;
        final String[] textLines = text.replace("\r", "").split("\n");
        for (String line : textLines) {
            int lineLength = line.length();
            if (lineLength > longestLineLength) {
                longestLineLength = lineLength;
            }
        }

        // Draw top of bubble text
        cowsay += " ";
        for (int i = 0; i < longestLineLength; ++i) {
            cowsay += '_';
        }
        cowsay += '\n';

        // Draw Quote
        for (String line : textLines) {
            cowsay += "(";
            cowsay += line;

            int padding = longestLineLength - line.length();
            for (int i = 0; i < padding; ++i) {
                cowsay += " ";
            }

            cowsay += ")\n";
        }

        // Draw bottom of bubble
        cowsay += " ";
        for (int i = 0; i < longestLineLength; ++i) {
            cowsay += '-';
        }
        cowsay += '\n';

        // Draw cow
        cowsay += "    \\   ^__^\n";
        cowsay += "     \\  (oo)\\_______\n";
        cowsay += "        (__)\\       )\\/\\\n";
        cowsay += "            ||----w |\n";
        cowsay += "            ||     ||\n";

        return cowsay;
    }
}
