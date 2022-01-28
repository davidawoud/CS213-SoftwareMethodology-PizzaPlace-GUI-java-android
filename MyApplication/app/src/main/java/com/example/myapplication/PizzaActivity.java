package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This class is the activity controller for the pizza window.
 @author Stephen Juan, David Halim
 */
public class PizzaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable
{
    private ImageView pizza_picture;
    private Spinner size_dropdown;
    private ListView available_box, selected_box;
    private EditText price_bar;

    private String pizzaType;
    private Pizza currentPizza;
    private int size = -1;
    int numToppings;

    ArrayList<String> availableTop = new ArrayList<String>();
    ArrayList<String> selectedTop= new ArrayList<String>();

    /**
     This method changes a string into an enum topping class (24 lines)
     @param topping - topping in string format
     @return enum class topping
     */
    private Topping getToppingString(String topping)
    {
        if (topping.equalsIgnoreCase("Chicken"))
            return Topping.Chicken;
        else if (topping.equalsIgnoreCase("Beef"))
            return Topping.Beef;
        else if (topping.equalsIgnoreCase("Pepperoni"))
            return Topping.Pepperoni;
        else if (topping.equalsIgnoreCase("Ham"))
            return Topping.Ham;
        else if (topping.equalsIgnoreCase("Sausage"))
            return Topping.Sausage;
        else if (topping.equalsIgnoreCase("Cheese"))
            return Topping.Cheese;
        else if (topping.equalsIgnoreCase("Pepper"))
            return Topping.Pepper;
        else if (topping.equalsIgnoreCase("Onion"))
            return Topping.Onion;
        else if (topping.equalsIgnoreCase("Mushroom"))
            return Topping.Mushroom;
        else if (topping.equalsIgnoreCase("Olive"))
            return Topping.Olive;
        else
            return Topping.Pineapple;
    }

    /**
     Ths method sets the image of the pizza view, depending on what pizza was selected.
     */
    private void setImage()
    {
        if (pizzaType.equals("deluxe"))
            pizza_picture.setImageResource(R.drawable.d_pizza);
        else if (pizzaType.equals("hawaiian"))
            pizza_picture.setImageResource(R.drawable.h_pizza);
        else
            pizza_picture.setImageResource(R.drawable.p_pizza);
    }

