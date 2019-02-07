org 00h

	mov p2,#00h

	ljmp inicio

inicio: 
	mov p2,#11000000b        ;0

	lcall tempo

	mov p2,#11111001b        ;1

	lcall tempo

	mov p2,#10100100b        ;2

	lcall tempo

	mov p2,#10110000b        ;3

	lcall tempo

	mov p2,#10011001b        ;4

	lcall tempo

	mov p2,#10010010b        ;5

	lcall tempo

	mov p2,#10000010b        ;6

	lcall tempo

	mov p2,#11111000b        ; 7

	lcall tempo

	mov p2,#10000000b        ; 8

	lcall tempo

	mov p2,#10011000b        ; 9

	lcall tempo
ljmp inicio

	tempo:	mov r1,#20h
	volta:	mov tmod,#01h

	mov th0,#3Ch

	mov tl0,#0AFh

	setb tr0

	clr tf0

	jnb tf0, $

	clr tr0

	clr tf0

	djnz r1,volta

	ret

end