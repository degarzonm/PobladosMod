package com.zeldem.pobladosmod.modelos;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

public class Casa {
    // Atributos de la clase
    private BlockPos entrada;
    private Sala[] salas;
  
    // Constructor de la clase
    public Casa(Sala[] salas, BlockPos entrada) {
      this.salas = salas;
      this.entrada = entrada;
    }
  
    // Método que construye la casa
    public void construir() {
      // Primero construimos la entrada
      //world.setBlock(entrada.getX(), entrada.getY(), entrada.getZ(), entrada.getMaterial());
  
      // Luego construimos las salas
      
    }
  }
  