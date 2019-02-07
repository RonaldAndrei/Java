.text

li $t1,10
li $t2,10
li $t0,268500992

bne $t1,$t2,if
	sw $t2,8($t0) #else 
	j exit #jump
	
if:
	sw $t2,12($t0)
	
exit:
	syscall

