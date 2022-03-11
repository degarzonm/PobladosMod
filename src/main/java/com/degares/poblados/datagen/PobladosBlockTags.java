package com.degares.poblados.datagen;

import com.degares.poblados.PobladosMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PobladosBlockTags extends BlockTagsProvider {


    public PobladosBlockTags(DataGenerator p_126511_,  ExistingFileHelper existingFileHelper) {
        super(p_126511_, PobladosMod.MODID, existingFileHelper);
    }


}
