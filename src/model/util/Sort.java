package model.util;

import java.util.Arrays;
import java.util.Collections;

public class Sort {
	
	/**
	 * Ordenar datos aplicando el algoritmo ShellSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarShellSort( Comparable[ ] datos ) {
		
		 int N = datos.length;
		 int h = 1;
		 while (h < N/3) h = 3*h + 1;
		 while (h >= 1)
		 { 
		 for (int i = h; i < N; i++)
		 { 
		 for (int j = i; j >= h && less(datos[j], datos[j-h]); j -= h)
		 exchange(datos, j, j-h);
		 }
		 h = h/3;
		 }
		 
		// TODO implementar el algoritmo ShellSort
	}
	
	/**
	 * Ordenar datos aplicando el algoritmo MergeSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarMergeSort( Comparable[ ] datos ) {
		Comparable[] aux = new Comparable[datos.length]; // Allocate space just once.
		 sortParaMegreSort(datos, aux, 0, datos.length - 1);
		// TODO implementar el algoritmo MergeSort
	}

	private static void  mergeParaMergeSort(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
	{ 
		 int i = lo, j = mid+1;
		 for (int k = lo; k <= hi; k++) 
		 aux[k] = a[k];
		 for (int k = lo; k <= hi; k++) 
		 if (i > mid) a[k] = aux[j++];
		 else if (j > hi ) a[k] = aux[i++];
		 else if (less(aux[j], aux[i])) a[k] = aux[j++];
		 else a[k] = aux[i++];
		}
	
	private static void  sortParaMegreSort(Comparable[] a, Comparable[] aux, int lo, int hi)
	 { 
		
		 if (hi <= lo) return;
		 int mid = lo + (hi - lo)/2;
		 sortParaMegreSort(a, aux, lo, mid);
		 sortParaMegreSort(a, aux, mid+1, hi);
		 mergeParaMergeSort(a, aux, lo, mid, hi); 
		 } 
	/**
	 * Ordenar datos aplicando el algoritmo QuickSort
	 * @param datos - conjunto de datos a ordenar (inicio) y conjunto de datos ordenados (final)
	 */
	public static void ordenarQuickSort( Comparable[ ] datos ) {

		Collections.shuffle(Arrays.asList(datos));
		 sortParaQuick(datos, 0, datos.length - 1);
		// TODO implementar el algoritmo QuickSort
	}
	private static void sortParaQuick(Comparable[] a, int lo, int hi)
	 {
	 if (hi <= lo) return;
	 int j = partitionParaQuick(a, lo, hi); 
	 sortParaQuick(a, lo, j-1); 
	 sortParaQuick(a, j+1, hi); 
	 } 
	
	
	private static int partitionParaQuick(Comparable[] a, int lo, int hi)
	{ 
	 int i = lo, j = hi+1; 
	 Comparable v = a[lo]; 
	 while (true)
	 { 
	 while (less(a[++i], v)) if (i == hi) break;
	 while (less(v, a[--j])) if (j == lo) break;
	 if (i >= j) break;
	 exchange(a, i, j);
	 }
	 exchange(a, lo, j);
	 return j; 
	}
	
	/**
	 * Comparar 2 objetos usando la comparacion "natural" de su clase
	 * @param v primer objeto de comparacion
	 * @param w segundo objeto de comparacion
	 * @return true si v es menor que w usando el metodo compareTo. false en caso contrario.
	 */
	private static boolean less(Comparable v, Comparable w)
	{
		boolean xd;
		if(v.compareTo(w)==-1)
		{
			xd=true;
			// TODO implementar
		}
		else{
			xd=false;
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

}
