package com.zeldem.pobladosmod.modelos.arquitectonicos;

import java.util.ArrayList;

import com.zeldem.pobladosmod.pobladosHelper.Bresenham;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class Muro extends Arquitectonico {

    // Constructor de la clase
    public Muro(BlockPos p1, BlockPos p2, ArrayList<BlockState> material) {
        super( material);
        this.plano_de_bloques = calcularBloques(p1, p2);
    }

    // create a function that calculate the blocks that compose the wall
    public static ArrayList<BlockPos> calcularBloques(BlockPos p1, BlockPos p2) {
        ArrayList<BlockPos> plano = new ArrayList<BlockPos>();
        BlockPos p3 = new BlockPos(p2.getX(), p1.getY(), p2.getZ());
        int rise = (p1.getY() <= p2.getY()) ? 1 : -1;

        // Initialize k to p1.getY()
        int k = 0;
        int y0 = p1.getY();
        plano.addAll(Bresenham.linea(p1, p3));

        // Loop until k equals p2.getY()
        while (y0 + k != p2.getY()) {
            k += rise;
            plano.addAll(Bresenham.linea(p1.offset(0, k, 0), p3.offset(0, k, 0)));
        }

        return plano;
    }
}
