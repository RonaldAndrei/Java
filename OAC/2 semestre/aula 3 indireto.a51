        org 0000h
ini:
        mov 23h, #0bbh
        mov r1, #23h
        mov a, @r1
        ajmp $
end