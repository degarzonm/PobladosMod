package com.zeldem.pobladosmod.item.varamuro;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import com.zeldem.pobladosmod.item.ModPobladosToolTab;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Muro;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.Maistro;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VaraMuro extends Item {

    private final String varamurop1x = "varamurop1";
    private final String varamurop1y = "varamurop1y";
    private final String varamurop1z = "varamurop1z";

    public VaraMuro(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        Player player = contexto.getPlayer();
        Maistro rodrigo = new Maistro(contexto.getLevel());
        if (!player.level.isClientSide) {
            ArrayList<BlockState> materiales = new ArrayList<BlockState>();
           
            if (!player.getOffhandItem().isEmpty()) {
                // The player is holding an item in their off-hand
                // Check if the offhand item is a block item
                Block m1= Block.byItem(player.getOffhandItem().getItem());
                if (m1!=Blocks.AIR) {
                    // The offhand item is a block item
                    // Do something with the block item...
                    materiales.add(m1.defaultBlockState());
                } else {
                    // The offhand item is not a block item
                    materiales.add(Blocks.SMOOTH_BASALT.defaultBlockState());
                }
            } else {
                // The player is not holding any item in their off-hand
                materiales.add(Blocks.STONE_BRICKS.defaultBlockState());
            }
            

            if (player.getPersistentData().contains("vara_muro")) {

                // creamos el punto p1 que es el punto inicial del muro, a partir de los
                // datos en el jugador
                BlockPos P_1 = Ayudante.consumePersistentBlockpos(player, "vara_muro");
                // se guarda el punto p2, que es el punto final del muro
                BlockPos P_2 = contexto.getClickedPos();
                // creamos el muro
                Muro muro = new Muro(P_1, P_2, materiales);
                rodrigo.construye(muro);
            } else {
                // guardamos el punto p1 persistentemente, que es el punto inicial del muro
                BlockPos P_1 = contexto.getClickedPos();
                Ayudante.savePersistentBlockpos(player, "vara_muro", P_1);
            }
            player.getCooldowns().addCooldown(this, 5);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}
