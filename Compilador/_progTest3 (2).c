#include <stdio.h>
int main () {
	int c,f;
	do {
		printf ("Ingrese un numero (-1 para salir)\n");
		scanf("%d", &c);
		printf("El numero es %d\n", c);
	} while (c != -1);
	f=5;
	while (f != 10) {
		if (f == 0) {
		  printf("Es un numero par \n");
		}else{
		  if (f!=0) {
			printf ("Es un numero impar\n");
		  }
		}
		f++;
	}
	return 0;
}