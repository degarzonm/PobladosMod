package com.degares.poblados.util.structures.house;

import com.degares.poblados.Helpers;
import com.degares.poblados.PobladosMod;
import com.degares.poblados.util.structures.house.sala.Sala;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;


public class Casa {
	
	private Helpers h=new Helpers(1);
	private UseOnContext mundo;
	BlockPos entrada;
	private String nivel;//define la cantidad de espacios que la casa tendra
	private Direction orientacion;
	
	
	public Casa(UseOnContext mundo , String level) {
		this.mundo=mundo;
		this.entrada=mundo.getClickedPos();
		this.nivel=level;
		this.orientacion=mundo.getHorizontalDirection();
	}
	


	public void generarEnMundo() {
		Sala principal = new Sala(entrada	,orientacion
												, 5
												,5
												,6
												,7);

		PobladosMod.LOGGER.debug("principal:"+principal);

		Sala subSalaIzq= new Sala(principal.getALeftEntrance(0.2f),orientacion.getCounterClockWise()
																	,4
																	,h.rand(1, 4)
																	,principal.altura-1
																	,principal.frente/2);
		PobladosMod.LOGGER.debug("izq:"+subSalaIzq);
		
		Sala subSalaDer= new Sala(principal.getARightEntrance(0.2f),orientacion.getClockWise()
				,h.rand(1, 4)
				,h.rand(1, 4)
				,principal.altura-1
				,principal.frente/2);
		PobladosMod.LOGGER.debug("der:"+subSalaDer);
		
		Sala portico= new Sala(entrada,orientacion.getClockWise().getClockWise()
				,h.rand(1, 4)
				,h.rand(1, 4)
				,principal.altura-1
				,4,false,false,false,false);


		principal.obrero(mundo);


		}



	}

