package com.zeldem.pobladosmod.pobladosHelper;

import java.util.ArrayList;

import com.zeldem.pobladosmod.modelos.Sala;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Arquitectonico;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Muro;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class Maistro {
    private Level nivel;

    public Maistro(Level nivel) {
        this.nivel = nivel;
    }

    public boolean construye(Arquitectonico arquitectonico) {
        // Get the list of materials
        ArrayList<BlockState> materials = arquitectonico.getMateriales();
    
        // Get the list of block positions
        ArrayList<BlockPos> planoConstruccion = arquitectonico.getPlanoConstruccion();
    
        // Flag to store the state of all setBlock operations
        boolean flag = true;
    
        // Iterate through the block positions
        for (int i = 0; i < planoConstruccion.size(); i++) {
            // Get the material for the current block position
            BlockState material = materials.get(i % materials.size());
            // Get the block position
            BlockPos pos = planoConstruccion.get(i);
            // Place the block
            flag &= nivel.setBlock(pos, material, 2);
        }
    
        return flag;
    }

    public boolean construye(Sala s){
        boolean flag = true;
        
        flag &= construye(s.getMobiliario());
        flag &= construye(s.getLuces());
        flag &= construye(s.getPiso());
        for(Muro m: s.getMuros()){
            flag &= construye(m);
        }
        flag &= construye(s.getTecho());
        //puertas
        nivel.setBlock(s.getE().above(), s.puerta().setValue(DoorBlock.FACING, s.getF()), 2);
        nivel.setBlock(s.getE().above().above(), s.puerta().setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING,  s.getF()), 2);
        return flag;
    }
}