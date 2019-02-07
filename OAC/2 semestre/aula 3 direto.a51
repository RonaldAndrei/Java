        org 0000h
ini:
        mov 20h, #0bbh ;movendo imediato pelo # para 20h
        mov 23h, 20h   ;movendo o conteudo de 20h para 23h
        mov a, p2      ;a recebe 255 padrao de p2
        add a, 23h     ;adiciona mais valor de 23h em a
end