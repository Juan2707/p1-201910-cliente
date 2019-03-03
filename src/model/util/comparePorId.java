package model.util;

import java.util.Comparator;

import model.vo.VOMovingViolations;

public class comparePorId implements Comparator<VOMovingViolations>{

	public int compare(VOMovingViolations arg0, VOMovingViolations arg1) {
		int second=arg1.objectId();
		int first=arg0.objectId();
		
		int resultado=0;
		
		if(first>second)
			resultado= 1;
		else if(first<second)
			resultado= -1;
		return resultado;
	}
}
