package com.company;

import java.io.IOException;

public interface SistemaTeleBook {

    public boolean iniciarSesion(String nickname, String password);

    public void crearPost(String nicknameLogeado);

    public void chequearPerfil(String nicknameLogeado);

    public boolean seguirUsuario(String nickname);

    public boolean dejarDeSeguirUsuario(String nickname);

    public boolean cambiarContrasena(String password);

    public void verPostSeguidores(String nicknameLogeado);

    public void verPostSeguidos(String nicknameLogeado);

    public void seguidoresPromedio();

    public void usuarioMenor();

    public void usuarioMayor();

    public void cantSuperPosts();

    public boolean registrarUsuario ();

    public void cerrarAplicacion() throws IOException;
}
