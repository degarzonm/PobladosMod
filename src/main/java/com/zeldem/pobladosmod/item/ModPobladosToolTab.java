package com.zeldem.pobladosmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModPobladosToolTab {
    public static final CreativeModeTab POBLADOSMOD_TAB = new CreativeModeTab("pobladosmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.VARA_CASA.get());
        }
    };
}
