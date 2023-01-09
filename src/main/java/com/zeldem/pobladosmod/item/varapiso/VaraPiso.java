package com.zeldem.pobladosmod.item.varapiso;

import java.util.ArrayList;
import java.util.Random;

import com.zeldem.pobladosmod.item.ModPobladosToolTab;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Piso;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.BodegaConstruccion;
import com.zeldem.pobladosmod.pobladosHelper.Maistro;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VaraPiso extends Item {

    public VaraPiso(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        InteractionResult resultado = InteractionResult.PASS;

        Player player = contexto.getPlayer();
        Maistro rodrigo = new Maistro(contexto.getLevel());
        if (!player.level.isClientSide) {
            // change the block to a black terracotta block
            contexto.getLevel().setBlock(contexto.getClickedPos(), Blocks.BLACK_TERRACOTTA.defaultBlockState(), 2);

            // Check if the player has already saved 4 persistent block positions
            if (player.getPersistentData().getInt("vara_piso") >= 4) {
                
                ArrayList<BlockState> materiales = new ArrayList<BlockState>();

                if (!player.getOffhandItem().isEmpty()) {
                    Block m1= Block.byItem(player.getOffhandItem().getItem());
                    if (m1==Blocks.DIRT) {
                        // The offhand item is a block item
                        // Do something with the block item...
                        materiales.addAll(Ayudante.selecciona(10, BodegaConstruccion.FARMLANDS));
                    }
                } else {
                    // The player is not holding any item in their off-hand
                    materiales.addAll(Ayudante.selecciona(8, BodegaConstruccion.WOODS));
                }


                // Consume the saved block positions and store them in an array list
                ArrayList<BlockPos> puntosBase = Ayudante.consumePersistentBlockposArr(player, "vara_piso");
                // create a new piso
                Piso piso = new Piso(puntosBase, materiales);
                // build the piso
                rodrigo.construye(piso);

            } else {
                // Save the current block position as a persistent block position
                BlockPos blockPos = contexto.getClickedPos();
                Ayudante.savePersistentBlockpos(player, "vara_piso", blockPos);
            }
            resultado = InteractionResult.SUCCESS;
            player.getCooldowns().addCooldown(this, 7);
        }

    return resultado;
}

}
