package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

/**
 This is the controller for the main app window.
 @author Stephen Juan, David Halim
 */
public class MainActivity extends AppCompatActivity implements Serializable
{
    private ImageView deluxe_pizza, hawaiian_pizza, pepperoni_pizza, current_order, store_order;
    private EditText phone_number;

    private StoreOrders allStoreOrder = new StoreOrders();
    private Order allCurrentOrder = new Order("");
    private boolean isOngoingOrder = true;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    /**
     This method checks if the phone number is valid, 10 digits and unused. (25 lines)
     @return true if its valid, false if otherwise
     */
    private boolean isValidPhone()
    {
        // check if the text box is empty
        if (phone_number.getText().toString().equals(""))
            return false;
        String phoneNumber = phone_number.getText().toString();
        // check if the phone number is 10 digits
        if (phoneNumber.length() != 10)
            return false;

        // CHECK IF THE PHONE NUMBER IS ALREADY IN StoreOrder
        String[] allNumbers = allStoreOrder.getArrOfPhone();
        for (int i = 0; i < allNumbers.length; i++)
        {
            if (phoneNumber.equals(allNumbers[i]))
                return false;
        }

        // loop through the phone number to check if its all digit
        for (int i = 0; i < phoneNumber.length(); i++)
        {
            if (!Character.isDigit(phoneNumber.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     This method returns the current order. (4 lines)
     @return the current order
     */
    public Order getCurrOrder()
    {
        return allCurrentOrder;
    }

    /**
     This method returns the current store orders. (4 lines)
     @return the current store orders
     */
    public StoreOrders getStoreOrder()
    {
        return allStoreOrder;
    }

    /**
     This method sets the current order. (4 lines)
     @param order - order to be replaced as
     */
    public void setCurrentOrder(Order order)
    {
        allCurrentOrder = order;
    }

    /**
     This method sets the current store order. (4 lines)
     @param order - store order to be replaced as
     */
    public void setStoreOrder(StoreOrders order)
    {
        allStoreOrder = order;
    }

    /**
     This method sets that the current order is not ongoing.  (4 lines)
     */
    public void setIsOngoingOrder()
    {
        isOngoingOrder = true;
    }

    /**
     This method shows the alert window when someone tries to put in an invalid phone number. (7 lines)
     */
    private void invalidPhone()
    {
        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.invalid_number);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     This method shows the alert window when the user clicks on any pizza. (7 lines)
     */
    private void orderingAlertWindow()
    {
        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.order_pizza);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     This method runs the code as soon as the window is opened.
     @param savedInstanceState - saved state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.phone_number = findViewById(R.id.phone_number);
    }

    /**
     This is the event handler for clicking on a deluxe pizza.
     @param view - view of the app
     */
    public void order_deluxe(View view)
    {
        if (!isValidPhone())
            invalidPhone();
        else
        {
            orderingAlertWindow();
            if (isOngoingOrder)
            {
                allCurrentOrder = new Order(phone_number.getText().toString());
                isOngoingOrder = false;
            }
            // open 2nd activity
            Intent intent = new Intent(this, PizzaActivity.class);
            intent.putExtra("PIZZATYPE", "deluxe");
            startActivityForResult(intent, 1);
        }
    }

    /**
     This is the event handler for clicking on a hawaiian pizza.
     @param view - view of the app
     */
    public void order_hawaiian(View view)
    {
        if (!isValidPhone())
            invalidPhone();
        else
        {
            orderingAlertWindow();
            if (isOngoingOrder)
            {
                allCurrentOrder = new Order(phone_number.getText().toString());
                isOngoingOrder = false;
            }
            // open 2nd activity
            Intent intent = new Intent(this, PizzaActivity.class);
            intent.putExtra("PIZZATYPE", "hawaiian");
            startActivityForResult(intent, 1);
        }
    }

    /**
     This is the event handler for clicking on a pepperoni pizza.
     @param view - view of the app
     */
    public void order_pepperoni(View view)
    {
        if (!isValidPhone())
            invalidPhone();
        else
        {
            orderingAlertWindow();
            if (isOngoingOrder)
            {
                allCurrentOrder = new Order(phone_number.getText().toString());
                isOngoingOrder = false;
            }
            // open 2nd activity
            Intent intent = new Intent(this, PizzaActivity.class);
            intent.putExtra("PIZZATYPE", "pepperoni");
            startActivityForResult(intent, 1);
        }
    }

    /**
     This is the event handler for clicking on a current order.
     @param view - view of the app
     */
    public void open_current(View view)
    {
        if (!isValidPhone())
            invalidPhone();
        else
        {
            Intent intent = new Intent(this, CurrentOrderActivity.class);
            intent.putExtra("ORDER",getCurrOrder());
            startActivityForResult(intent, 2);
        }
    }

    /**
     This is the event handler for clicking on the store orders.
     @param view - view of the app
     */
    public void open_store(View view)
    {
        if (allStoreOrder.getArrOfPhone().length > 0)
        {
            Intent intent = new Intent(this, StoreOrdersActivity.class);
            intent.putExtra("ORDERS", getStoreOrder());
            startActivityForResult(intent, 3);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.no_orders);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     This is the code that runs when the pizza activity, current order activity or
     store activity is closed.
     * @param requestCode - which window was opened
     * @param resultCode  - status result of the activity
     * @param data        - data to be passed
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                Pizza pizza = (Pizza) data.getSerializableExtra("pizza");
                allCurrentOrder.addPizza(pizza);
            }
        }
        if (requestCode == 2)
        {
            if(resultCode == RESULT_OK)
            {
                Order order = (Order) data.getSerializableExtra("ORDER_RES");
                allStoreOrder.addOrder(order);
                allCurrentOrder = new Order("");
                setIsOngoingOrder();
            }
            else
            {
                Order order = (Order) data.getSerializableExtra("ORDER_RES");
                allCurrentOrder = order;
            }
        }

        if (requestCode == 3)
        {
            if(resultCode == RESULT_OK || resultCode == RESULT_CANCELED)
                allStoreOrder = (StoreOrders) data.getSerializableExtra("ORDERS_RES");
        }
    }
}
