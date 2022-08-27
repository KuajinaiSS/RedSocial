package com.company;

public class UsuarioNormal extends Usuario{

    public UsuarioNormal(String nombre, String apellido, String email, String nickname, String password, boolean esAdmin, int seguidores, int seguidos) {
        super(nombre, apellido, email, nickname, password, esAdmin, seguidores, seguidos);
        this.esAdmin = false;
    }
}
