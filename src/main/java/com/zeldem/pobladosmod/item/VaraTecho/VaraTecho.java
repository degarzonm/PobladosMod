package com.zeldem.pobladosmod.item.VaraTecho;

import java.util.ArrayList;
import java.util.Random;

import com.zeldem.pobladosmod.item.ModPobladosToolTab;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Techo;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.BodegaConstruccion;
import com.zeldem.pobladosmod.pobladosHelper.Maistro;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class VaraTecho extends Item {


    public VaraTecho(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        InteractionResult resultado = InteractionResult.PASS;

        Player player = contexto.getPlayer();
        Maistro rodrigo = new Maistro(contexto.getLevel());
        if (!player.level.isClientSide) {
            // Check if the player has already saved 4 persistent block positions
            if (player.getPersistentData().getInt("vara_techo") >= 4) {
                ArrayList<BlockState> materiales = new ArrayList<BlockState>();

                // Create a new Random object
                Random random = new Random();

                // Generate 6 random integers between 0 and 15
                for (int i = 0; i < 6; i++) {
                    int colorIndex = random.nextInt(16);
                    // Add the corresponding terracotta color to the ArrayList
                    materiales.add(BodegaConstruccion.WOODS.get(colorIndex));
                }

                // Consume the saved block positions and store them in an array list
                ArrayList<BlockPos> puntosBase = Ayudante.consumePersistentBlockposArr(player,"vara_techo");
                // Create a new techo
                Techo techo = new Techo(puntosBase, materiales);
                // Build the techo
                rodrigo.construye(techo);

            } else {
                // Save the current block position as a persistent block position
                BlockPos blockPos = contexto.getClickedPos();
                Ayudante.savePersistentBlockpos(player,"vara_techo", blockPos);
            }
            resultado = InteractionResult.SUCCESS;
            player.getCooldowns().addCooldown(this, 7);
        }

        return resultado ;
    }
}

