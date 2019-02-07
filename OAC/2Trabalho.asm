.data

vet:.word

.text

la $t0,vet #carrega vet em $t0
li $t1,1 #i
li $t2,5 #k 

loop:
	beq $t3,$t2,end #enquanto vet[0] != k
	
	addi $t1,$t1,1 #i=i+1
	sw $t1,0($t0) #salva i em vet[0]
	lw $t3,0($t0) #carrega vet[0] em #$t3
	
j loop

end:
	syscall
