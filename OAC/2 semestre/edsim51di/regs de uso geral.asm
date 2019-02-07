;registradores de uso geral
	org 0000h
ini:
	mov r1,#0ffh 
	mov a,r1
	inc r5
	jmp $
end