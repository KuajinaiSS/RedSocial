package com.company;

public class Admin extends Usuario {

    public Admin(String nombre, String apellido, String email, String nickname, String password, boolean esAdmin, int seguidores, int seguidos) {
        super(nombre, apellido, email, nickname, password, esAdmin, seguidores, seguidos);
        this.esAdmin = true;
    }
}
