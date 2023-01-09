package com.zeldem.pobladosmod.item.varasala;

import java.util.ArrayList;
import java.util.HashMap;
import com.zeldem.pobladosmod.modelos.Sala;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.BodegaConstruccion;
import com.zeldem.pobladosmod.pobladosHelper.Maistro;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VaraSalaRect extends Item {

    public VaraSalaRect(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {

        InteractionResult resultado = super.useOn(contexto);

        Player player = contexto.getPlayer();
        Maistro rodrigo = new Maistro(contexto.getLevel());
        
        if (!player.level.isClientSide) {
			//change the block to a stripped jungle log
            contexto.getLevel().setBlock(contexto.getClickedPos(), Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState(),2);

               BlockPos entrada = contexto.getClickedPos();
                Direction facing = contexto.getHorizontalDirection();
                ArrayList<BlockState> matmuros = new ArrayList<BlockState>();
                ArrayList<BlockState> matpiso = new ArrayList<BlockState>();
                ArrayList<BlockState> mattecho = new ArrayList<BlockState>();
                ArrayList<BlockState> matmobiliario= new ArrayList<BlockState>();
                ArrayList<BlockState> matluces= new ArrayList<BlockState>();

                if (!player.getOffhandItem().isEmpty()) {
                    Block m1= Block.byItem(player.getOffhandItem().getItem());
                    if (m1==Blocks.SAND) {
                        // The offhand item is a block item
                        // Do something with the block item...
                        matmuros.addAll(Ayudante.selecciona(3, BodegaConstruccion.DESERT));
                        
                    }
                } else {
                    // The player is not holding any item in their off-hand
                    matmuros.addAll(Ayudante.selecciona(1, BodegaConstruccion.WOODS));
                }
                matpiso.addAll(Ayudante.selecciona(1, BodegaConstruccion.WOODS));
                mattecho.addAll(Ayudante.selecciona(2, BodegaConstruccion.DESERT));
                matmobiliario.addAll(Ayudante.selecciona(7, BodegaConstruccion.FURNITURE));
                matluces.addAll(Ayudante.selecciona(1, BodegaConstruccion.LUCES));
				// Create a map with an entry "muros" that stores the materials for the walls
				HashMap<String, ArrayList<BlockState>> materialesMap = new HashMap<>();
				materialesMap.put("muros", matmuros);
                materialesMap.put("piso", matpiso);
                materialesMap.put("techo", mattecho);
                materialesMap.put("mobiliario", matmobiliario);
                materialesMap.put("luces", matluces);

                Sala sala = new Sala(entrada, facing,8,6,5, materialesMap);
                rodrigo.construye(sala);
           
            resultado = InteractionResult.SUCCESS;
            player.getCooldowns().addCooldown(this, 7);
        }

        return resultado ;
    }
    
}

