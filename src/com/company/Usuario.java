package com.company;

abstract class Usuario {
    String nombre;
    String apellido;
    String email;
    String nickname;
    String password;
    boolean esAdmin;
    int seguidores;
    int seguidos;

    private ListaSeguidores listaSeguidores;
    private ListaSeguidos listaSeguidos;
    private ListaPost listaPost;


    public Usuario(String nombre, String apellido, String email, String nickname, String password, boolean esAdmin, int seguidores, int seguidos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.esAdmin = esAdmin;
        this.seguidores = seguidores;
        this.seguidos = seguidos;

        listaSeguidores = new ListaSeguidores(10);//lista de gente que sigue AL usuario
        listaSeguidos = new ListaSeguidos(10);//lista de gente que sigue EL usuario
        listaPost = new ListaPost(50);//lista de post
    }



    public ListaSeguidores getListaSeguidores() {
        return listaSeguidores;
    }

    public void setListaSeguidores(ListaSeguidores listaSeguidores) {
        this.listaSeguidores = listaSeguidores;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public int getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(int seguidos) {
        this.seguidos = seguidos;
    }

    public ListaSeguidos getListaSeguidos() {
        return listaSeguidos;
    }

    public void setListaSeguidos(ListaSeguidos listaSeguidos) {
        this.listaSeguidos = listaSeguidos;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public ListaPost getListaPost() {
        return listaPost;
    }

    public void setListaPost(ListaPost listaPost) {
        this.listaPost = listaPost;
    }
}



