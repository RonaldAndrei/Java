
.macro scan

li $v0,5 #le um inteiro e salva em $v0
syscall

.end_macro

.macro print

li $v0,1 #imprime na tela o valor de $a0
li $a0,3378
syscall

.end_macro

.text

scan
add $t0,$zero,$v0 #salva o valor que foi lido em $t0

print