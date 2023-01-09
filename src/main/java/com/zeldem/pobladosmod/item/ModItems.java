package com.zeldem.pobladosmod.item;

import com.zeldem.pobladosmod.PobladosMod;
import com.zeldem.pobladosmod.item.VaraTecho.VaraTecho;
import com.zeldem.pobladosmod.item.varamuro.VaraMuro;
import com.zeldem.pobladosmod.item.varamuro.VaraPared;
import com.zeldem.pobladosmod.item.varapiso.VaraPiso;
import com.zeldem.pobladosmod.item.varasala.VaraSalaRect;
import com.zeldem.pobladosmod.item.varasala.VaraSalaVertex;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        PobladosMod.MODID);
        public static Item.Properties commonProps = new Item.Properties().tab(ModPobladosToolTab.POBLADOSMOD_TAB)
                        .stacksTo(1);

        public static final RegistryObject<Item> VARA_CASA = ITEMS.register("vara_casa",
                        () -> new Item(commonProps));

        public static final RegistryObject<Item> VARA_MURO = ITEMS.register("vara_muro",
                        () -> new VaraMuro(commonProps));
        public static final RegistryObject<Item> VARA_PARED = ITEMS.register("vara_pared",
                        () -> new VaraPared(commonProps));
        public static final RegistryObject<Item> VARA_PISO = ITEMS.register("vara_piso",
                        () -> new VaraPiso(commonProps));

        // techo
        public static final RegistryObject<Item> VARA_TECHO = ITEMS.register("vara_techo",
                        () -> new VaraTecho(commonProps));

        public static final RegistryObject<Item> VARA_SALA_RECT = ITEMS.register("vara_sala_rect",
                        () -> new VaraSalaRect(commonProps));

        public static final RegistryObject<Item> VARA_SALA_VERTEX = ITEMS.register("vara_sala_vertex",
                        () -> new VaraSalaVertex(commonProps));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }

}
