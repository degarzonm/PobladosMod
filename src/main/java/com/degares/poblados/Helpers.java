package com.degares.poblados;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Random;

public class Helpers {

	long seed;
	private Random r;

	public Helpers(long seed) {
		this.seed = seed;
		r = new Random(seed);
	}

	// Returns a random number between min and max
	public int rand(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}

	// place a block in the position (x,y,z) in the Level based on the origin
	// blockPos, facing and the offset (i,j,k)
	public boolean setBlock_relative(Level mundo, BlockPos e, Direction f, int i, int j, int k, BlockState block) {

		int x, y, z;

		switch (f) {
			case SOUTH:
			default: {
				x = i;
				y = j;
				z = k;

				break;
			}
			case NORTH: {
				x = -i;
				y = j;
				z = -k;
				block = block.rotate(mundo, e, Rotation.CLOCKWISE_180);
				break;
			}
			case EAST: {
				x = k;
				y = j;
				z = -i;
				block = block.rotate(mundo, e, Rotation.COUNTERCLOCKWISE_90);
				break;
			}
			case WEST: {
				x = -k;
				y = j;
				z = i;
				block = block.rotate(mundo, e, Rotation.CLOCKWISE_90);
				break;
			}
		}

		BlockPos finalLoc = new BlockPos(e.getX() + x, e.getY() + y, e.getZ() + z);

		return mundo.setBlockAndUpdate(finalLoc, block);
	}

	public BlockPos getBlockPos_relative(BlockPos e, Direction ori, int i, int j, int k) {
		BlockPos b;
		int x, y, z;
		switch (ori) {
			case SOUTH:
			default: {
				x = i;
				y = j;
				z = k;

				break;
			}
			case NORTH: {
				x = -i;
				y = j;
				z = -k;
				break;
			}
			case EAST: {
				x = k;
				y = j;
				z = -i;
				break;
			}
			case WEST: {
				x = -k;
				y = j;
				z = i;
				break;
			}
		}
		b = new BlockPos(e.getX() + x, e.getY() + y, e.getZ() + z);
		return b;
	}

	public static String printBlockCoords(BlockPos p) {
		return "(" + p.getX() + "," + p.getY() + "," + p.getZ() + ")";
	}

	// valor absoluto de un entero
	public static int abs(int i) {
		if (i < 0)
			return -i;
		return i;
	}

	//mayor de tres enteros
	public static int max(int a, int b, int c) {
		if (a > b) {
			if (a > c)
				return a;
			else
				return c;
		} else {
			if (b > c)
				return b;
			else
				return c;
		}
	}
	
	public static ArrayList<BlockPos> BresenhamLinea(BlockPos P0, BlockPos P1) {

		ArrayList<BlockPos> voxeles = new ArrayList<>();
		// define x0 y x1
		int x0 = P0.getX();
		int x1 = P1.getX();
		// define y0 y y1
		int y0 = P0.getY();
		int y1 = P1.getY();

		// define z0 y z1
		int z0 = P0.getZ();
		int z1 = P1.getZ();

		//parametros iniciales
		int dx = abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
		int dy = abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
		int dz = abs(z1 - z0), sz = z0 < z1 ? 1 : -1;
		int dm = max(dx, dy, dz), i = dm; /* maxima diferencia */

		for (x1 = y1 = z1 = i / 2; i-- >= 0;) { /* loop */
			//
			//agregamos el bloque a la lista
			voxeles.add(new BlockPos(x0, y0, z0));
			x1 -= dx;
			if (x1 < 0) {
				x1 += dm;
				x0 += sx;
			}
			y1 -= dy;
			if (y1 < 0) {
				y1 += dm;
				y0 += sy;
			}
			z1 -= dz;
			if (z1 < 0) {
				z1 += dm;
				z0 += sz;
			}
		}

		return voxeles;
	}
}
