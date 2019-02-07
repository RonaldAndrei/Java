.text
main:
li $a0,1 #g
li $a1,2 #h
li $a2,3 #i
li $a3,4 #j

	jal funcao
	j exit
	
funcao:
	addi $sp,$sp,-28
	
	sw $t0,0($sp) #f
	sw $t1,4($sp) #g+h
	sw $t2,8($sp) #i+j
	sw $a0,12($sp) #g
	sw $a1,16($sp) #h
	sw $a2,20($sp) #i
	sw $a3,24($sp) #j
	
	sw $ra,28($sp) 
	
	
	add $t1,$a0,$a1 #g+h
	add $t2,$a2,$a3 #i+j
	
	sub $t0,$t1,$t2 #f
	
	add $v0,$t0,$zero
	
	lw $t0,0($sp) #f
	lw $t1,4($sp) #g+h
	lw $t2,8($sp) #i+j
	lw $a0,12($sp) #g
	lw $a1,16($sp) #h
	lw $a2,20($sp) #i
	lw $a3,24($sp) #j
	lw $ra,28($sp) 
	
	jr $ra
exit:
