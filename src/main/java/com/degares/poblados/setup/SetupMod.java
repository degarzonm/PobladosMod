package com.degares.poblados.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class SetupMod {

    public static final String TAB_ITEM_POBLADOS_NAME = "poblados";
    public static final CreativeModeTab GRUPO_ITEMS_POBLADOS = new CreativeModeTab(TAB_ITEM_POBLADOS_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.STONE_AXE);
        }
    };




    public static void init(FMLCommonSetupEvent event){

    }
}
