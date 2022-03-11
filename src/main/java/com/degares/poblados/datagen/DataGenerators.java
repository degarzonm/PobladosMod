package com.degares.poblados.datagen;

import com.degares.poblados.PobladosMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid= PobladosMod.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        if(event.includeServer()){
            generator.addProvider(new PobladosRecipes(generator));
            generator.addProvider(new PobladosLootTables(generator));

          //  PobladosBlockTags blockTags= new PobladosBlockTags(generator, event.getExistingFileHelper());
          //  generator.addProvider(blockTags);
         //   generator.addProvider(new PobladosItemTags(generator, blockTags, event.getExistingFileHelper()));

        }
        if(event.includeClient()){
            generator.addProvider(new PobladosBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new PobladosItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new PobladosLanguageProvider(generator,"en_us"));
        }

    }
}
