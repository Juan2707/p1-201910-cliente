package model.util;

import java.util.Comparator;

import model.vo.VOMovingViolations;

public class compareParaAddressId implements Comparator<VOMovingViolations>{

	@Override
	public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
		String second=arg1.getStreetSegId();
		String first=arg0.getStreetSegId();
		
		int resultado=0;
		
		if(first.compareToIgnoreCase(second)>0)
			resultado= 1;
		else if(first.compareToIgnoreCase(second)<0)
			resultado= -1;
		else{
			 compareePorFecha punto= new compareePorFecha();
			resultado=punto.compare(arg0, arg1);
		}
		return resultado;
	}

}
