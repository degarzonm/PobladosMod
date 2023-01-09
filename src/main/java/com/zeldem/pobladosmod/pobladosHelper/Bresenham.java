package com.zeldem.pobladosmod.pobladosHelper;

import java.util.ArrayList;

import net.minecraft.core.BlockPos;

public class Bresenham {

    
    public static ArrayList<BlockPos> linea(BlockPos BP1, BlockPos BP2) {
        // Inicializamos la lista de voxeles que vamos a retornar
        ArrayList<BlockPos> voxeles = new ArrayList<>();

        //define las variables individuales
        int x1 = BP1.getX();
        int y1 = BP1.getY();
        int z1 = BP1.getZ();
        int x2 = BP2.getX();
        int y2 = BP2.getY();
        int z2 = BP2.getZ();

        // añadimos el primer punto a la lista de voxeles
        voxeles.add(BP1);

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dz = Math.abs(z2 - z1);

        int xs = (x2 > x1) ? 1 : -1;
        int ys = (y2 > y1) ? 1 : -1;
        int zs = (z2 > z1) ? 1 : -1;

        if (dx >= dy && dx >= dz) {
            int p1 = 2 * dy - dx;
            int p2 = 2 * dz - dx;
            while (x1 != x2) {
                x1 += xs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * dx;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2 * dx;
                }
                p1 += 2 * dy;
                p2 += 2 * dz;
                voxeles.add(new BlockPos(x1, y1, z1));
            }
        }else if(dy >= dx && dy >= dz){
            int p1 = 2 * dx - dy;
            int p2 = 2 * dz - dy;
            while (y1 != y2) {
                y1 += ys;
                if (p1 >= 0) {
                    x1 += xs;
                    p1 -= 2 * dy;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2 * dy;
                }
                p1 += 2 * dx;
                p2 += 2 * dz;
                voxeles.add(new BlockPos(x1, y1, z1));
            }
        }
        else{
            int p1 = 2 * dy - dz;
            int p2 = 2 * dx - dz;
            while (z1 != z2) {
                z1 += zs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * dz;
                }
                if (p2 >= 0) {
                    x1 += xs;
                    p2 -= 2 * dz;
                }
                p1 += 2 * dy;
                p2 += 2 * dx;
                voxeles.add(new BlockPos(x1, y1, z1));
            }
        }


        return voxeles;
    }
}
