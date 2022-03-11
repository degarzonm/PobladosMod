package com.degares.poblados.items;

import com.degares.poblados.util.structures.house.Casa;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VaraCasita extends Item {

    public VaraCasita(Properties p_41383_) {
        super(p_41383_);

    }

    @Override
    public InteractionResult useOn(UseOnContext contexto) {
        Direction orientacion = contexto.getHorizontalDirection();
        BlockPos centro =  contexto.getClickedPos();

        Casa h1=new Casa(contexto,"S{S{,,S},,S(5,6,4)}");
        h1.generarEnMundo();


        return super.useOn(contexto);
    }

    public void placeSimpleWall(){

    }

}