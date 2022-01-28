package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 This class is the activity controller for the current order window.
 @author Stephen Juan, David Halim
 */
public class CurrentOrderActivity extends AppCompatActivity implements Serializable
{
    private ListView orderList;
    ArrayAdapter<String> arrayAdapter;
    private EditText phoneNumber, subTotal, tax, total;
    private Order order;
    private Pizza selected_pizza;
    private String selected_pizza_str;
    ArrayList<String> piazzaList = new ArrayList<String>();

    /**
     This method sets values for the android widgets.
     */
    private void setValues()
    {
        this.phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        phoneNumber.setFocusable(false);
        phoneNumber.setText(order.getPhoneNumber());
        this.subTotal = (EditText) findViewById(R.id.subTotal);
        subTotal.setFocusable(false);
        this.tax = (EditText) findViewById(R.id.tax);
        tax.setFocusable(false);
        this.total = (EditText) findViewById(R.id.total);
        total.setFocusable(false);
        this.orderList = (ListView) findViewById(R.id.orderList);
    }

    /**
     This method sets the costs for subtotal, tax and grand total in the text bar.
     */
    private void setCosts()
    {
        float subtotalCost = 0;
        float taxRate = (float) 1.06625;

        ArrayList<Pizza> pizzasInOrder = order.getPizzaOrder();

        for (int i = 0; i < pizzasInOrder.size(); i++)
        {
            subtotalCost += pizzasInOrder.get(i).price();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        subTotal.setText(df.format(subtotalCost));
        tax.setText(df.format(subtotalCost * (taxRate - 1)));
        total.setText(df.format(subtotalCost * taxRate));
    }

    /**
     This method fills the array with the pizzas in string format.
     */
    private void fillList()
    {
        String[] list = order.getArrOfPizza();
        for (int i = 0; i < list.length; i++)
            piazzaList.add(list[i]);
    }

    /**
     This code runs when the activity is opened.
     @param savedInstanceState - state of the app that is saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("ORDER");
        setValues();
        setCosts();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, piazzaList);
        orderList.setAdapter(arrayAdapter);
        fillList();
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                selected_pizza_str = (String) adapter.getItemAtPosition(position);
                String type = getType();
                Size pizzaSize = getSize();
                ArrayList<Topping> toppings = getToppings();
                Pizza pizza = null;
                try
                {
                    pizza = PizzaMaker.createPizza(type);
                }
                catch(Exception e) {}
                pizza.setSize(pizzaSize);
                pizza.setToppings(toppings);

                selected_pizza = pizza;

                Context context = getApplicationContext();
                CharSequence text = selected_pizza_str + context.getString(R.string.selected);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    /**
     This method gets the type of the selected pizza in a toString format. (12 lines)
     @return type of pizza
     */
    private String getType()
    {
        String type = "";
        for (int i = 0; i < selected_pizza_str.length(); i++)
        {
            if (selected_pizza_str.charAt(i) == ':')
                break;
            type += selected_pizza_str.charAt(i);
        }
        String arr[] = type.split(" ", 2);
        type = arr[0];
        return type;
    }

    /**
     This method gets the size of the selected pizza in a toString format. (25 lines)
     @return size of pizza
     */
    private Size getSize()
    {
        String size = "";
        Size pizzaSize;
        int semicolonCount = 0;
        for (int i = 0; i < selected_pizza_str.length(); i++)
        {
            if (selected_pizza_str.charAt(i) == ':')
            {
                semicolonCount++;
                continue;
            }
            if (selected_pizza_str.charAt(i) == ':' && semicolonCount == 2)
                break;
            if (semicolonCount == 1)
                size += selected_pizza_str.charAt(i);
        }
        if (size.equals("small"))
            pizzaSize = Size.Small;
        else if (size.equals("medium"))
            pizzaSize = Size.Medium;
        else
            pizzaSize = Size.Large;

        return pizzaSize;
    }

    /**
     This method adds a topping in string format to an arraylist of Topping. (24 lines)
     @param currentTopping - topping to be added
     @param toppings - arraylist of Topping
     */
    private void addToppings(String currentTopping, ArrayList<Topping> toppings)
    {
        if (currentTopping.equals("chicken"))
            toppings.add(Topping.Chicken);
        else if (currentTopping.equals("beef"))
            toppings.add(Topping.Beef);
        else if (currentTopping.equals("pepperoni"))
            toppings.add(Topping.Pepperoni);
        else if (currentTopping.equals("ham"))
            toppings.add(Topping.Ham);
        else if (currentTopping.equals("sausage"))
            toppings.add(Topping.Sausage);
        else if (currentTopping.equals("cheese"))
            toppings.add(Topping.Cheese);
        else if (currentTopping.equals("pepper"))
            toppings.add(Topping.Pepper);
        else if (currentTopping.equals("onion"))
            toppings.add(Topping.Onion);
        else if (currentTopping.equals("mushroom"))
            toppings.add(Topping.Mushroom);
        else if (currentTopping.equals("olive"))
            toppings.add(Topping.Olive);
        else
            toppings.add(Topping.Pineapple);
    }

    /**
     This method gets the toppings of the selected pizza in a toString format. (24 lines)
     @return toppings of a pizza
     */
    private ArrayList<Topping> getToppings()
    {
        ArrayList<Topping> toppings = new ArrayList<>();
        String currentTopping = "";
        int semicolonCount = 0;
        for (int i = 0; i < selected_pizza_str.length(); i++)
        {
            if (selected_pizza_str.charAt(i) == ':')
            {
                semicolonCount++;
                continue;
            }
            if (semicolonCount == 2)
            {
                if (selected_pizza_str.charAt(i) == ' ')
                {
                    addToppings(currentTopping, toppings);
                    currentTopping = "";
                    continue;
                }
                currentTopping += selected_pizza_str.charAt(i);
            }
        }
        return toppings;
    }

    /**
     This method shows a warning that there is nothing in the order.
     */
    public void nothingInOrder()
    {
        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.empty_order);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     Removes the selected pizza in the current order.
     */
    public void removePizza(View view)
    {
        if(piazzaList.isEmpty())
        {
            nothingInOrder();
            return;
        }

        if(selected_pizza_str == null)
        {
            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.nothing_selected);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        piazzaList.remove(selected_pizza_str);
        arrayAdapter.notifyDataSetChanged();
        order.removePizza(selected_pizza);
        setCosts();
    }

    /**
     This method cancels the order if the back button is pressed.
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("ORDER_RES", order);
        setResult(RESULT_CANCELED, intent);
        super.onBackPressed();
    }

    /**
     This button places the order all the pizzas, identified with the phone number.
     */
    public void placeOrder(View view)
    {
        if(piazzaList.isEmpty())
        {
            nothingInOrder();
            return;
        }

        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.order_placed);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent();
        intent.putExtra("ORDER_RES", order);
        setResult(RESULT_OK, intent);
        finish();
    }
}