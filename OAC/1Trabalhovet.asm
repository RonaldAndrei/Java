.data #define variavel
vet:.word #declara vet: 4 bytes

.text

li $t0,10
li $t1,0
li $t2,0
li $t3,1
la $t4, vet #a posição inicial esta em $t4
loop:
	beq $t1,$t0,end
	
	sw $t3,0($t4)
	addi $t4,$t4,4
	addi $t1,$t1,1 #contador
	
j loop

end:
	syscall