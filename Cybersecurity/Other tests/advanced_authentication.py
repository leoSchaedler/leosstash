matrizCredenciais = [["joao", "123456", 0], ["pedro", "abcdef", 0], ["Antonella", "123abc", 0]]
permissoes = [["joao", "Foto.png", "leitura", 0], ["joao", "Musica.mp3", "reproduzir", 0], ["pedro", "Vingadores.avi", "deletar", 0]]

loop = True
while( loop ):

    usuario = input("Digite seu usuario: ")
    senha = input("Digite sua senha: ")
    arquivo = input("Qual arquivo deseja acessar? ")
    acao = input("O que deseja fazer com tal arquivo? ")

    for credencial in matrizCredenciais:
        usuarioCorrente = credencial[0]
        senhaCorrente = credencial[1]
        tentativas = credencial[2]


        if usuario == usuarioCorrente and senha == senhaCorrente:
            if( tentativas < 5 ):
                credencial[2] = 0
                loop = False
            else:
                print("Usuario esta bloqueado, contate o administrador!")
        elif usuario == usuarioCorrente:
            credencial[2] = tentativas + 1
            if (credencial[2] >= 5):
                print("Usuario foi bloqueado!")


    for permissao in permissoes:
        usuarioCorrente = permissao[0]
        arquivoCorrente = permissao[1]
        acaoCorrente = permissao[2]
        tentativas = permissao[3]

        if arquivo == arquivoCorrente and acao == acaoCorrente:
            if(tentativas < 5):
                permissao[3] = 0
                loop = False
            else:
                print("Usario bloqueado, contate o administrador!")

        elif usuarioCorrente == usuario:
            permissao[3] = tentativas + 1
            if (permissao[3] >= 5):
                print("Usuario foi bloqueado!")


    if loop == True:
        print("Usuario ou senha invalido!")
    else:
        print("Usuario autenticado com sucesso!")

