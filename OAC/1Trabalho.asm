.text

li $t0,10 #tamanho vetor
li $t1,0 #inicio do vetor
li $t2,0 #registrador posição
li $t3,1 #v[i]

loop:
	beq $t1,$t0,end #enquanto v < 10
	
	sw $t3,268500992,($t2) #envia 1 para $t2
	addi $t2,$t2,4 #posição + 4 bytes
	addi $t1,$t1,1 #contador
	
j loop

end:
	syscall
