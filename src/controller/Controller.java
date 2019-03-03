package controller;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.util.Sort;
import model.util.comparePorId;
import model.util.compareePorFecha;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;
	
	Comparable<VOMovingViolations>[] ordenador;

	private Stack<VOMovingViolations > almacenamiento;
	
	List <String[]> info;
	public Controller() {
		view = new MovingViolationsManagerView();
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		
		while(!fin)
		{
			view.printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 0:
					view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
					int numeroCuatrimestre = sc.nextInt();
					controller.loadMovingViolations(numeroCuatrimestre);
					break;
				
				case 1:
					boolean isUnique = controller.verifyObjectIDIsUnique();
					view.printMessage("El objectId es único: " + isUnique);
					break;
					
				case 2:
					
					view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaInicialReq2A = convertirFecha_Hora_LDT(sc.next());
					
					view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaFinalReq2A = convertirFecha_Hora_LDT(sc.next());
					
					IQueue<VOMovingViolations> resultados2 = controller.getMovingViolationsInRange(fechaInicialReq2A, fechaFinalReq2A);
					
					view.printMovingViolationsReq2(resultados2);
					
					break;
					
				case 3:
					
					view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode3 = sc.next();
					
					double [] promedios3 = controller.avgFineAmountByViolationCode(violationCode3);
					
					view.printMessage("FINEAMT promedio sin accidente: " + promedios3[0] + ", con accidente:" + promedios3[1]);
					break;
						
					
				case 4:
					
					view.printMessage("Ingrese el ADDRESS_ID");
					String addressId4 = sc.next();

					view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017)");
					LocalDate fechaInicialReq4A = convertirFecha(sc.next());
					
					view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017)");
					LocalDate fechaFinalReq4A = convertirFecha(sc.next());
					
					IStack<VOMovingViolations> resultados4 = controller.getMovingViolationsAtAddressInRange(addressId4, fechaInicialReq4A, fechaFinalReq4A);
					
					view.printMovingViolationsReq4(resultados4);
					
					break;
					
				case 5:
					view.printMessage("Ingrese el limite inferior de FINEAMT  (Ej: 50)");
					double limiteInf5 = sc.nextDouble();
					
					view.printMessage("Ingrese el limite superior de FINEAMT  (Ej: 50)");
					double limiteSup5 = sc.nextDouble();
					
					IQueue<VOViolationCode> violationCodes = controller.violationCodesByFineAmt(limiteInf5, limiteSup5);
					view.printViolationCodesReq5(violationCodes);
					break;
				
				case 6:
					
					view.printMessage("Ingrese el limite inferior de TOTALPAID (Ej: 200)");
					double limiteInf6 = sc.nextDouble();
					
					view.printMessage("Ingrese el limite superior de TOTALPAID (Ej: 200)");
					double limiteSup6 = sc.nextDouble();
					
					view.printMessage("Ordenar Ascendentmente: (Ej: true)");
					boolean ascendente6 = sc.nextBoolean();				
					
					IStack<VOMovingViolations> resultados6 = controller.getMovingViolationsByTotalPaid(limiteInf6, limiteSup6, ascendente6);
					view.printMovingViolationReq6(resultados6);
					break;
					
				case 7:
					
					view.printMessage("Ingrese la hora inicial (Ej: 23)");
					int horaInicial7 = sc.nextInt();
					
					view.printMessage("Ingrese la hora final (Ej: 23)");
					int horaFinal7 = sc.nextInt();
					
					IQueue<VOMovingViolations> resultados7 = controller.getMovingViolationsByHour(horaInicial7, horaFinal7);
					view.printMovingViolationsReq7(resultados7);
					break;
					
				case 8:
					
					view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode8 = sc.next();
					
					double [] resultado8 = controller.avgAndStdDevFineAmtOfMovingViolation(violationCode8);
					
					view.printMessage("FINEAMT promedio: " + resultado8[0] + ", desviación estandar:" + resultado8[1]);
					break;
					
				case 9:
					
					view.printMessage("Ingrese la hora inicial (Ej: 23)");
					int horaInicial9 = sc.nextInt();
					
					view.printMessage("Ingrese la hora final (Ej: 23)");
					int horaFinal9 = sc.nextInt();
					
					int resultado9 = controller.countMovingViolationsInHourRange(horaInicial9, horaFinal9);
					
					view.printMessage("Número de infracciones: " + resultado9);
					break;
				
				case 10:
					view.printMovingViolationsByHourReq10();
					break;
					
				case 11:
					view.printMessage("Ingrese la fecha inicial (Ej : 28/03/2017)");
					LocalDate fechaInicial11 = convertirFecha(sc.next());
					
					view.printMessage("Ingrese la fecha final (Ej : 28/03/2017)");
					LocalDate fechaFinal11 = convertirFecha(sc.next());
					
					double resultados11 = controller.totalDebt(fechaInicial11, fechaFinal11);
					view.printMessage("Deuda total "+ resultados11);
					break;
				
				case 12:	
					view.printTotalDebtbyMonthReq12();
					
					break;
					
				case 13:	
					fin=true;
					sc.close();
					break;
			}
		}

	}
	
	
	public void loadMovingViolations(int numeroCuatrimestre) {
		almacenamiento= new Stack<VOMovingViolations>();
		if(numeroCuatrimestre==1){
			cargarEnStack("./data/Moving_Violations_Issued_In_January_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_February_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_March_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_April_2018.csv");
		}
		else if(numeroCuatrimestre==2){
			cargarEnStack("./data/Moving_Violations_Issued_In_May_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_June_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_July_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_August_2018.csv");
		}
		else if(numeroCuatrimestre==3){
			cargarEnStack("./data/Moving_Violations_Issued_In_September_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_October_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_November_2018.csv");
			cargarEnStack("./data/Moving_Violations_Issued_In_December_2018.csv");
		}
		
	}
	public void cargarEnStack(String ruta){
		try{
			FileReader n1 = new FileReader(ruta);
			CSVReader n2 = new CSVReaderBuilder(n1).withSkipLines(1).build();
			
			 info = n2.readAll();
			Comparable<VOMovingViolations>[] ordenador = new Comparable[info.size()];
			
			
			for(int i=0;i<info.size();i++){
				ordenador[i]=(new VOMovingViolations(Integer.parseInt(info.get(i)[0]), info.get(i)[2], info.get(i)[13],Integer.parseInt(info.get(i)[9]), info.get(i)[12], info.get(i)[15],(double) Integer.parseInt(info.get(i)[8]), info.get(i)[14]));
			}
			Sort.ordenarMergeSort(ordenador);
			for(int i=0;i<info.size();i++){
				almacenamiento.push((VOMovingViolations)ordenador[i]);
			}
			
			
			
			n1.close();
			n2.close();
			
		}
		
		catch(Exception e){
			view.printMessage(e.getMessage());
		}
	}
	
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}

	public boolean verifyObjectIDIsUnique() {
		boolean b=true;
		ordenarMergeSort(ordenador,new comparePorId());
		int ubicacion= 0;
		for (int i=1; i<ordenador.length;i++){
			if(ordenador[i].compareTo((VOMovingViolations)ordenador[i-1])==0){
				ubicacion=i;
				b=false;
			}
			
		}
		return b;
	}

	public IQueue<VOMovingViolations> getMovingViolationsInRange(LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		ordenarMergeSort(ordenador,new compareePorFecha());
		Queue<VOMovingViolations> guardar= new Queue<>();
		boolean comenzarGuardado=false;
		boolean terminar=false;
		for(int i=0;i<ordenador.length&&terminar==false;i++){
			VOMovingViolations actual = (VOMovingViolations)ordenador[i];
			if(fechaInicial.compareTo(LocalDateTime.parse(actual.getTicketIssueDate()))<=0){
				comenzarGuardado=true;
			}
			if(fechaFinal.compareTo(LocalDateTime.parse(actual.getTicketIssueDate()))<=0){
				terminar=true;
			}
			else{
				if(comenzarGuardado==true){
				guardar.enqueue(actual);
			}
			}
			return guardar;
		}
		
		return null;
	}
	private static boolean less(Comparable v, Comparable w, Comparator<VOMovingViolations> comparador)
	{
		boolean xd=false;
		if(v!=null&&w!=null){
			if(comparador.compare((VOMovingViolations)v,(VOMovingViolations) w)<0)
		{
			xd=true;
			// TODO implementar
		}
		else{
			xd=false;
		}
		}
		
		return xd;
	}
	
	/**
	 * Intercambiar los datos de las posicion i y j
	 * @param datos contenedor de datos
	 * @param i posicion del 1er elemento a intercambiar
	 * @param j posicion del 2o elemento a intercambiar
	 */
	private static void exchange( Comparable[] datos, int i, int j)
	{
		Comparable dato	= datos[i];
		datos[i]=datos[j];
		datos[j]=dato;
		// TODO implementar
	}
	
	public static void ordenarMergeSort( Comparable[ ] datos , Comparator<VOMovingViolations> comparador) {
		Comparable[] aux = new Comparable[datos.length]; // Allocate space just once.
		 sortParaMegreSort(datos, aux, 0, datos.length - 1, comparador);
		// TODO implementar el algoritmo MergeSort
	}

	private static void  mergeParaMergeSort(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, Comparator<VOMovingViolations> comparador)
	{ 
		 int i = lo, j = mid+1;
		 for (int k = lo; k <= hi; k++) 
		 aux[k] = a[k];
		 for (int k = lo; k <= hi; k++) 
		 if (i > mid) a[k] = aux[j++];
		 else if (j > hi ) a[k] = aux[i++];
		 else if (less(aux[j], aux[i], comparador)) a[k] = aux[j++];
		 else a[k] = aux[i++];
		}
	
	private static void  sortParaMegreSort(Comparable[] a, Comparable[] aux, int lo, int hi, Comparator<VOMovingViolations> comparador)
	 { 
		
		 if (hi <= lo) return;
		 int mid = lo + (hi - lo)/2;
		 sortParaMegreSort(a, aux, lo, mid,comparador);
		 sortParaMegreSort(a, aux, mid+1, hi, comparador);
		 mergeParaMergeSort(a, aux, lo, mid, hi, comparador); 
		 } 

	public double[] avgFineAmountByViolationCode(String violationCode3) {
		double cantidadNoAccidentados=0;
		double cantidadFineAMTNoAccidentados=0;
		double cantidadAccidentados=0;
		double cantidadFineAMTAccidentados=0;
		for(int i=0;i<ordenador.length;i++){
			VOMovingViolations actual= (VOMovingViolations) ordenador[i];
			if(actual.getViolationCode().equals(violationCode3)){
				if(actual.getAccidentIndicator().equals("No")){
					cantidadNoAccidentados++;
					cantidadFineAMTNoAccidentados+=actual.getFineAMT();
				}
				else{
					cantidadAccidentados++;
					cantidadFineAMTAccidentados+=actual.getFineAMT();
				}
			}
		}
		double promedio1= cantidadNoAccidentados/cantidadFineAMTNoAccidentados;
		double promedio2= cantidadAccidentados/cantidadFineAMTAccidentados;
		return new double [] {promedio1, promedio2};
	}

	public IStack<VOMovingViolations> getMovingViolationsAtAddressInRange(String addressId,
			LocalDate fechaInicial, LocalDate fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOViolationCode> violationCodesByFineAmt(double limiteInf5, double limiteSup5) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStack<VOMovingViolations> getMovingViolationsByTotalPaid(double limiteInf6, double limiteSup6,
			boolean ascendente6) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOMovingViolations> getMovingViolationsByHour(int horaInicial7, int horaFinal7) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] avgAndStdDevFineAmtOfMovingViolation(String violationCode8) {
		// TODO Auto-generated method stub
		return new double [] {0.0 , 0.0};
	}

	public int countMovingViolationsInHourRange(int horaInicial9, int horaFinal9) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double totalDebt(LocalDate fechaInicial11, LocalDate fechaFinal11) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	
	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss"));
	}
}
