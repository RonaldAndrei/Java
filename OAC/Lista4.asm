.text

main:
	li $a0,8 #parametro 1
	li $a1,12 #parametro 2
	
	li $t1,2 #divisor
	
	jal media
	j exit
	
media:
	addi $sp,$sp,-20
	
	sw $t0,0($sp) #div
	sw $t2,4($sp) #soma
	sw $a0,8($sp) #parametro 1
	sw $a1,12($sp) #parametro 2
	sw $ra,16($sp) 
	
	jal soma
	lw $ra,16($sp)
	div $t0,$t3,$t1
	jr $ra
	
soma:
	add $t3,$a0,$a1
	jr $ra
exit:
	syscall
	