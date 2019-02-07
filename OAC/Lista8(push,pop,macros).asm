.data
inserido:.asciiz"elemento inserido"
pula_linha:.asciiz"\n"
removido:.asciiz"elemento removido"

.macro push(%D) #começa função %d = valor à inserir
	
	addi $t0,$t0,-4 #endereço
	add $t1,$zero,%D #soma o valor inserido em $t1
	sw $t1,0($t0) #envia o valor inserido para o endereço
	addi $t2,$t2,1 #contador
	
	li $v0,4 #escolhe syscall para imprimir string
	la $a0,inserido #imprime a mensagem <-
	syscall #imprime na tela
	
	la $a0,pula_linha #imprime a mensagem <-
	syscall #imprime na tela

.end_macro #fim da função 

.macro pop() #começa função de remoção
	
	add $t1,$zero,0 #soma em $t1 o valor 0
	sw $t1,0($t0) #envia o valor 0 acima para o endereço
	addi $t0,$t0,4 #pula para a proxima posição do endereço
	sub $t2,$t2,1 #contador
	
	la $a0,removido #imprime a mensagem <-	
	syscall #imprime na tela
	la $a0,pula_linha #imprime a mensagem <-
	syscall #imprime na tela
	
.end_macro #fim da função

.macro imp() #começa função de imprime
	move $t3,$t0 #copia o endereço para $t3
	move $t4,$t2 #copia o contador para $t4
inicio:	
	bgt $t4,$zero,for #se $t4 for maior que zero entra no for
	j exit #sai do loop
	
for:
	
	li $v0,1 #imprime um inteiro
	lw $a0,0($t3) #envia para $a0 o valor de $t3 que
	syscall #imprime na tela
	
	addi $t3,$t3,4 #pula para o prox endereço
	addi $t4,$t4,-1 #decrementa o contador
	
	li $v0,4 #escolhe syscall para imprimir string
	la $a0,pula_linha #imprime a mensagem <-
	syscall	#imprime na tela
j inicio #volta para o inicio do for

exit: #sai se não entrar no for

.end_macro #termina função imprime

.text
main:
	move $t0,$sp #copia para $t0 o endereço de $pc
	
	push(10)
	push(20)
	pop()
	push(50)
	imp()
	push(20)
	push(30)
	imp()
	pop()
	imp()


