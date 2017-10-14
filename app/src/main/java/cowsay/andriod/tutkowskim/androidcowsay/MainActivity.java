package cowsay.andriod.tutkowskim.androidcowsay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputText = (EditText) findViewById(R.id.inputText);
        final TextView outputText = (TextView) findViewById(R.id.outputText);
        final Button copyTextButton = (Button) findViewById(R.id.copyTextButton);

        // Setup a listener to display the cowsay text
        if (inputText != null) {
            inputText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // Intentionally left blank
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // Intentionally left blank
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    updateCowsayMessage(inputText, outputText);
                }
            });
        }

        // Setup the copy text button
        if (copyTextButton != null) {
            copyTextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    clipboard.setPrimaryClip(ClipData.newPlainText(inputText.getText(), cowsay(inputText.getText().toString())));
                }
            });
        }

        // Set the initial cowsay value
        updateCowsayMessage(inputText, outputText);
    }

    private void updateCowsayMessage(EditText inputText, TextView outputText) {
        if (inputText != null && outputText != null) {
            String message = inputText.getText().toString();
            String cowsayMessage = cowsay(message);
            outputText.setText(cowsayMessage);
        }
    }

    private String cowsay(String text) {
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
