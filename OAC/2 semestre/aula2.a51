        ORG 0000h
ini:
        MOV a,#01h
        MOV p2,#00h
        MOV p2,#0FFh
        MOV a,#02h
        JMP ini
        END