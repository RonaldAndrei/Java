.data

.text
addi $t0, $zero, 1 #A
addi $t1, $zero, 2 #B
addi $t2, $zero, 1 #C
addi $t3, $zero, 3 #D
addi $t4, $zero, 2 #E
	           #F = resultado
add $t6, $t0, $t1 #soma A+B
add $t7, $t2, $t3 #soma C+D
add $t8, $t7, $t4 #soma (C+D)+E

sub $t5, $t6, $t8 #subtração de (A+B)-((C+D)+E)

sw $t5, 268500992($zero)