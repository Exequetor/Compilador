package debug;

import java.util.Vector;

public class TestVector {
	
	public TestVector () {
		Vector <Integer> vecInt = new Vector <Integer> ();//Declaración de vector 1 de enteros.
		Vector <Integer> vecInt2 = new Vector <Integer> ();//Declaración de vector 2 de enteros.
		vecInt.add(5);//Se agrega un 5 al vector. Esto es que en la posición 0 tendremos un 5.
		vecInt2 = vecInt;//A vecInt2 suponemos que le hacemos una copia de vecStr, pero en realidad
						 //estamos sincronizando los vectores y dandole los valores de vecInt
						 //a vecInt2.
		vecInt2.setElementAt(8, 0);//Cambiamos el 5 que obtuvimos de la supuesta copia de vecInt
								   //por un 8.
		System.out.print(vecInt);//Imprimimos vecInt que se supone que tiene un 5, pero en la consola
								 //obtenemos un 8.
	}
}
