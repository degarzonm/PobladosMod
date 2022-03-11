package com.degares.poblados.datagen;

import com.degares.poblados.PobladosMod;
import com.degares.poblados.setup.Registro;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.degares.poblados.setup.SetupMod.TAB_ITEM_POBLADOS_NAME;

public class PobladosLanguageProvider extends LanguageProvider {
    public PobladosLanguageProvider(DataGenerator gen ,String locale) {
        super(gen, PobladosMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup."+TAB_ITEM_POBLADOS_NAME , "Poblados tools");

        add(Registro.CAJA_POBLADORA.get(),"town box");
        add(Registro.CAJA_INMUEBLE.get(),"house box");
        add(Registro.VARA_CASITA.get(),"home stick");
    }
}
