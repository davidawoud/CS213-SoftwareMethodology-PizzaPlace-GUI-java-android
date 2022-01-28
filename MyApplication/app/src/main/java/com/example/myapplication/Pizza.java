package com.example.myapplication;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Abstract Pizza class extended by all pizza flavors classes.
 @author David Halim, Stephen Juan.
 */
public abstract class Pizza implements Serializable
{
    public static final int DELUXE_TOPPINGS = 5;
    public static final int HWAIIAN_TOPPINGS = 2;
    public static final int PEPPERONI_TOPPINGS = 1;
    public static final int MAX_TOPPINGS = 7;
    public static final double MEDIUM_PRICE = 2.0;
    public static final double LARGE_PRICE = 4.0;
    public static final double EXTRA_TOPPING_PRICE = 1.49;

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     Calculates and returns the price of a pizza. (1 line)
     @return price of the pizza
     */
    public abstract double price();

    /**
     Sets the size of the pizza. (4 lines)
     @param sizeIn to set the pizza size
     */
    public void setSize(Size sizeIn)
    {
        size = sizeIn;
    }

    /**
     Adds a topping to the pizza. (10 lines)
     @param topIn to be added
     @return true if topping is added,
     false if the topping is already on the pizza
     @throws Exception if the pizza reached MAX_TOPPINGS
     */
    public boolean addTopping(Topping topIn) throws Exception
    {
        int toppingIndex = toppings.indexOf(topIn);
        if (toppingIndex != -1)
            return false;
        if (getNumberOfToppings() >= MAX_TOPPINGS)
            throw new Exception("Can't add more than 7 toppings.");
        toppings.add(topIn);
        return true;
    }

    /**
     Removes a topping to the pizza. (8 lines)
     @param topIn to be removed
     @return true if topping is removed,
     false if the topping is not on the pizza
     */
    public boolean removeTopping(Topping topIn)
    {
        int toppingIndex = toppings.indexOf(topIn);
        if (toppingIndex == -1)
            return false;
        toppings.remove(toppingIndex);
        return true;
    }

    /**
     Gets of toppings on the pizza. (4 lines)
     @return toppings ArrayList.
     */
    public ArrayList<Topping> getToppings()
    {
        return toppings;
    }

    public ArrayList<String> getToppingsStr()
    {
        ArrayList<String> arr = new ArrayList<String>();
        for (int i = 0; i < toppings.size(); i++)
        {
            if (toppings.get(i).equals(Topping.Chicken))
                arr.add("Chicken");
            else if (toppings.get(i).equals(Topping.Beef))
                arr.add("Beef");
            else if (toppings.get(i).equals(Topping.Pepperoni))
                arr.add("Pepperoni");
            else if (toppings.get(i).equals(Topping.Ham))
                arr.add("Ham");
            else if (toppings.get(i).equals(Topping.Sausage))
                arr.add("Sausage");
            else if (toppings.get(i).equals(Topping.Cheese))
                arr.add("Cheese");
            else if (toppings.get(i).equals(Topping.Pepper))
                arr.add("Pepper");
            else if (toppings.get(i).equals(Topping.Onion))
                arr.add("Onion");
            else if (toppings.get(i).equals(Topping.Mushroom))
                arr.add("Mushroom");
            else if (toppings.get(i).equals(Topping.Olive))
                arr.add("Olive");
            else
                arr.add("Pineapple");
        }
        return arr;
    }


    /**
     This method gets an array of toppings in a string format. (29 lines)
     @return array of topppings in a string format
     */
    public String[] getArrToppings()
    {
        String[] arr = new String[toppings.size()];
        for (int i = 0; i < arr.length; i++)
        {
            if (toppings.get(i).equals(Topping.Chicken))
                arr[i] = "Chicken";
            else if (toppings.get(i).equals(Topping.Beef))
                arr[i] = "Beef";
            else if (toppings.get(i).equals(Topping.Pepperoni))
                arr[i] = "Pepperoni";
            else if (toppings.get(i).equals(Topping.Ham))
                arr[i] = "Ham";
            else if (toppings.get(i).equals(Topping.Sausage))
                arr[i] = "Sausage";
            else if (toppings.get(i).equals(Topping.Cheese))
                arr[i] = "Cheese";
            else if (toppings.get(i).equals(Topping.Pepper))
                arr[i] = "Pepper";
            else if (toppings.get(i).equals(Topping.Onion))
                arr[i] = "Onion";
            else if (toppings.get(i).equals(Topping.Mushroom))
                arr[i] = "Mushroom";
            else if (toppings.get(i).equals(Topping.Olive))
                arr[i] = "Olive";
            else
                arr[i] = "Pineapple";
        }
        return arr;
    }

    /**
     This method sets the toppings of the pizza. (4 lines)
     @param listOfTopping - toppings to be set
     */
    public void setToppings(ArrayList<Topping> listOfTopping)
    {
        toppings = listOfTopping;
    }

    /**
     Gets the number of toppings on the pizza. (4 lines)
     @return the size of toppings ArrayList
     */
    public int getNumberOfToppings()
    {
        return toppings.size();
    }

    /**
     This method returns compares 2 Pizza objects (9 lines)
     @return true if 2 Pizza objects are equal,
     false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        Pizza pizza = (Pizza) o;
        return (this.getClass().equals(pizza.getClass()))
                && (this.size.equals(pizza.size))
                && (this.getNumberOfToppings() == pizza.getNumberOfToppings())
                && (this.toppings.containsAll(pizza.toppings));
    }
}
