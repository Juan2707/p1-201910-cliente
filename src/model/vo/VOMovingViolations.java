package model.vo;

/**
 * Representation of a Trip object
 */
public class VOMovingViolations implements Comparable<VOMovingViolations>{

	private int id;
	private String location;
	private String ticketIssueDate;
	private int totalPaid;
	private String accidentIndicator;
	private String violationDescription;
	private double fineAMT;
	private String violationCode;

	public VOMovingViolations(int pId, String pLocation, String pTicketIssueDate, int pTotalPaid, String pAccidentIndicator,String pViolationDescription, double pFineAMT, String pViolationCode){
		id=pId;
		location=pLocation;
		ticketIssueDate=pTicketIssueDate;
		totalPaid=pTotalPaid;
		accidentIndicator=pAccidentIndicator;
		violationDescription=pViolationDescription;
		fineAMT=pFineAMT;
		violationCode=pViolationCode;
	}
	/**
	 * @return id - Identificador único de la infracción
	 */
	public int objectId() {
		// TODO Auto-generated method stub
		return id;
	}	
	public String getViolationCode(){
		return violationCode;
		
	}

	/**
	 * @return location - Dirección en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * @return date - Fecha cuando se puso la infracción .
	 */
	public String getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketIssueDate;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente pagó el que recibió la infracción en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return totalPaid;
	}

	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public String  getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidentIndicator;
	}

	/**
	 * @return description - Descripción textual de la infracción.
	 */
	public String  getViolationDescription() {
		// TODO Auto-generated method stub
		return violationDescription;
	}
	
	
	@Override
	//Aun no tiene en cuenta el caso de que sean iguales.
	public int compareTo(VOMovingViolations o) {
		// TODO Auto-generated method stub
		int retorno=0;
		if(id>o.objectId()){
			retorno=1;
		}
		else if(id<o.objectId()){
			retorno=-1;
		}
		return retorno;
	}
	public int getStreetSegId() {
		
		return 0;
	}
	public int getAddressId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public double getFineAMT(){
		return fineAMT;
		
	}
	
}
