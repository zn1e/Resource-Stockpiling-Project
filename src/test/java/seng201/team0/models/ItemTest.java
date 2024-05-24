package seng201.team0.models;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    void testCreateValidItemInput() {
        String itemName = "Valid Test Item";
        int itemCost = 100;
        Image image = null;
        String itemDescription = "This is a valid test item.";

        Item item = new Item(itemName, itemCost, null, itemDescription);

        assertEquals(itemName, item.getName());
        assertEquals(itemCost, item.getItemCost());
        assertNull(item.getImage());
        assertEquals(itemDescription, item.getDescription());
    }
    @Test
    void testCreateItemWithNegativeCost(){
        String itemName = "Negative Test Item";
        int itemCost = -100;
        Image image = null;
        String itemDescription = "This is a negative test item.";
        assertThrows(IllegalArgumentException.class, () -> {
            new Item(itemName, itemCost, null, itemDescription);
        });


    }
    @Test
    void testCreateItemWithMaxCost() {
        String itemName = "Test Item";
        int itemCost = Integer.MAX_VALUE;
        Image image = null;
        String itemDescription = "This is a test item.";

        Item item = new Item(itemName, itemCost, null, itemDescription);

        assertEquals(itemName, item.getName());
        assertEquals(itemCost, item.getItemCost());
        assertNull(item.getImage());
        assertEquals(itemDescription, item.getDescription());
    }

    @Test
    void testGetSellPrice() {
        int itemCost = 100;
        Item item = new Item("Test Item", itemCost, null, "");

        int expectedSellPrice = itemCost - 5;
        assertEquals(expectedSellPrice, item.getSellPrice());
    }

    @Test
    void testToString() {
        String itemName = "Test Item";
        Item item = new Item(itemName, 0, null, "");

        assertEquals(itemName, item.toString());
    }

}
