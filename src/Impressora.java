import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Impressora {

    private static final String ASSINATURA_QRCODE_CANCELAMENTO =
            "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0" +
            "E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQ" +
            "sE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+Z" +
            "fO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

    private int tipo;
    private String modelo;
    private String conexao;
    private int parametro;
    private boolean conexaoAberta;

    public boolean isConexaoAberta() {
        return conexaoAberta;
    }

    public void configurar(Scanner sc) {
        System.out.println("1 = USB, 2 = Serial, 3 = Rede (TCP/IP), 4 = Bluetooth");
        tipo = Main.lerInteiro(sc, "Tipo de conexão: ");
        System.out.print("Modelo (i8 ou i9): ");
        modelo = sc.nextLine();
        System.out.print("Conexão (USB, COM2, IP...): ");
        conexao = sc.nextLine();
        parametro = Main.lerInteiro(sc, "Parâmetro (0 para USB): ");
        System.out.println("Configuração salva.");
    }

    public void abrirConexao() {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código: " + retorno);
            }
        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void fecharConexao() {
        int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
        if (retorno == 0) {
            conexaoAberta = false;
            System.out.println("Conexão fechada com sucesso.");
        } else {
            System.out.println("Erro ao fechar conexão. Código: " + retorno);
        }
    }

    public void imprimirTexto(String dados, int posicao, int estilo, int tamanho) {
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, posicao, estilo, tamanho);
        if (retorno >= 0) {
            System.out.println("Texto enviado com sucesso.");
        } else {
            System.out.println("Erro ao imprimir texto. Código: " + retorno);
        }
        cortarPapel();
    }

    public void imprimirQRCode(String dados, int tamanho, int nivelCorrecao) {
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, tamanho, nivelCorrecao);
        if (retorno == 0) {
            System.out.println("QR Code impresso com sucesso.");
        } else {
            System.out.println("Erro ao imprimir QR Code. Código: " + retorno);
        }
        cortarPapel();
    }

    public void imprimirCodigoBarras(int tipo, String dados, int altura, int largura, int hri) {
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(tipo, dados, altura, largura, hri);
        if (retorno == 0) {
            System.out.println("Código de barras impresso com sucesso.");
        } else {
            System.out.println("Erro ao imprimir código de barras. Código: " + retorno);
        }
        cortarPapel();
    }

    public void imprimirXMLSAT(String caminhoArquivo) {
        try {
            String conteudo = Files.readString(Path.of(caminhoArquivo));
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(conteudo, 0);
            if (retorno == 0) {
                System.out.println("Cupom fiscal (SAT) impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir XML SAT. Código: " + retorno);
            }
            cortarPapel();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        }
    }

    public void imprimirXMLCancelamentoSAT(String caminhoArquivo) {
        try {
            String conteudo = Files.readString(Path.of(caminhoArquivo));
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                    conteudo, ASSINATURA_QRCODE_CANCELAMENTO, 0);
            if (retorno == 0) {
                System.out.println("Cupom de cancelamento impresso com sucesso.");
            } else {
                System.out.println("Erro ao imprimir XML de cancelamento. Código: " + retorno);
            }
            cortarPapel();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        }
    }

    public void abrirGaveta() {
        int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin(0, 10, 10);
        if (retorno == 0) {
            System.out.println("Gaveta aberta com sucesso.");
        } else {
            System.out.println("Erro ao abrir a gaveta. Código: " + retorno);
        }
    }

    public void sinalSonoro(int quantidade) {
        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 50, 5);
        if (retorno == 0) {
            System.out.println("Bip!");
        } else {
            System.out.println("Erro no sinal sonoro. Código: " + retorno);
        }
    }

    public void avancarPapel(int linhas) {
        int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);
        if (retorno == 0) {
            System.out.println("Papel avançado com sucesso.");
        } else {
            System.out.println("Erro ao avançar papel. Código: " + retorno);
        }
    }

    public void statusImpressora(int param) {
        int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(param);
        if (retorno < 0) {
            System.out.println("Erro ao consultar status. Código: " + retorno);
            return;
        }
        System.out.println("Status retornado: " + retorno);
        if (param == 3) {
            switch (retorno) {
                case 5 -> System.out.println("Papel: tem papel.");
                case 6 -> System.out.println("Papel: acabando.");
                case 7 -> System.out.println("Papel: sem papel.");
                default -> System.out.println("Papel: status desconhecido.");
            }
        } else if (param == 4) {
            switch (retorno) {
                case 0 -> System.out.println("Gaveta: fechada.");
                case 1 -> System.out.println("Gaveta: aberta.");
                default -> System.out.println("Gaveta: status desconhecido.");
            }
        }
    }

    private void cortarPapel() {
        int retorno = ImpressoraDLL.INSTANCE.Corte(0);
        if (retorno == 0) {
            System.out.println("Papel cortado com sucesso.");
        } else {
            System.out.println("Erro ao cortar papel. Código: " + retorno);
        }
    }

}
