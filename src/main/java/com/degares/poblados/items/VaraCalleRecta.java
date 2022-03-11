package com.degares.poblados.items;
import com.degares.poblados.util.structures.house.CalleRecta;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class VaraCalleRecta extends Item {

    public VaraCalleRecta(Properties p_4) {
        super(p_4);
    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {

        Player player = contexto.getPlayer();

        if (player.getPersistentData().contains("poblados_calle_recta_x0")) {

            // se guarda el punto p1, que es el punto final de la calle
            BlockPos P_1 = contexto.getClickedPos();
            // creamos el punto p0 que es el punto inicial de la calle, a partir de los
            // datos en el jugador
            BlockPos P_0 = new BlockPos(player.getPersistentData().getInt("poblados_calle_recta_x0"),
                    player.getPersistentData().getInt("poblados_calle_recta_y0"),
                    player.getPersistentData().getInt("poblados_calle_recta_z0"));

            // creamos la calle a partir de p0 y p1
            CalleRecta calle = new CalleRecta(contexto, P_0, P_1);

            calle.generarEnMundo();

            // removemos los tags, para que el jugador pueda volver a empezar a dibujar la
            // calle
            player.getPersistentData().remove("poblados_calle_recta_x0");
            player.getPersistentData().remove("poblados_calle_recta_y0");
            player.getPersistentData().remove("poblados_calle_recta_z0");

        } else {
            // guardamos el punto p0 persistentemente, que es el punto inicial de la calle
            BlockPos P_0 = contexto.getClickedPos();
            player.getPersistentData().putInt("poblados_calle_recta_x0", P_0.getX());
            player.getPersistentData().putInt("poblados_calle_recta_y0", P_0.getY());
            player.getPersistentData().putInt("poblados_calle_recta_z0", P_0.getZ());

        }
        return super.useOn(contexto);
    }

}