    /**
     This method creates a pizza.
     */
    private void makePizza()
    {
        try
        {
            currentPizza = PizzaMaker.createPizza(pizzaType);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     This method fills the dropdown menu with values.
     */
    private void fillDropMenu()
    {
        String[] arraySpinner = new String[] { getResources().getString(R.string.small_pizza),
                getResources().getString(R.string.medium_pizza),
                getResources().getString(R.string.large_pizza)   };
        this.size_dropdown = (Spinner) findViewById(R.id.size_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size_dropdown.setAdapter(adapter);
        size_dropdown.setOnItemSelectedListener(this);
    }

    /**
     Adds the unpicked topping for deluxe pizza.
     */
    private void addDeluxeToppings()
    {
        availableTop.add(getResources().getString(R.string.chicken_topping));
        availableTop.add(getResources().getString(R.string.beef_topping));
        availableTop.add(getResources().getString(R.string.ham_topping));
        availableTop.add(getResources().getString(R.string.cheese_topping));
        availableTop.add(getResources().getString(R.string.pepper_topping));
        availableTop.add(getResources().getString(R.string.pineapple_topping));
    }

    /**
     Adds the unpicked topping for hawaiian pizza.
     */
    private void addHawaiianToppings()
    {
        availableTop.add(getResources().getString(R.string.chicken_topping));
        availableTop.add(getResources().getString(R.string.beef_topping));
        availableTop.add(getResources().getString(R.string.cheese_topping));
        availableTop.add(getResources().getString(R.string.pepper_topping));
        availableTop.add(getResources().getString(R.string.pepperoni_topping));
        availableTop.add(getResources().getString(R.string.sausage_topping));
        availableTop.add(getResources().getString(R.string.onion_topping));
        availableTop.add(getResources().getString(R.string.mushroom_topping));
        availableTop.add(getResources().getString(R.string.olive_topping));
    }

    /**
     Adds the unpicked topping for pepperoni pizza.
     */
    private void addPepperoniToppings()
    {
        availableTop.add(getResources().getString(R.string.chicken_topping));
        availableTop.add(getResources().getString(R.string.beef_topping));
        availableTop.add(getResources().getString(R.string.cheese_topping));
        availableTop.add(getResources().getString(R.string.pepper_topping));
        availableTop.add(getResources().getString(R.string.sausage_topping));
        availableTop.add(getResources().getString(R.string.onion_topping));
        availableTop.add(getResources().getString(R.string.mushroom_topping));
        availableTop.add(getResources().getString(R.string.olive_topping));
        availableTop.add(getResources().getString(R.string.pineapple_topping));
        availableTop.add(getResources().getString(R.string.ham_topping));
    }

    /**
     Adds selected topping to the listview.
     */
    private void addToppingsToList()
    {
        if (pizzaType.equals("deluxe"))
            addDeluxeToppings();
        else if (pizzaType.equals("hawaiian"))
            addHawaiianToppings();
        else
            addPepperoniToppings();
    }

    /**
     Sets values for widgets and adds default toppings to the listview.
     */
    private void setValues()
    {
        selectedTop = currentPizza.getToppingsStr();
        numToppings = currentPizza.getNumberOfToppings();
        this.available_box = (ListView) findViewById(R.id.available_box);
        this.selected_box = (ListView) findViewById(R.id.selected_box);
        this.price_bar = (EditText) findViewById(R.id.price_bar);

    }

    /**
     Adds a topping to pizza.
     @param value - string of topping
     */
    private void addToppingToPizza(String value)
    {
        try {
            currentPizza.addTopping(getToppingString(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     This method disables the user from editing the price bar.
     */
    private void disablePriceBarEdit()
    {
        this.price_bar = (EditText) findViewById(R.id.price_bar);
        price_bar.setFocusable(false);
    }

    /**
     This method displays a toast message that says that the user can't pick more than 7 toppings.
     */
    private void maxToppingMessage()
    {
        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.max_toppings);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     This method runs on startup.
     @param savedInstanceState - state of the app that is saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        Intent intent = getIntent();
        pizzaType = intent.getStringExtra("PIZZATYPE");
        this.pizza_picture = (ImageView) findViewById(R.id.pizza_picture);
        // changes the pizza picture based on stuff
        setImage();
        // makes the pizza
        makePizza();
        // fills in small, med, large for the drop down menu
        fillDropMenu();
        // fills in toppings in the listview, depending on type of pizza selected
        addToppingsToList();
        // set values for selectedTopping, numToppings and IDs the widgets
        setValues();

        // set price
        price_bar.setText(currentPizza.price() + "");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, availableTop);
        available_box.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, selectedTop);
        selected_box.setAdapter(arrayAdapter1);
        // action event for adding a topping
        available_box.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                System.out.println(value);
                if (numToppings < Pizza.MAX_TOPPINGS)
                {
                    numToppings++;
                    availableTop.remove(value);
                    arrayAdapter.notifyDataSetChanged();
                    selectedTop.add(value);
                    arrayAdapter1.notifyDataSetChanged();
                    // add topping to pizza
                    addToppingToPizza(value);
                    // update price
                    price_bar.setText(currentPizza.price() + "");
                }
                else
                    maxToppingMessage();
            }
        });
        selected_box.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                String value = (String) adapter.getItemAtPosition(position);
                System.out.println(value);
                numToppings--;
                selectedTop.remove(value);
                arrayAdapter1.notifyDataSetChanged();
                availableTop.add(value);
                arrayAdapter.notifyDataSetChanged();
                // remove topping from pizza
                currentPizza.removeTopping(getToppingString(value));
                // update price
                price_bar.setText(currentPizza.price() + "");
            }
        });
        // disables editing the price bar
        disablePriceBarEdit();
    }

    /**
     Event handler for setting size of pizza.
     @param parent   - adapterview
     @param view     - view of app
     @param position - position of selected
     @param id       - identification
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String choice = parent.getItemAtPosition(position).toString();
        if (choice.equals("Small")) {
            currentPizza.setSize(Size.Small);
            size = 0;
        }
        else if (choice.equals("Medium")) {
            currentPizza.setSize(Size.Medium);
            size = 1;
        }
        else {
            currentPizza.setSize(Size.Large);
            size = 2;
        }
        price_bar.setText(currentPizza.price() + "");
    }

    /**
     Nothing is selected. This method should ideally never run.
     @param parent - parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.order_pizza);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     Event handler for buying pizza.
     Sends the information that we created in the pizza back to the main activity.
     @param view
     */
    public void buy_pizza(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("pizza", currentPizza);
        setResult(RESULT_OK, intent);
        finish(); // closes the window
    }

}