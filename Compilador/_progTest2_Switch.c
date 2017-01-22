#include <stdio.h>

int main () {
	int a;
	a = 1;
	switch (a) {
		case 1:
			printf ("case 1");
			break;
		case 2:
			printf ("case 2");
			break;
		default:
			printf ("default case");
	}
}