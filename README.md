# 🖨️ Sistema de Controle de Impressora Elgin
---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido para se comunicar com uma impressora térmica **Elgin i8/i9** usando a biblioteca `E1_Impressora01.dll`. O sistema tem login com no máximo 3 tentativas e um menu com 12 operações de impressão e controle da impressora. Faz parte da disciplina de **Programação Orientada a Objetos**.

---

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **IntelliJ IDEA**
- **JNA 5.15.0** (comunicação com a DLL)
- **DLL E1_Impressora01** (SDK da Elgin)
- **Impressora Elgin i8 ou i9**

---

## 📁 Estrutura do Projeto

```
/src
   Main.java        → Login, menu e leitura dos dados digitados
   Usuario.java      → Guarda login e senha e confere se estão certos
   Impressora.java   → Guarda os dados da conexão e tem um método para cada comando da DLL
   ImpressoraDLL.java → Interface JNA fornecida (não alterada)
/Bibliotecas E1 Impressora → DLLs da Elgin (x64 e x86)
/libs Java                → jna-5.15.0.jar
XMLSAT.xml                → Cupom de venda de exemplo
CANC_SAT.xml               → Cupom de cancelamento de exemplo
```

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto
1. Acesse o repositório no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia o arquivo.

### 2. Abrir no IntelliJ IDEA
1. **File → Open** e escolha a pasta extraída.
2. Em **File → Project Structure → Libraries**, confirme que o `jna-5.15.0.jar` está adicionado.
3. Em **Run → Edit Configurations**, confirme que o *Working directory* é a pasta principal do projeto (onde está a pasta `Bibliotecas E1 Impressora`).
4. Conecte e ligue a impressora.
5. Abra `Main.java` e clique em ▶️ **Run**.

### Abrir no Visual Studio Code
1. Abra a pasta principal do projeto, que contém `src`, `libs Java` e `Bibliotecas E1 Impressora`.
2. Instale as extensões **Extension Pack for Java** e **Debugger for Java**.
3. O projeto configura automaticamente o JNA pelo arquivo `.vscode/settings.json`. Se o VS Code já estava aberto, execute **Java: Clean Java Language Server Workspace** e reabra a pasta.
4. Abra `src/Main.java` e clique em **Run**.

---

## 🔐 Login

- Usuário: `gei`
- Senha: `9080`
- Máximo de 3 tentativas

---

## 📋 Funcionalidades do Menu

| Opção | O que faz |
|---|---|
| 1 | Configurar conexão (tipo, modelo, porta, parâmetro) |
| 2 | Abrir conexão com a impressora |
| 3 | Fechar conexão com a impressora |
| 4 | Imprimir texto |
| 5 | Imprimir QR Code |
| 6 | Imprimir código de barras |
| 7 | Imprimir XML SAT (cupom de venda) |
| 8 | Imprimir XML de cancelamento SAT |
| 9 | Abrir gaveta |
| 10 | Sinal sonoro (bip) |
| 11 | Avançar papel |
| 12 | Consultar status da impressora (gaveta, tampa, papel ou geral) |
| 0 | Sair |

Se a conexão não estiver aberta, as opções de 3 a 12 mostram o aviso "Conexão fechada. Abra a conexão antes." e voltam ao menu, sem pedir nenhum dado.

---

## 🧪 Testes Realizados

- Login com senha errada 3 vezes seguidas (bloqueio do programa).
- Login correto na primeira e na segunda tentativa.
- Tentativa de imprimir com a conexão fechada (mensagem de aviso).
- Entrada de letras em um campo numérico (mensagem de erro sem travar o programa).
- Abertura e fechamento de conexão, impressão de texto, QR Code, código de barras, bip, abertura de gaveta e impressão dos XMLs de exemplo, testados diretamente na impressora.

---

## 📚 Documentação Adicional

- Manual da impressora Elgin
- Documentação do SDK E1_Impressora: https://elgindevelopercommunity.github.io/group___m1.html

---

## 👥 Autores
*(em ordem alfabética)*

- 
- 
- 
