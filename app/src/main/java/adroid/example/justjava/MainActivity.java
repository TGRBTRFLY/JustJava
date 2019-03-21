package adroid.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show error message as toast
            Toast.makeText(this, "You cannot order more than 100 cups", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0)
            quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value between the - and + buttons.
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }

    /**
     * This method is called when the ODER button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = findViewById(R.id.name_field); //User entered name
        String name = nameField.getText().toString();
        Log.v("MainActivity", "Name: " + name);

        //Does customer want whipped cream?
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        //Figure out if the user wants cinnamon topping
        CheckBox cinnamonCheckBox = findViewById(R.id.cinnamon_checkbox);
        boolean hasCinnamon = cinnamonCheckBox.isChecked();

//Complete order summary
        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate, hasCinnamon);

        // new Intent object                   constant
        Intent intent = new Intent(Intent.ACTION_SENDTO); //constructor with 1 argument to initialize
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }


    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create summary of the order.
     *
     * @param name  user entered
     * @param price  of order
     * @param addWhippedCream  user selected
     * @param addChocolate  user selected
     * @param addCinnamon  user selected
     * @return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate, boolean addCinnamon) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nAdd cinnamon? " + addCinnamon;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.orderSummary_message);
        orderSummaryTextView.setText(message);
    }

}