package amsi.dei.estg.ipleiria.RightPrice.listener;

//Listener para o sistema de login
public interface LoginListener {

    void onValidateLogin(String token, String username,int TipoValidate);
    void onErroLogin(String erro);

    void onErroRegistar(String erro);
    void onValidateRegistar(String texto);
}
