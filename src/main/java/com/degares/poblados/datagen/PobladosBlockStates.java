package com.degares.poblados.datagen;

import com.degares.poblados.PobladosMod;
import com.degares.poblados.setup.Registro;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PobladosBlockStates extends BlockStateProvider {

    PobladosBlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, PobladosMod.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registro.CAJA_INMUEBLE.get());
        simpleBlock(Registro.CAJA_POBLADORA.get());
    }
}
