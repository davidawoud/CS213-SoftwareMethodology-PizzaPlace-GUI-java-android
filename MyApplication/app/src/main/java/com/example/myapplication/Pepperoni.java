package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.io.Serializable;

/**
 Pepperoni Pizza class that extends Pizza.
 @author David Halim, Stephen Juan.
 */
public class Pepperoni extends Pizza
{
    /**
     Default constructor that sets the default tapings and size to small. (6 lines)
     */
    public Pepperoni()
    {
        toppings.add(Topping.Pepperoni);

        size = Size.Small;
    }

    /**
     Overloaded constructor that sets the default tapings and size to sizeIn. (6 lines)
     @param sizeIn - input size
     */
    public Pepperoni(Size sizeIn)
    {
        toppings.add(Topping.Pepperoni);

        size = sizeIn;
    }

    /**
     Calculates and returns the price of a pizza.
     @return price of the pizza.
     */
    @Override
    public double price()
    {
        double peppPrice = 8.99;
        int extraToppings = getNumberOfToppings() - PEPPERONI_TOPPINGS;

        if (!size.equals(Size.Small))
        {
            if (size.equals(Size.Medium))
                peppPrice += MEDIUM_PRICE;
            else if (size.equals(Size.Large))
                peppPrice += LARGE_PRICE;
        }

        if (extraToppings > 0)
            peppPrice += EXTRA_TOPPING_PRICE * extraToppings;

        peppPrice = Math.floor(peppPrice * 100) / 100;
        return peppPrice;
    }

    /**
     Returns a deluxe pizza in a string format. (38 lines)
     */
    @Override
    public String toString()
    {
        String string = "Pepperoni Pizza:";

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
