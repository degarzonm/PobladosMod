package com.zeldem.pobladosmod.modelos.arquitectonicos;

import java.util.ArrayList;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class Techo extends Arquitectonico {

    private ArrayList<BlockPos> vertices;
    private BlockPos centro;
    // constructor de la clase
    public Techo(ArrayList<BlockPos> vertices, ArrayList<BlockState> material) {
        super(material);
        this.vertices = vertices;
        this.centro = Ayudante.centro(vertices);
        this.plano_de_bloques = calcularBloquesRegular(vertices, centro);
        //this.plano_de_bloques = calcularBloquesCentro(vertices, centro);
    }

    // create a function that calculate the blocks that compose the wall
    public static ArrayList<BlockPos> calcularBloquesRegular(ArrayList<BlockPos> vertices, BlockPos centro) {
        ArrayList<BlockPos> plano_de_bloques = Ayudante.blockposDentroPoligono(vertices);
        ArrayList<BlockPos> plano_nuevos = new ArrayList<BlockPos>();
        for (BlockPos blockPos : plano_de_bloques) {
            //min distance from blockpos to vertices
            int h = Ayudante.distanciaEuclidea(blockPos, centro);
            for (int j=0; j<vertices.size(); j++) {
                int d = Ayudante.distanciaEuclidea(blockPos, vertices.get(j));
                if(d<h){
                    h=d;
                }
            }
            //add the blocks that are in the roof
            /*for (int i = 1; i < h; i++) {
                plano_nuevos.add(blockPos.above(i));
            }*/
            plano_nuevos.add(blockPos.above(h/3));
            plano_nuevos.add(blockPos.above(h/3+1));
        }
        plano_de_bloques.addAll(plano_nuevos);
        return plano_nuevos;
    }

    public static ArrayList<BlockPos> calcularBloquesCentro(ArrayList<BlockPos> vertices, BlockPos centro) {
        ArrayList<BlockPos> plano_de_bloques = Ayudante.blockposDentroPoligono(vertices);
        ArrayList<BlockPos> plano_nuevos = new ArrayList<BlockPos>();
        for (BlockPos blockPos : plano_de_bloques) {
            //min distance from blockpos to vertices
            int h= Ayudante.distanciaManhattan(blockPos, centro);
            //add the blocks that are in the roof
            /*for (int i = 1; i < h; i++) {
                plano_nuevos.add(blockPos.above(i));
            }*/
            plano_nuevos.add(blockPos.above(h/2));
        }
        plano_de_bloques.addAll(plano_nuevos);
        return plano_nuevos;
    }

    // getter setter vertices
    public ArrayList<BlockPos> getVertices() {
        return this.vertices;
    }

}
