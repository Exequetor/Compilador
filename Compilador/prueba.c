#include<stdio.h>
int main(){
	int d,s;
	char c[20];
	printf("Nombre\n");
	scanf("%s", c);
	printf("%s", c);
	d=10+4*2;
	s=3;
	if(d>=0){
		printf("d es mayor a 0");
	}
	if(d<=0 && s==3){
		printf("S vale 3");
	}
	return 0;		
}
