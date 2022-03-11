package com.degares.poblados.util.structures.house.sala;

import com.degares.poblados.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;

public class Sala{
	Helpers h = new Helpers(1);
	BlockPos e;	//puerta entrada
	public final Direction orientacion;
	public final int delta_i;
	public final int delta_d;
	public final int frente;
	public final int altura;
	boolean pared_entrada=true,
			pared_opuesta=true,
			pared_izq=true,
			pared_der=true;
	
	public static final BlockState[] BLOQUES_PARED= {
			 Blocks.WHITE_CONCRETE.defaultBlockState()
			,Blocks.COBBLESTONE.defaultBlockState()
			,Blocks.BRICKS.defaultBlockState()
			,Blocks.BRICKS.defaultBlockState()};
	
	
	public Sala(BlockPos entrada,Direction orientacion, int d_i, int d_d, int altura, int frente) {
		delta_i=d_i>=1?d_i:1;
		delta_d=d_d>=1?d_d:2;
		this.frente=frente>=3?frente:3;
		this.altura=altura>=3?altura:3;
		this.e=entrada;
		this.orientacion=orientacion;
	}

	public Sala(BlockPos entrada,Direction orientacion, int d_i, int d_d, int altura, int frente, boolean pared_entrada, boolean pared_frente, boolean pared_izq, boolean pared_der) {
		this.pared_entrada=pared_entrada;
		this.pared_opuesta=pared_frente;
		this.pared_izq=pared_izq;
		this.pared_der=pared_der;

		delta_i=d_i>=1?d_i:1;
		delta_d=d_d>=1?d_d:2;
		this.frente=frente>=3?frente:3;
		this.altura=altura>=3?altura:3;
		this.e=entrada;
		this.orientacion=orientacion;
	}
	
	
	public static final BlockState[] BLOQUES_SUELO= {Blocks.OAK_PLANKS.defaultBlockState()
													,Blocks.SPRUCE_PLANKS.defaultBlockState()
													,Blocks.DARK_OAK_PLANKS.defaultBlockState()
													,Blocks.CRACKED_STONE_BRICKS.defaultBlockState()};
	
	public static final BlockState[] BLOQUES_COLUMNA= 	{Blocks.SPRUCE_LOG.defaultBlockState()
														,Blocks.OAK_LOG.defaultBlockState()
	};
	
	public static final BlockState[] BLOQUES_TECHO= {Blocks.OAK_SLAB.defaultBlockState()
													,Blocks.BIRCH_SLAB.defaultBlockState()
													,Blocks.SPRUCE_SLAB.defaultBlockState()};
	
	
	public void obrero(UseOnContext contexto) {
		limpiarSala(contexto);
		int aleatorio = h.rand(0, BLOQUES_PARED.length-1);
		BlockState bloque_pared=BLOQUES_PARED[aleatorio];
		buildParedes(contexto,altura,bloque_pared);

		buildTecho_plano(contexto,Blocks.BIRCH_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM));
		buildTecho_triangulo_slabs(contexto,BLOQUES_TECHO);
		
		
		buildSuelo(contexto,BLOQUES_SUELO);
		
