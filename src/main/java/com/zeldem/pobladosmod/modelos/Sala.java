package com.zeldem.pobladosmod.modelos;

import java.util.ArrayList;
import java.util.HashMap;

import com.zeldem.pobladosmod.modelos.arquitectonicos.Arquitectonico;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Muro;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Piso;
import com.zeldem.pobladosmod.modelos.arquitectonicos.Techo;
import com.zeldem.pobladosmod.pobladosHelper.Ayudante;
import com.zeldem.pobladosmod.pobladosHelper.BodegaConstruccion;
import com.zeldem.pobladosmod.pobladosHelper.Bresenham;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Sala {
  // Atributos de la clase
  private BlockPos entrada;
  private Direction facing;
  private ArrayList<BlockPos> puntos_base;
  private ArrayList<Muro> muros;
  private Piso mobiliario;
  private Piso luces;
  private Piso piso;
  private BlockState puerta;
  private int altura;
  private HashMap<String, ArrayList<BlockState>> materiales;
  private Techo techo;

  // Constructor de la clase
  // El array de puntos base debe tener 4 elementos, uno para cada esquina de la
  // sala
  // el mapa de materiales debe tener 5 elementos, uno para los muros,
  // uno para el techo, otro para el piso, las columnas y las ventanas
  public Sala(ArrayList<BlockPos> puntos_base, int altura,
      BlockPos entrada, Direction facing,
      HashMap<String, ArrayList<BlockState>> materiales) {
    // inicializamos variables
    this.entrada = entrada;
    this.facing = facing;
    this.puntos_base = puntos_base;
    this.altura = altura;
    this.materiales = materiales;
    // inicializamos muros
    initMuros();
    initPiso();
    initTecho();
    initMobiliario();
    initLuces();
  }

  public Sala(BlockPos entrada, Direction facing, int horizontal, int frente, int altura,
      HashMap<String, ArrayList<BlockState>> materiales) {
     // inicializamos variables
     this.entrada = entrada;
     this.facing = facing;
     this.altura = altura;
     this.materiales = materiales;
    puntos_base = new ArrayList<BlockPos>();
    puntos_base.add(entrada.relative(facing.getClockWise(), horizontal / 2));
    puntos_base.add(entrada.relative(facing, frente).relative(facing.getClockWise(), horizontal / 2));
    puntos_base.add(entrada.relative(facing, frente).relative(facing.getCounterClockWise(), horizontal / 2-1));
    puntos_base.add(entrada.relative(facing.getCounterClockWise(), horizontal / 2-1));
    
    initMuros();
    initPiso();
    initTecho();
    initMobiliario();
    initLuces();
  }

  private void initPiso() {
    this.piso = new Piso(puntos_base, materiales.get("piso"));
  }

  private void initTecho() {
    //puntos_techo are found using puntos_base.offset up altura units
    ArrayList<BlockPos> puntos_techo = new ArrayList<BlockPos>();
    for (BlockPos punto : puntos_base) {
      puntos_techo.add(punto.offset(0, altura, 0));
    }
    this.techo = new Techo(puntos_techo, materiales.get("techo"));
  }

  private void initMuros() {
    this.muros = new ArrayList<Muro>();
    muros.add(new Muro(
      puntos_base.get(0),
      puntos_base.get(puntos_base.size() - 1).above(altura),
      materiales.get("muros")));

    for (int i = 0; i < puntos_base.size() - 1; i++) {
     Muro mtemp = new Muro(puntos_base.get(i), puntos_base.get(i + 1).offset(0, altura, 0), 
     materiales.get("muros"));
     mtemp.damage(0.1);
      muros.add(mtemp);
    }
    
  }

  private void initMobiliario() {
    //puntos_techo are found using puntos_base.offset up altura units
    ArrayList<BlockPos> puntos_techo = new ArrayList<BlockPos>();
    for (int i=0 ;i < puntos_base.size() ; i++) {
      puntos_techo.add(puntos_base.get(i).above());
    }
    mobiliario = new Piso(puntos_techo, materiales.get("mobiliario"));
    mobiliario.damage(0.95);  
    puerta = Ayudante.selecciona(1, BodegaConstruccion.DOORS).get(0);
  }
  private void initLuces() {
    //puntos_techo are found using puntos_base.offset up altura units
    ArrayList<BlockPos> puntos_techo = new ArrayList<BlockPos>();
    for (int i=0 ;i < puntos_base.size() ; i++) {
      puntos_techo.add(puntos_base.get(i).above(altura-1));
    }
    luces = new Piso(puntos_techo, materiales.get("luces"));
    luces.damage(0.95);
  }

  public BlockState puerta(){
    return puerta;
  }

  public ArrayList<Muro> getMuros() {
    return muros;
  }

  public Piso getPiso() {
    return piso;
  }

  public Techo getTecho() {
    return techo;
  }

  public BlockPos getE() {
    return entrada;
  }

  public Direction getF() {
    return facing;
  }

  public Arquitectonico getMobiliario() {
    return mobiliario;
  }

  public Arquitectonico getLuces() {
    return luces;
  }

}


