.data
inserido:.asciiz"elemento inserido"
pula_linha:.asciiz"\n"
removido:.asciiz"elemento removido"

.macro insere(%D) #começa função %d = valor à inserir
	
	addi $t0,$t0,-4 #endereço
	add $t1,$zero,%D #soma o valor inserido em $t1
	sw $t1,0($t0) #envia o valor inserido para o endereço
	addi $t2,$t2,1 #contador

.end_macro #fim da função 

.macro remove()
	
	addi $t6,$sp,-4
	beqz $t2,vazia 
	bgtz $t2,for
for:	
	lw $t6,-4($t6)
	sw $t6,-4($sp)
	addi $t6,$t6,-4 #posição
	addi $t2,$t2,-1 #contador
	bgtz $t2,for
	addi $t0,$t0,4
vazia:
	j exit
exit:

.end_macro

.text
main:
	move $t0,$sp #copia para $t0 o endereço de $pc
	
	insere(10)
	insere(20)
	remove()
	insere(50)
	insere(1)
	insere(2)
	insere(3)
	remove()
	remove()