package cowsay.andriod.tutkowskim.androidcowsay;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main activity
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Setup action listeners on create
     * @param savedInstanceState
     */
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
                    String inputTextValue = inputText.getText().toString();
                    String cowsay = Cowsay.say(inputTextValue);

                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    clipboard.setPrimaryClip(ClipData.newPlainText(inputTextValue, cowsay));
                }
            });
        }

        // Set the initial cowsay value
        updateCowsayMessage(inputText, outputText);

        // Don't hide the cowsay text when the keyboard is visible
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * Update the outtext with the cowsay output containing the input text
     * @param inputText Text for the cow to say
     * @param outputText Text field to update
     */
    private void updateCowsayMessage(EditText inputText, TextView outputText) {
        if (inputText != null && outputText != null) {
            String message = inputText.getText().toString();
            String cowsayMessage = Cowsay.say(message);
            outputText.setText(cowsayMessage);
        }
    }
}
