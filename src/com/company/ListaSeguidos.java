package com.company;

public class ListaSeguidos {

    private Usuario[] vecSeguidos;
    private int cantActualSeguidos=0;
    private int max;

    public ListaSeguidos(int max){
        this.max = max;
        vecSeguidos = new Usuario[max];
    }


    public Usuario buscarSeguidosNickname(String nickname){
        for (int i = 0; i < cantActualSeguidos; i++) {

            if(nickname.equalsIgnoreCase(vecSeguidos[i].getNickname())){
                return vecSeguidos[i];
            }
        }
        return null;
    }


    public boolean agregarSeguidos(Usuario usuario){

        if(cantActualSeguidos>=max){
            return false;
        }

        if(buscarSeguidosNickname(usuario.getNickname())==null){
            vecSeguidos[cantActualSeguidos] = usuario;
            cantActualSeguidos++;
            return true;

        }else{

            System.out.println("El nickname ya esta registrado");
            return false;
        }
    }


    public void desplegarSeguidos(){
        for (int i = 0; i < cantActualSeguidos; i++) {
            System.out.println(getVecSeguidos()[i].getNickname());
        }
    }

    public boolean eliminarSeguidos(String nickname){
        int j;
        for( j=0;j<cantActualSeguidos;j++){
            if(getVecSeguidos()[j].getNickname().equalsIgnoreCase(nickname)){
                break;
            }
        }
        if(j==cantActualSeguidos){//no lo encontro
            System.out.println("Usuario no encontrado");
            return false;

        }else{//lo encontro
            for (int k = j; k < cantActualSeguidos-1; k++) {
                getVecSeguidos()[k] = getVecSeguidos()[k+1];
            }
            cantActualSeguidos--;
            return true;
        }

    }

    public boolean buscarSiExisteONo(String nicknameABuscar){
        for( int j=0;j<cantActualSeguidos;j++){
            if(getVecSeguidos()[j].getNickname().equalsIgnoreCase(nicknameABuscar)){
                return true;
            }
        }
        return false;
    }


    public Usuario[] getVecSeguidos() {
        return vecSeguidos;
    }

    public void setVecSeguidos(Usuario[] vecSeguidos) {
        this.vecSeguidos = vecSeguidos;
    }

    public int getCantActualSeguidos() {
        return cantActualSeguidos;
    }

    public void setCantActualSeguidos(int cantActualSeguidos) {
        this.cantActualSeguidos = cantActualSeguidos;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
