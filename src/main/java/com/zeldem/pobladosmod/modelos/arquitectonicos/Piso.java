package com.zeldem.pobladosmod.modelos.arquitectonicos;

import java.util.ArrayList;
import java.util.Random;

import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class Piso extends Arquitectonico{
    
    private ArrayList<BlockPos> vertices;
    //constructor de la clase
    public Piso(ArrayList<BlockPos> vertices, ArrayList<BlockState> material){
        super(material);
        this.vertices = vertices;
        this.plano_de_bloques = calcularBloques(vertices);
    }

    //create a function that calculate the blocks that compose the wall
    public static ArrayList<BlockPos> calcularBloques(ArrayList<BlockPos> vertices){
        return  Ayudante.blockposDentroPoligono(vertices);
    }

    //getter setter vertices
    public ArrayList<BlockPos> getVertices(){
        return this.vertices;
    }
    
  
    
}
