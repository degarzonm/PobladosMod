package com.zeldem.pobladosmod.pobladosHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class Ayudante {


    public static ArrayList<BlockState> selecciona(int n, ArrayList<BlockState> opciones){
        ArrayList<BlockState> seleccion = new ArrayList<BlockState>();
        Random random = new Random();

        // Generate n random integers between 0 and 15
        for (int i = 0; i < n; i++) {
            int colorIndex = random.nextInt(opciones.size());
            seleccion.add(opciones.get(colorIndex));
        }

        return seleccion;
    }
   

    public static ArrayList<BlockPos> blockposDentroPoligono(ArrayList<BlockPos> poligono) {
        ArrayList<BlockPos> pointsInside = new ArrayList<>();
      
        // Find the bounding box of the polygon
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        for (int i = 0; i < poligono.size(); i++) {
          minX = Math.min(minX, poligono.get(i).getX());
          maxX = Math.max(maxX, poligono.get(i).getX());
          minZ = Math.min(minZ, poligono.get(i).getZ());
          maxZ = Math.max(maxZ, poligono.get(i).getZ());
        }
        // set a height with the y of any vertice
        int height = poligono.get(0).getY();
        // Iterate over all points in the bounding box
        for (int x = minX; x <= maxX; x++) {
          for (int z = minZ; z <= maxZ; z++) {
            // Check if point is inside the polygon
            BlockPos point = new BlockPos(x, height, z);
            if (puntoDentro(poligono, point)) {
              pointsInside.add(point);
            }
          }
        }
        //use bresenham to build lines between the points of the polygon
        for (int i = 0; i < poligono.size(); i++) {
          pointsInside.addAll(Bresenham.linea(poligono.get(i), poligono.get((i + 1) % poligono.size())));
      }
      //remove duplicates
      

        return pointsInside;
      }

      
      static boolean intersecta(BlockPos pn_0, BlockPos pn_1, BlockPos P) {
         float pz = P.getZ();  
        if (pn_0.getZ() > pn_1.getZ())
            return intersecta(pn_1, pn_0, P);

        if (P.getZ() == pn_0.getZ() || P.getZ() == pn_1.getZ())
        pz += 0.0001;

        if (pz > pn_1.getZ() || pz < pn_0.getZ() || P.getX() >= Math.max(pn_0.getX(), pn_1.getX()))
            return false;

        if (P.getX() < Math.min(pn_0.getX(), pn_1.getX()))
            return true;

        double red = (pz - pn_0.getZ()) / (double) (P.getX() - pn_0.getX());
        double blue = (pn_1.getZ() - pn_0.getZ()) / (double) (pn_1.getX() - pn_0.getX());
        return red >= blue;
    }

    static boolean puntoDentro(ArrayList<BlockPos> poligono, BlockPos p) {
        boolean inside = false;
        int len = poligono.size();
        for (int i = 0; i < len; i++) {
            if (intersecta(poligono.get(i), poligono.get((i + 1) % len), p))
                inside = !inside;
        }
        return inside;
    }

     public BlockPos blockPosRelativo(BlockPos e, Direction ori, int i, int j, int k) {
    int x = 0, y = j, z = 0;
    switch (ori) {
        case NORTH: x = -i; z = -k; break;
        case EAST: x = k; z = -i; break;
        case WEST: x = -k; z = i; break;
        case SOUTH:
        default: x = i; z = k; break;
    }
    return new BlockPos(e.getX() + x, e.getY() + y, e.getZ() + z);
}

    //method that retrieves a base blockpos, solid and non solid above
 
    public static BlockPos encuentraBase(Level level, BlockPos pos) {
        // Check 30 blocks above and below the given block position
        for (int i = -30; i <= 30; i++) {
            BlockPos currentPos = pos.offset(0, i, 0);
            Material currentState = level.getBlockState(currentPos).getMaterial();
            Material aboveState = level.getBlockState(currentPos.above()).getMaterial();
            if (currentState.isSolid() && !aboveState.isSolid()) {
                return currentPos;
            }
        }
        return null;
    }

    // method that saves persistent data in a player entity
    public static void savePersistentBlockpos(Player player, String key, BlockPos b) {
        int i = 0;
        //incremnt the key if it already exists
        if(player.getPersistentData().contains(key)){
            i = player.getPersistentData().getInt(key);
            player.getPersistentData().putInt(key, i+1);
        }else{
            player.getPersistentData().putInt(key, 1);
        }
        player.getPersistentData().putInt(key+ "x"+i, b.getX());
        player.getPersistentData().putInt(key+ "y"+i, b.getY());
        player.getPersistentData().putInt(key+ "z"+i, b.getZ());
        player.sendSystemMessage(
                Component.literal("saved: " + key+i + ":(" + b.getX() + "," + b.getY() + "," + b.getZ() + ")"));
    }

    // method that gets persistent data from a player entity
    public static BlockPos consumePersistentBlockpos(Player player, String key) {
        BlockPos P_1 = new BlockPos(player.getPersistentData().getInt(key + "x0"),
                player.getPersistentData().getInt(key + "y0"),
                player.getPersistentData().getInt(key + "z0"));

        player.getPersistentData().remove(key);
        player.getPersistentData().remove(key + "x0");
        player.getPersistentData().remove(key + "y0");
        player.getPersistentData().remove(key + "z0");

        player.sendSystemMessage(Component.literal("consumed: " + key+ ":(" + P_1.getX() + "," + P_1.getY() + "," + P_1.getZ() + ")"));
        return P_1;
    }

    public static ArrayList<BlockPos> consumePersistentBlockposArr(Player player, String key) {
        ArrayList<BlockPos> arr = new ArrayList<BlockPos>();
        int i = player.getPersistentData().getInt(key);
        for (int j = 0; j < i; j++) {
            arr.add(new BlockPos(player.getPersistentData().getInt(key + "x" + j),
                    player.getPersistentData().getInt(key + "y" + j),
                    player.getPersistentData().getInt(key + "z" + j)));
        }
        player.getPersistentData().remove(key);
        for (int j = 0; j < i; j++) {
            player.getPersistentData().remove(key + "x" + j);
            player.getPersistentData().remove(key + "y" + j);
            player.getPersistentData().remove(key + "z" + j);
        }
        return arr;
    }


    public static int distanciaManhattan(BlockPos blockPos, BlockPos vertice) {
        
        return Math.abs(blockPos.getX() - vertice.getX()) + Math.abs(blockPos.getY() - vertice.getY())
                + Math.abs(blockPos.getZ() - vertice.getZ());
    }

    public static int distanciaEuclidea(BlockPos blockPos, BlockPos vertice) {
        
        return (int) Math.sqrt(Math.pow(blockPos.getX() - vertice.getX(), 2)
                + Math.pow(blockPos.getY() - vertice.getY(), 2) + Math.pow(blockPos.getZ() - vertice.getZ(), 2));
    }


    public static BlockPos centro(ArrayList<BlockPos> vertices) {
        
        int x = 0;
        int y = 0;
        int z = 0;
        for (BlockPos vertice : vertices) {
            x += vertice.getX();
            y += vertice.getY();
            z += vertice.getZ();
        }
        return new BlockPos(x / vertices.size(), y / vertices.size(), z / vertices.size());
    }
}
