package com.zeldem.pobladosmod.modelos.arquitectonicos;

import java.util.ArrayList;
import java.util.Random;

import com.zeldem.pobladosmod.pobladosHelper.Bresenham;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Arquitectonico {
    // Atributos de la clase
    protected ArrayList<BlockPos> plano_de_bloques;
    protected ArrayList<BlockState> material;

    // Constructor de la clase
    public Arquitectonico(ArrayList<BlockState> material) {
        this.plano_de_bloques = new ArrayList<BlockPos>();
        if(material.size()==0){
            this.material = new ArrayList<BlockState>();
            this.material.add(null);
        }else{
            this.material = material;
        }
    }

    public ArrayList<BlockPos> getPlanoConstruccion(){
        return plano_de_bloques;
    }
    //setter
    public void nuevosBloques(ArrayList<BlockPos> nuevos){
        plano_de_bloques.addAll(nuevos);
    }

    public ArrayList<BlockState> getMateriales(){
        return material;
    }
    public void damage(double percent){
        Random r = new Random();
        //deletes percent of the blocks
        int n = (int) (this.plano_de_bloques.size() * percent);
        for(int i = 0; i < n; i++){
            this.plano_de_bloques.remove(r.nextInt(this.plano_de_bloques.size()));
        }
    }

}
