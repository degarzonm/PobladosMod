package com.degares.poblados.setup;

import com.degares.poblados.PobladosMod;
import com.degares.poblados.items.VaraCalleRecta;
import com.degares.poblados.items.VaraCamino;
import com.degares.poblados.items.VaraCasita;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registro {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PobladosMod.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PobladosMod.MODID);

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
    public static final BlockBehaviour.Properties PROPIEDADES_BLOQUES = BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(3f, 800f);
    public static final Item.Properties PROPIEDADES_ITEMS = new Item.Properties().tab(SetupMod.GRUPO_ITEMS_POBLADOS);

    public static final RegistryObject<Block> CAJA_POBLADORA =BLOCKS.register("caja_pobladora", ()-> new Block(PROPIEDADES_BLOQUES));
    public static final RegistryObject<Item>  CAJA_POBLADORA_ITEM = fromBlock(CAJA_POBLADORA) ;

    public static final RegistryObject<Block> CAJA_INMUEBLE =BLOCKS.register("caja_inmueble", ()-> new Block(PROPIEDADES_BLOQUES));
    public static final RegistryObject<Item>  CAJA_INMUEBLE_ITEM = fromBlock(CAJA_INMUEBLE) ;

    public static final RegistryObject<Item>    VARA_CASITA = ITEMS.register("vara_casita", ()-> new VaraCasita(PROPIEDADES_ITEMS));
    public static final RegistryObject<Item>    VARA_CAMINO = ITEMS.register("vara_camino", ()-> new VaraCamino(PROPIEDADES_ITEMS));
    public static final RegistryObject<Item>    VARA_CALLE_RECTA = ITEMS.register("vara_calle_recta", ()-> new VaraCalleRecta(PROPIEDADES_ITEMS));


    public  static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block){

        return ITEMS.register(block.getId().getPath(), ()-> new BlockItem(block.get(), PROPIEDADES_ITEMS));
    }

}
