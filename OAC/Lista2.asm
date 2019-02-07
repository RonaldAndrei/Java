.text


#addi $t1,$zero,10
#addi $t2,$zero,10

li $t1,10
li $t2,10

bne $t1,$t2,if
	sub $t2,$t2,$t1

if:
	add $t1,$t1,$t2
 
exit:
	syscall
   