package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 This class holds a order which contains a phone number and a list of pizzas.
 @author Stephen Juan, David Halim
 */
public class Order implements Serializable
{
    private String phoneNumber;
    private ArrayList<Pizza> pizzaOrder = new ArrayList<Pizza>();
    private float orderTotal;

    /**
     Constructor for an order that includes a phone number. (4 lines)
     @param phoneNumber - phone number for the order
     */
    public Order(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     This method gets the arraylist of pizzas in the order. (4 lines)
     @return arraylist of pizzas
     */
    public ArrayList<Pizza> getPizzaOrder()
    {
        return pizzaOrder;
    }

    /**
     This method returns the phone number of the order. (4 lines)
     @return phone number of the order
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     This method sets the total cost of the order. (4 lines)
     @param cost - total cost of the order
     */
    public void setOrderTotal(float cost)
    {
        orderTotal = cost;
    }

    /**
     This method returns the total cost of the order. (4 lines)
     @return total cost of order
     */
    public float getOrderTotal()
    {
        return orderTotal;
    }

    /**
     This method adds a pizza to the order. (4 lines)
     @param pizza - pizza to be added
     */
    public void addPizza(Pizza pizza)
    {
        pizzaOrder.add(pizza);
    }

    /**
     This method removes a pizza from the order. (10 lines)
     @param compare - pizza to be remove from the order
     */
    public void removePizza(Pizza compare)
    {
        for (int i = 0; i < pizzaOrder.size(); i++)
        {
            Pizza pizza = pizzaOrder.get(i);

            if (compare.equals(pizza))
                pizzaOrder.remove(i);
        }
    }

    /**
     This method returns an array of strings of all the pizzas in the order. (12 lines)
     @return an array of strings of all the pizzas in the order
     */
    public String[] getArrOfPizza()
    {
        String[] pizzas = new String[pizzaOrder.size()];

        for (int i = 0; i < pizzas.length; i++)
        {
            String pizza = (pizzaOrder.get(i)).toString();
            pizzas[i] = pizza;
        }

        return pizzas;
    }
}
