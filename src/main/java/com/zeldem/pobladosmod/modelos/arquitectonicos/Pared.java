package com.zeldem.pobladosmod.modelos.arquitectonicos;

import java.util.ArrayList;

import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.Bresenham;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Pared extends Arquitectonico{
    private BlockPos p1;
    private BlockPos p2;
    private int altura;

    // Constructor de la clase
    public Pared(BlockPos p1, BlockPos p2, ArrayList<BlockState> material, int altura , Level nivel) {
        super( material);
        this.p1 = p1;
        this.p2 = p2;
        this.altura = altura;
        this.plano_de_bloques = calcularBloques(p1, p2, altura ,nivel);
    }

    // create a function that calculate the blocks that compose the wall
    public static ArrayList<BlockPos> calcularBloques(BlockPos p1, BlockPos p2, int altura, Level nivel) {
        ArrayList<BlockPos> plano = new ArrayList<BlockPos>();
        ArrayList<BlockPos> linea_base = Bresenham.linea(p1, p2);
        
            ArrayList<BlockPos> wall = new ArrayList<>();
        
            // Iterate through the block positions in the line
            for (BlockPos pos : linea_base) {
                // Find the lowest solid block
                BlockPos basePos = Ayudante.encuentraBase(nivel, pos);
        
                // Add the base block to the wall if not null
                if (basePos != null){
                        wall.add(basePos);
        
                // Add h blocks above the base block
                for (int i = 1; i <= altura; i++) 
                        wall.add(basePos.above(i));
                    }
                
            }
        
            return wall;
        
    }

}