		buildDecoraciones(contexto,BLOQUES_COLUMNA);
		buildPuertas(contexto,Blocks.BIRCH_DOOR);
	}
	
	

	public void limpiarSala(UseOnContext contexto) {
		for(int i=-delta_d+1;i<delta_i;i++) {
			for(int j=1;j<=altura;j++) {
				for(int k=1;k<frente;k++) {
					h.setBlock_relative(contexto.getLevel(), e, orientacion, i, j, k, Blocks.AIR.defaultBlockState());
				}
			}
		}
	}
	
	public void buildParedes(UseOnContext contexto, int altura, BlockState block) {
		
		for(int j=0;j<=altura;j++) {
			for(int i=-delta_d;i<=delta_i;i++) {
				if(pared_entrada)h.setBlock_relative(contexto.getLevel(), e, orientacion, i, j, 0, block);
				if(pared_opuesta)h.setBlock_relative(contexto.getLevel(), e, orientacion, i, j, frente, block);
			}
			for(int k=0;k<=frente;k++) {
				if(pared_izq)h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i, j, k, block);
				if(pared_der)h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d, j, k, block);
			}
		}
	}
	
	public void buildPuertas(UseOnContext contexto, Block puerta){
		h.setBlock_relative(contexto.getLevel(), e.above(), orientacion, 0, 0, 0,
				puerta.defaultBlockState().setValue(DoorBlock.FACING, orientacion));


		h.setBlock_relative(contexto.getLevel(), e.above().above(), orientacion, 0, 0, 0,
				puerta.defaultBlockState().setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER).setValue(DoorBlock.FACING, orientacion));
	}
	
	
	
	public void buildTecho_plano(UseOnContext contexto, BlockState block) {
		for(int i=-delta_d;i<=delta_i;i++) {
			for(int k=0;k<=frente;k++) {
				h.setBlock_relative(contexto.getLevel(), e, orientacion, i, altura, k, block);
			}
		}
	}
	
	public void buildTecho_triangulo_slabs(UseOnContext contexto, BlockState[] blocks) {
		
			for(int k=-1;k<=frente+1;k++) {
				int r=delta_i+1,s=-delta_d-1;
				for(int i=0;i<=(delta_d+delta_i)/2+1;i++) {
					BlockState block=blocks[h.rand(0, blocks.length-1)];
					h.setBlock_relative(contexto.getLevel(), e, orientacion, r, altura+(i/2), k, block.setValue(SlabBlock.TYPE, i%2==0?SlabType.BOTTOM:SlabType.DOUBLE));
					block=blocks[h.rand(0, blocks.length-1)];
					h.setBlock_relative(contexto.getLevel(), e, orientacion, s, altura+(i/2), k, block.setValue(SlabBlock.TYPE, i%2==0?SlabType.BOTTOM:SlabType.DOUBLE));
					r--;s++;
				}
			}
			
		
	}
	
	public void buildSuelo(UseOnContext contexto, BlockState[] bloques) {
		for(int i=-delta_d+1;i<delta_i;i++) {
			for(int k=1;k<frente;k++) {
				h.setBlock_relative(contexto.getLevel(), e, orientacion, i, 0, k, bloques[h.rand(0, bloques.length-1)]);
			}
		}
	}
	
	public void buildDecoraciones(UseOnContext contexto,	BlockState[] columnas
								//	ArrayList<BlockState> ventanas,
								//	ArrayList<BlockState> luces, 
								//ArrayList<BlockState> muebles
			) {
				BlockPlaceContext contexto_ventanas= new BlockPlaceContext(contexto);


		//luces:
		{
			h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i-1, altura-1, 1, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, Boolean.TRUE));
			h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d+1, altura-1, 1, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, Boolean.TRUE));
			h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i-1, altura-1, frente-1, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, Boolean.TRUE));
			h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d+1, altura-1, frente-1, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, Boolean.TRUE));
		}
		
		//ventanas
		{
			for(int j=2;j<altura;j++) {
				for (int i=-delta_d+1;i<delta_i;i++) {
					if(pared_opuesta)
					h.setBlock_relative(contexto.getLevel(),e,orientacion,i,j,frente,
										Blocks.GLASS_PANE.defaultBlockState().setValue(BlockStateProperties.EAST, true).setValue(BlockStateProperties.WEST, true));


				if((i>1 || i<-1) && pared_entrada) {
					if(pared_entrada)h.setBlock_relative(contexto.getLevel(),e,orientacion,i,j,0,Blocks.GLASS_PANE.defaultBlockState().setValue(BlockStateProperties.EAST, true).setValue(BlockStateProperties.WEST, true));
					}
				
				}
				for (int k=1;k<frente;k++) {
					if(pared_izq)h.setBlock_relative(contexto.getLevel(),e,orientacion,delta_i,j,k,Blocks.GLASS_PANE.defaultBlockState().setValue(BlockStateProperties.NORTH, true).setValue(BlockStateProperties.SOUTH, true));
					if(pared_der)h.setBlock_relative(contexto.getLevel(),e,orientacion,-delta_d,j,k,Blocks.GLASS_PANE.defaultBlockState().setValue(BlockStateProperties.NORTH, true).setValue(BlockStateProperties.SOUTH, true));
				}
				
			}
		}
		
		//columnas
		{
			BlockState columna=columnas[h.rand(0, columnas.length-1)];
			for(int j=1;j<altura;j++) {
				if(j==altura-1) {
					for(int i=-delta_d;i<=delta_i;i++) {
						h.setBlock_relative(contexto.getLevel(), e, orientacion, i, j, 0, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
						h.setBlock_relative(contexto.getLevel(), e, orientacion, i, j, frente, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
					}
					for(int k=0;k<=frente;k++) {
						h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i, j, k, columna);
						h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d, j, k, columna);
					}
				}
				
				h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i, j, 0, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d, j, frente, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				h.setBlock_relative(contexto.getLevel(), e, orientacion, -delta_d, j, 0, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				h.setBlock_relative(contexto.getLevel(), e, orientacion, delta_i, j, frente, columna.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				
			}
		}
		
		
	}
	
	//returns the blockPos of a left entrance for the next sala or exit
	public BlockPos getALeftEntrance(float location){
		return new BlockPos(h.getBlockPos_relative(e, orientacion, delta_i, 0, (int)(location*(frente-1))));
	}
	
	//returns the blockPos of a front entrance for the next sala or exit
	public BlockPos getAFrontEntrance(float location) {
		return new BlockPos(h.getBlockPos_relative(e, orientacion, h.rand(-delta_d+1, delta_i-1), 0, frente));
	}
	
	//returns the blockPos of a right entrance for the next sala or exit
	public BlockPos getARightEntrance(float location) {
		return new BlockPos(h.getBlockPos_relative(e, orientacion, -delta_d, 0, h.rand(1, frente-1)));
	}

	
	public String toString() {
		return "Sala simple:("+e.getX()+","+e.getY()+","+e.getZ()+")  dir:"+orientacion;
	}
	

	

}
