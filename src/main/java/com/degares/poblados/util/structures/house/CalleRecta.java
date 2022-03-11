package com.degares.poblados.util.structures.house;

import java.util.ArrayList;

import com.degares.poblados.Helpers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class CalleRecta {
	
	private BlockPos p0,p1;
	private UseOnContext mundo;
	private ArrayList<BlockPos> puntos_centrales;
	private ArrayList<BlockPos> puntos_laterales;


	public CalleRecta(UseOnContext mundo,BlockPos p0, BlockPos p1) {
		this.mundo=mundo;
		this.p0 = p0;
		this.p1 = p1;
		puntos_centrales=Helpers.BresenhamLinea(p0, p1);

	}

	public boolean generarEnMundo(){
		boolean r=true;
		puntos_centrales.forEach((BlockPos s)->{
			mundo.getLevel().setBlockAndUpdate(s, Blocks.COBBLESTONE.defaultBlockState());
		});

		return r;
	}

}




















