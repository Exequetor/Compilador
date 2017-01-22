#include <stdio.h>

int main () {
	int c, **a[3][5][65], f;
	do {
		printf ("Ingrese un numero (-1 para salir)\n");
		scanf("%d", &c);
		printf("El numero es %d\n", c);
	} while (c != -1);
	for (c = 0, f = 5 ; (c < 8 || f) && c ; c++, f--) {
		if (c == 20) {
			c = 0;
		}
		if (c != f) {
			printf ("c != f");
		} else {
			printf("c == f");
		}
	}
	return 0;
}

