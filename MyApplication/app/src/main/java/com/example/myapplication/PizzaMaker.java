package com.example.myapplication;

/**
 Create an instance of subclasses based on the chosen flavor.
 This is to hide the details of creating the pizza objects and allow flexibility to add
 new pizza flavors in the future, similar to the design pattern â€œFactory Methodâ€�.
 @author David Halim, Stephen Juan.
 */
public class PizzaMaker
{
    /**
     Creates an instance of subclasses based on the chosen flavor.
     @param flavor of the pizza
     @return outPizza of the entered flavor
     @throws Exception if invalid flavor was passed as a parameter
     */
    public static Pizza createPizza(String flavor) throws Exception
    {
        Pizza outPizza;

        if (flavor.equalsIgnoreCase("Deluxe"))
            outPizza = new Deluxe();
        else if (flavor.equalsIgnoreCase("Hawaiian"))
            outPizza = new Hawaiian();
        else if (flavor.equalsIgnoreCase("Pepperoni"))
            outPizza = new Pepperoni();
        else
            throw new Exception ("Invalid flavor.");

        return outPizza;
    }
}
