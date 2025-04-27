package com.gencal.alternative_earlygame.items.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CrushingHammerItem extends Item {

    public CrushingHammerItem(Properties pProperties) {
        super(pProperties
                .defaultDurability(32)
                .setNoRepair()
        );
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        // Allow the item to persist if it's not fully damaged
        return stack.getDamageValue() < stack.getMaxDamage();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        ItemStack copy = stack.copy();
        // Increase damage by 1 (adjust this value to control durability loss per craft)
        copy.setDamageValue(copy.getDamageValue() + 1);
        return copy;
    }

}
