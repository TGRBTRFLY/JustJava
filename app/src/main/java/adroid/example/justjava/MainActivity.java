package adroid.example.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
//import android.widget.CheckBox;

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
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCups) {
        TextView quantityTextView = findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCups);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Does customer want whipped cream?
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        //Figure out if the user wants cinnamon topping
        CheckBox cinnamonCheckBox = findViewById(R.id.cinnamon_checkbox);
        boolean hasCinnamon = cinnamonCheckBox.isChecked();

        //Calculate the price
        int price = quantity * 5;

        // Display the order summary on the screen
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, hasCinnamon);
        displayMessage(message);
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, boolean addCinnamon) {
        String priceMessage = "Name: ";
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