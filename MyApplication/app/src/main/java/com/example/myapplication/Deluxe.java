package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.io.Serializable;

/**
 Deluxe Pizza class that extends Pizza.
 @author David Halim, Stephen Juan.
 */
public class Deluxe extends Pizza
{
    /**
     Default constructor that sets the default tapings and size to small. (10 lines)
     */
    public Deluxe()
    {
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Sausage);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Olive);
        toppings.add(Topping.Mushroom);

        size = Size.Small;
    }

    /**
     Overloaded constructor that sets the default tapings and size to sizeIn (10 lines)
     @param sizeIn - input size
     */
    public Deluxe(Size sizeIn)
    {
        toppings.add(Topping.Pepperoni);
        toppings.add(Topping.Sausage);
        toppings.add(Topping.Onion);
        toppings.add(Topping.Olive);
        toppings.add(Topping.Mushroom);

        size = sizeIn;
    }

    /**
     Calculates and returns the price of a pizza. (18 lines)
     @return price of the pizza.
     */
    @Override
    public double price()
    {
        double deluxePrice = 12.99;
        int extraToppings = getNumberOfToppings() - DELUXE_TOPPINGS;

        if (!size.equals(Size.Small))
        {
            if (size.equals(Size.Medium))
                deluxePrice += MEDIUM_PRICE;
            else if (size.equals(Size.Large))
                deluxePrice += LARGE_PRICE;
        }

        if (extraToppings > 0)
            deluxePrice += EXTRA_TOPPING_PRICE * extraToppings;

        deluxePrice = Math.floor(deluxePrice * 100) / 100;
        return deluxePrice;
    }

    /**
     Returns a deluxe pizza in a string format. (39 lines)
     */
    @Override
    public String toString()
    {
        String string = "Deluxe Pizza:";

        if (size == size.Small)
            string += "small:";
        else if (size == size.Medium)
            string += "medium:";
        else
            string += "large:";

        for (int i = 0; i < toppings.size();i++)
        {
            if (toppings.get(i) == Topping.Chicken)
                string += "chicken ";
            else if (toppings.get(i) == Topping.Beef)
                string += "beef ";
            else if (toppings.get(i) == Topping.Pepperoni)
                string += "pepperoni ";
            else if (toppings.get(i) == Topping.Ham)
                string += "ham ";
            else if (toppings.get(i) == Topping.Sausage)
                string += "sausage ";
            else if (toppings.get(i) == Topping.Cheese)
                string += "cheese ";
            else if (toppings.get(i) == Topping.Pepper)
                string += "pepper ";
            else if (toppings.get(i) == Topping.Onion)
                string += "onion ";
            else if (toppings.get(i) == Topping.Mushroom)
                string += "mushroom ";
            else if (toppings.get(i) == Topping.Olive)
                string += "olive ";
            else
                string += "pineapple ";
        }

        return string;
    }
}