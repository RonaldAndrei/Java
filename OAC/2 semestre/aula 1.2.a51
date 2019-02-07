;acionar os ports

        org 0000h

inicio:
        mov p0, #00h    ;nivel baixo
        mov p0, #0ffh    ;nivel alto

        jmp     inicio
end     