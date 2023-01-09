package com.zeldem.pobladosmod.pobladosHelper;

import java.util.ArrayList;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BodegaConstruccion {
  
    public static final ArrayList<BlockState> DOORS = new ArrayList<BlockState>() {
        {
            add(Blocks.ACACIA_DOOR.defaultBlockState());
            add(Blocks.BIRCH_DOOR.defaultBlockState());
            add(Blocks.DARK_OAK_DOOR.defaultBlockState());
            add(Blocks.JUNGLE_DOOR.defaultBlockState());
            add(Blocks.OAK_DOOR.defaultBlockState());
            add(Blocks.SPRUCE_DOOR.defaultBlockState());
            add(Blocks.CRIMSON_DOOR.defaultBlockState());
            add(Blocks.WARPED_DOOR.defaultBlockState());
        }};
    
    public static final ArrayList<BlockState> DESERT = new ArrayList<BlockState>() {
        {
            add(Blocks.SAND.defaultBlockState());
            add(Blocks.SANDSTONE.defaultBlockState());
            add(Blocks.CHISELED_SANDSTONE.defaultBlockState());
            add(Blocks.CUT_SANDSTONE.defaultBlockState());
            add(Blocks.SMOOTH_SANDSTONE.defaultBlockState());
            add(Blocks.SANDSTONE_STAIRS.defaultBlockState());
            add(Blocks.SANDSTONE_WALL.defaultBlockState());
            add(Blocks.SMOOTH_SANDSTONE_STAIRS.defaultBlockState());
            add(Blocks.SANDSTONE_SLAB.defaultBlockState());
            add(Blocks.SANDSTONE_WALL.defaultBlockState());
            //red sand
            add(Blocks.RED_SANDSTONE.defaultBlockState());
            add(Blocks.CHISELED_RED_SANDSTONE.defaultBlockState());
            add(Blocks.CUT_RED_SANDSTONE.defaultBlockState());
            add(Blocks.SMOOTH_RED_SANDSTONE.defaultBlockState());
            add(Blocks.RED_SANDSTONE_STAIRS.defaultBlockState());
            add(Blocks.RED_SANDSTONE_SLAB.defaultBlockState());
        }};

    
    // change terracota colors to an arraylist
    public static final ArrayList<BlockState> TERRACOTTA_COLORS = new ArrayList<BlockState>() {
        {
            add(Blocks.WHITE_TERRACOTTA.defaultBlockState());
            add(Blocks.ORANGE_TERRACOTTA.defaultBlockState());
            add(Blocks.MAGENTA_TERRACOTTA.defaultBlockState());
            add(Blocks.LIGHT_BLUE_TERRACOTTA.defaultBlockState());
            add(Blocks.YELLOW_TERRACOTTA.defaultBlockState());
            add(Blocks.LIME_TERRACOTTA.defaultBlockState());
            add(Blocks.PINK_TERRACOTTA.defaultBlockState());
            add(Blocks.GRAY_TERRACOTTA.defaultBlockState());
            add(Blocks.LIGHT_GRAY_TERRACOTTA.defaultBlockState());
            add(Blocks.CYAN_TERRACOTTA.defaultBlockState());
            add(Blocks.PURPLE_TERRACOTTA.defaultBlockState());
            add(Blocks.BLUE_TERRACOTTA.defaultBlockState());
            add(Blocks.BROWN_TERRACOTTA.defaultBlockState());
            add(Blocks.GREEN_TERRACOTTA.defaultBlockState());
            add(Blocks.RED_TERRACOTTA.defaultBlockState());
            add(Blocks.BLACK_TERRACOTTA.defaultBlockState());
        }
    };
    public static final ArrayList<BlockState> WOODS = new ArrayList<BlockState>() {{
        add(Blocks.OAK_LOG.defaultBlockState());
        add(Blocks.SPRUCE_LOG.defaultBlockState());
        add(Blocks.BIRCH_LOG.defaultBlockState());
        add(Blocks.JUNGLE_LOG.defaultBlockState());
        add(Blocks.ACACIA_LOG.defaultBlockState());
        add(Blocks.DARK_OAK_LOG.defaultBlockState());
        add(Blocks.STRIPPED_OAK_LOG.defaultBlockState());
        add(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState());
        add(Blocks.STRIPPED_BIRCH_LOG.defaultBlockState());
        add(Blocks.STRIPPED_JUNGLE_LOG.defaultBlockState());
        add(Blocks.STRIPPED_ACACIA_LOG.defaultBlockState());
        add(Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState());
    }};

    public static final BlockState[] GLASS = {
            Blocks.WHITE_STAINED_GLASS.defaultBlockState(),
            Blocks.ORANGE_STAINED_GLASS.defaultBlockState(),
            Blocks.MAGENTA_STAINED_GLASS.defaultBlockState(),
            Blocks.LIGHT_BLUE_STAINED_GLASS.defaultBlockState(),
            Blocks.YELLOW_STAINED_GLASS.defaultBlockState(),
            Blocks.LIME_STAINED_GLASS.defaultBlockState(),
            Blocks.PINK_STAINED_GLASS.defaultBlockState(),
            Blocks.GRAY_STAINED_GLASS.defaultBlockState(),
            Blocks.LIGHT_GRAY_STAINED_GLASS.defaultBlockState(),
            Blocks.CYAN_STAINED_GLASS.defaultBlockState(),
            Blocks.PURPLE_STAINED_GLASS.defaultBlockState(),
            Blocks.BLUE_STAINED_GLASS.defaultBlockState(),
            Blocks.BROWN_STAINED_GLASS.defaultBlockState(),
            Blocks.GREEN_STAINED_GLASS.defaultBlockState(),
            Blocks.RED_STAINED_GLASS.defaultBlockState(),
            Blocks.BLACK_STAINED_GLASS.defaultBlockState(),
            Blocks.WHITE_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.ORANGE_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.MAGENTA_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.LIGHT_BLUE_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.YELLOW_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.LIME_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.PINK_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.GRAY_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.CYAN_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.PURPLE_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.BLUE_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.BROWN_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.GREEN_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.RED_STAINED_GLASS_PANE.defaultBlockState(),
            Blocks.BLACK_STAINED_GLASS_PANE.defaultBlockState()
    };

    public static final ArrayList<BlockState> FARMLANDS = new ArrayList<BlockState>() {
        {
            for(int i=0;i<5;i++)add(Blocks.FARMLAND.defaultBlockState());
            add(Blocks.WATER.defaultBlockState());
            add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState());
        }
    };

    public static final ArrayList<BlockState> FURNITURE = new ArrayList<BlockState>() {{
        add(Blocks.CHEST.defaultBlockState());
        add(Blocks.TRAPPED_CHEST.defaultBlockState());
        add(Blocks.CRAFTING_TABLE.defaultBlockState());
        add(Blocks.FURNACE.defaultBlockState());
        add(Blocks.BLAST_FURNACE.defaultBlockState());
        add(Blocks.SMOKER.defaultBlockState());
        add(Blocks.BARREL.defaultBlockState());
        add(Blocks.CARTOGRAPHY_TABLE.defaultBlockState());
        add(Blocks.FLETCHING_TABLE.defaultBlockState());
        add(Blocks.SMITHING_TABLE.defaultBlockState());
        add(Blocks.GRINDSTONE.defaultBlockState());
        add(Blocks.LOOM.defaultBlockState());
        add(Blocks.STONECUTTER.defaultBlockState());
        add(Blocks.BOOKSHELF.defaultBlockState());
        add(Blocks.CAULDRON.defaultBlockState());
        add(Blocks.LECTERN.defaultBlockState());
        add(Blocks.COMPOSTER.defaultBlockState());
        add(Blocks.JUKEBOX.defaultBlockState());
        add(Blocks.NOTE_BLOCK.defaultBlockState());
        add(Blocks.ENCHANTING_TABLE.defaultBlockState());
    }};

    public static final ArrayList<BlockState> LUCES = new ArrayList<BlockState>() {{
        add(Blocks.LANTERN.defaultBlockState());
        add(Blocks.SOUL_LANTERN.defaultBlockState());
        add(Blocks.SEA_LANTERN.defaultBlockState());
        
    }};
}
