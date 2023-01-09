package com.zeldem.pobladosmod.item.varamuro;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Pared;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.Maistro;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VaraPared extends Item {


    public VaraPared(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        Player player = contexto.getPlayer();
        Maistro rodrigo = new Maistro(contexto.getLevel());
        if (!player.level.isClientSide) {

            ArrayList<BlockState> materiales = new ArrayList<BlockState>();
            materiales.add(Blocks.RED_TERRACOTTA.defaultBlockState());

            if (player.getPersistentData().contains("vara_pared")) {
                if (!player.getOffhandItem().isEmpty()) {
                    // The player is holding an item in their off-hand
                    Item offhandItem = player.getOffhandItem().getItem();
                    // Do something with the offhand item...
                } else {
                    // The player is not holding any item in their off-hand
                }
                // creamos el punto p1 que es el punto inicial del pared, a partir de los
                // datos en el jugador
                BlockPos P_1 = Ayudante.consumePersistentBlockpos(player, "vara_pared");
                // se guarda el punto p2, que es el punto final del pared
                BlockPos P_2 = contexto.getClickedPos();
                // creamos el pared
                Pared pared = new Pared(P_1, P_2, materiales, 8, contexto.getLevel());
                rodrigo.construye(pared);
            } else {
                // guardamos el punto p1 persistentemente, que es el punto inicial del pared
                BlockPos P_1 = contexto.getClickedPos();
                Ayudante.savePersistentBlockpos(player, "vara_pared", P_1);
            }
            player.getCooldowns().addCooldown(this, 5);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}
