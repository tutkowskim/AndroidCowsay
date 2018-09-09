package cowsay.andriod.tutkowskim.androidcowsay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Main activity
 */
public class MainActivity extends AppCompatActivity {
    private String cowsayMessage = "";

    /**
     * Setup action listeners on create
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputText = findViewById(R.id.inputText);
        final TextView outputText = findViewById(R.id.outputText);

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

        // Set the initial cowsay value
        updateCowsayMessage(inputText, outputText);

        // Don't hide the cowsay text when the keyboard is visible
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * Prepare the menu options
     *
     * @param menu Menu to prepare
     * @return Returns true if successful
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    /**
     * Handle a selected option.
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share_as_text:
                shareAsText();
                return true;
            case R.id.share_as_image:
                shareAsImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Update the outtext with the cowsay output containing the input text
     *
     * @param inputText  Text for the cow to say
     * @param outputText Text field to update
     */
    private void updateCowsayMessage(EditText inputText, TextView outputText) {
        if (inputText != null && outputText != null) {
            String message = inputText.getText().toString();
            this.cowsayMessage = Cowsay.say(message);
            outputText.setText(cowsayMessage);
        }
    }

    private void shareAsText() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, cowsayMessage);
        startActivity(Intent.createChooser(shareIntent, "Share cowsay text using"));
    }

    private void shareAsImage() {
        File imageDir = new File(getCacheDir(), "images");
        File image = new File(imageDir, "image.png");

        // Create the directory if it doesn't exist
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        // Save the image to the cache directory
        final TextView textView = findViewById(R.id.outputText);
        textView.setDrawingCacheEnabled(true);
        Bitmap tvImage = Bitmap.createBitmap(textView.getDrawingCache());
        try {
            tvImage.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(image));
        } catch (FileNotFoundException e) {
            // The cache directory should always exist, so this should never occur.
            return;
        }
        textView.setDrawingCacheEnabled(false);

        // Send intent to share the image
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri imageUri = FileProvider.getUriForFile(this, "cowsay.andriod.tutkowskim.androidcowsay.fileprovider", image);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(shareIntent, "Share cowsay image using"));
    }
}
