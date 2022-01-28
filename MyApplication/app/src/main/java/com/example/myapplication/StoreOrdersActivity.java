package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 This is the controller for the Store Orders window.
 @author Stephen Juan, David Halim
 */
public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Serializable
{
    private Spinner phonesSpinner;
    private ListView orderList;
    ArrayAdapter<String> spinnerAdapter;
    ArrayAdapter<String> listAdapter;
    private EditText orderTotal;
    private StoreOrders orders;
    private ArrayList<String> phones =  new ArrayList<String>();
    private ArrayList<String> piazzaList = new ArrayList<String>();
    private String selected_phone;
    private Order selected_order;

    /**
     This method fills the dropdown menu of the IDs.
     */
    private void fillSpinner()
    {
        this.phonesSpinner = (Spinner) findViewById(R.id.phonesSpinner);
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, phones);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phonesSpinner.setAdapter(spinnerAdapter);
        phonesSpinner.setOnItemSelectedListener(this);
    }

    /**
     This method finds the selected order in the store orders.
     */
    private void setSelected_order()
    {
        selected_order = orders.findOrder(selected_phone);
    }

    /**
     This method sets the total cost of the order in the text bar.
     */
    private void setOrderTotal()
    {
        float subtotalCost = 0;
        float taxRate = (float) 1.06625;

        ArrayList<Pizza> pizzasInOrder = selected_order.getPizzaOrder();

        for (int i = 0; i < pizzasInOrder.size(); i++)
        {
            subtotalCost += pizzasInOrder.get(i).price();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        orderTotal.setText(df.format(subtotalCost * taxRate));
    }

    /**
     This method sets the phone numbers in the list (used after deleting an order).
     */
    public void setPhones()
    {
        piazzaList.clear();
        String[] list = orders.getArrOfPhone();
        for (int i = 0; i < list.length; i++)
            phones.add(list[i]);
    }

    /**
     This method sets the list of pizza in the order.
     */
    public void setPiazzaList()
    {
        piazzaList.clear();
        String[] list = selected_order.getArrOfPizza();
        for (int i = 0; i < list.length; i++)
            piazzaList.add(list[i]);
    }

    /**
     This is run on opening of the window.
     @param savedInstanceState - state of the app that is saved
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        Intent intent = getIntent();
        orders = (StoreOrders) intent.getSerializableExtra("ORDERS");
        setPhones();
        fillSpinner();

        if(!phones.isEmpty())
            selected_phone = phones.get(0);
        setSelected_order();
        this.orderTotal = (EditText) findViewById(R.id.orderTotal);
        orderTotal.setFocusable(false);
        setPiazzaList();
        this.orderList = (ListView) findViewById(R.id.orderList);
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, piazzaList);
        orderList.setAdapter(listAdapter);
        setOrderTotal();
    }

    /**
     This method runs when an item is selected from the spinner.
     @param parent   - parent adapter view
     @param view     - view
     @param position - position of the spinner
     @param id       - identification
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        selected_phone = parent.getItemAtPosition(position).toString();
        setSelected_order();
        if (selected_order != null)
        {
            setOrderTotal();
            setPiazzaList();
            listAdapter.notifyDataSetChanged();
        }
    }

    /**
     This method runs when nothing is selected.
     @param parent - parent adapter view
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

        Context context = getApplicationContext();
        CharSequence text = context.getString(R.string.no_orders);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     This method runs when the cancel button is pressed.
     @param view - view of the app
     */
    public void cancelOrder(View view)
    {
        orders.removeOrder(selected_phone);
        phones.remove(selected_phone);
        if(phones.size() > 0)
        {
            spinnerAdapter.notifyDataSetChanged();
            selected_phone = phones.get(0);
            setSelected_order();
            if (selected_order != null)
            {
                setOrderTotal();
                setPiazzaList();
                listAdapter.notifyDataSetChanged();
            }
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = context.getString(R.string.no_orders);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent();
            intent.putExtra("ORDERS_RES", orders);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     This method runs when the back button is pressed.
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("ORDERS_RES", orders);
        setResult(RESULT_CANCELED, intent);
        super.onBackPressed();
    }
}