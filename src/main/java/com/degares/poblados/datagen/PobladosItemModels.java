package com.degares.poblados.datagen;

import com.degares.poblados.PobladosMod;
import com.degares.poblados.setup.Registro;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PobladosItemModels extends ItemModelProvider {

    public PobladosItemModels(DataGenerator gen, ExistingFileHelper helperexis){
        super(gen, PobladosMod.MODID, helperexis);
    }
    @Override
    protected void registerModels() {
        withExistingParent(Registro.CAJA_POBLADORA.get().getRegistryName().getPath(), modLoc("block/caja_pobladora"));
        withExistingParent(Registro.CAJA_INMUEBLE.get().getRegistryName().getPath(), modLoc("block/caja_inmueble"));
        withExistingParent(Registro.VARA_CASITA.get().getRegistryName().getPath(), modLoc("item/vara_casita"));
    }
}
