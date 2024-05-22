package seng201.team0.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void testCartInitialization(){
        Cart cart = new Cart(250, "eye", 0);
        assertEquals(250, cart.getResourceCapacity());
        assertEquals("eye", cart.getResourceType());
        assertEquals(0, cart.getCurrentPosition());
        assertEquals(0, cart.getCurrentLoad());
        assertEquals(10, cart.getSpeed());
    }
    @Test
    void testAddResources(){
        Cart cart = new Cart(250, "eye", 0);
        cart.addResources(200);
        assertEquals(200, cart.getCurrentLoad());

        cart.addResources(100);
        assertEquals(250, cart.getCurrentLoad());
    }
    @Test
    void testIsFull(){
        Cart cart = new Cart(250, "eye", 0);
        assertFalse(cart.isFull());

        cart.addResources(250);
        assertTrue(cart.isFull());
    }
    @Test
    void testSetCurrentPosition(){
        Cart cart = new Cart(250, "eye", 0);
        cart.setCurrentPosition(7);
        assertEquals(7, cart.getCurrentPosition());
    }

}