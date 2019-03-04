package model.util;

import java.util.Comparator;

import model.vo.VOMovingViolations;

public class comparePorAddressId implements Comparator<VOMovingViolations>{

	@Override
	public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
		String second=arg1.getAddressId();
		String first=arg0.getAddressId();
		
		int resultado=0;
		
		if(first.compareToIgnoreCase(second)>0)
			resultado= 1;
		else if(first.compareToIgnoreCase(second)<0)
			resultado= -1;
		return resultado;
	}

}
