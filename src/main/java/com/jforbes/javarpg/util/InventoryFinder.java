package com.jforbes.javarpg.util;

import com.jforbes.javarpg.items.Consumable;
import com.jforbes.javarpg.items.Item;
import com.jforbes.javarpg.player.Inventory;

public class InventoryFinder {
    public static Item find(Inventory inventory, Class<?> itemType) {
        return inventory.contents
            .stream()
            .filter(itemType::isInstance)
            .findFirst()
            .orElse(null);
    }
    public static void removeFrom(Inventory inventory, Class<?> itemType) {
        Item item = find(inventory, itemType);
        if(item instanceof Consumable) {
            ((Consumable)item).reduceUses(); // Remove one
        } else if(item == null) {
            throw new Error("Could not find a " + itemType.getSimpleName().toLowerCase());
        } else {
            item.removeFromInventory();
        }
    }
}
