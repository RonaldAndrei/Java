.text
main:
li $a0,7 #a
li $a1,8 #b
li $a2,6 #c 

	jal maior1
	j exit

maior:

	bgt $a0,$a1,else
	
	add $v0,$a1,$zero #d
	jr $ra
else:
	add $v0,$a0,$zero #d	
	jr $ra

maior1:

addi $sp,$sp,-12
	sw $a0,0($sp) #a
	sw $a1,4($sp) #b
	sw $a2,8($sp) #c
	sw $ra,12($sp)

	jal maior
	lw $ra,12($sp)
	
	bgt $a2,$v0,if
	
	add $v0,$v0,$zero
	jr $ra
if:
	add $v0,$a2,$zero
	jr $ra
exit:
