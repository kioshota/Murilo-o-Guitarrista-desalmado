public class Usuario {

    private final String login;
    private final String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public boolean validar(String loginDigitado, String senhaDigitada) {
        return login.equals(loginDigitado) && senha.equals(senhaDigitada);
    }
}
