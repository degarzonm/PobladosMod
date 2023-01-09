package com.zeldem.pobladosmod.item.varacasa;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public class VaraCasaItem extends Item {
    
    public VaraCasaItem( Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        Player player = contexto.getPlayer();

        if (player.getPersistentData().contains("poblados_calle_recta_x0")) {

        } else {

        }
        return super.useOn(contexto);
    }

}
