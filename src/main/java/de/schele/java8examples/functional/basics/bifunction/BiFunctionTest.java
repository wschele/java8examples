package de.schele.java8examples.functional.basics.bifunction;

/**
 * http://alvinalexander.com/java/jwarehouse/openjdk-8/jdk/test/java/util/function/BiFunction/BiFunctionTest.java.shtml
 */

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class BiFunctionTest {
	
    static class Quote {
        double unit_price;

        Quote(double price) {
            unit_price = price;
        }
    };

    static class Order {
        int quantity;

        Order(int quantity) {
            this.quantity = quantity;
        }
    };

    /**
     * BiFunction 
     */
    BiFunction<Quote, Order, Double> estimate = (quote, order) -> {
        if (quote.unit_price < 0) {
            throw new IllegalArgumentException("quote");
        }

        if (order.quantity < 0) {
            throw new IllegalArgumentException("order");
        }

        return quote.unit_price * order.quantity;
    };

    /**
     * Function
     */
    Function<Double, Long> creditcheck = total -> {
        if (total > 100.00) {
            throw new RuntimeException("overlimit");
        }
        return total.longValue();
    };
    
    public void testMethod() {
    	 try {
             BiFunction<Quote, Order, Long> checkout = estimate.andThen(null);
             Assert.fail("Null argument should throw NPE");
         } catch (NullPointerException npe) {
             // ignore
         }

         BiFunction<Quote, Order, Long> checkout = estimate.andThen(creditcheck);
         try {
             checkout.apply(new Quote(20.0), new Order(-1));
             Assert.fail("First function delivers exception");
         } catch (IllegalArgumentException e) {
         	 Assert.assertEquals(e.getMessage(), "order");
         }

         try {
             checkout.apply(new Quote(20.0), new Order(10));
             Assert.fail("Second function delivers exception");
         } catch (RuntimeException e) {
         	 Assert.assertEquals(e.getMessage(), "overlimit");
         }

         Assert.assertEquals(49, checkout.apply(new Quote(24.99), new Order(2)).longValue());
         Assert.assertEquals(50, checkout.apply(new Quote(25), new Order(2)).longValue());
    }
    
    /**
     * Start as junit test
     */
    @Test
    public void testIt() {    	
    	BiFunctionTest biFunctionTest = new BiFunctionTest();
    	biFunctionTest.testMethod();
    }
    
    /**
     * Start as java main
     */
    public static void main(String[] args){    	
    	BiFunctionTest biFunctionTest = new BiFunctionTest();
    	biFunctionTest.testMethod();    	
    }
       
}