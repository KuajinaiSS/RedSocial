package com.company;

import java.util.Arrays;

public class ListaUsuario {
    private Usuario[] vecUsuario;
    private int cantActual;
    private int max;

    public ListaUsuario(int max){
        this.max = max;
        cantActual=0;
        vecUsuario = new Usuario[max];
    }

    public Usuario buscarUsuarioNickname(String nickname){
        for (int i = 0; i < cantActual; i++) {

            if(nickname.equalsIgnoreCase(vecUsuario[i].getNickname())){
                return vecUsuario[i];
            }
        }
        return null;
    }


    public boolean agregarUsuario(Usuario usuario){
        if(cantActual>=max){
            return false;
        }

        if(buscarUsuarioNickname(usuario.getNickname())==null){
            vecUsuario[cantActual] = usuario;
            cantActual++;
            return true;

        }else{
            System.out.println("El nickname ya esta registrado");
            return false;
        }
    }


    public boolean buscarnickname(String nicknameBuscar){
        for (int i = 0; i < cantActual; i++) {
            if(getVecUsuario()[i].getNickname().equalsIgnoreCase(nicknameBuscar)){
                return true;
            }
        }
        return false;
    }


    public boolean buscarCorreo(String correoBuscar){
        for (int i = 0; i < cantActual; i++) {
            if(getVecUsuario()[i].getEmail().equalsIgnoreCase(correoBuscar)){
                return true;
            }
        }
        return false;
    }



    public Usuario[] getVecUsuario() {
        return vecUsuario;
    }

    public int getCantActual() {
        return cantActual;
    }

    @Override
    public String toString() {
        return "ListaUsuario{" +
                "vecUsuario=" + Arrays.toString(vecUsuario) +
                ", cantActual=" + cantActual +
                ", max=" + max +
                '}';
    }

    public void desplegarUsuarios(){
        for (int i = 0; i < getCantActual(); i++) {

            System.out.println(getVecUsuario()[i].getNickname()+" = "+getVecUsuario()[i].getPassword());
        }

    }




}

