package com.company;

public class ListaSeguidores {

    private Usuario[] vecSeguidores;
    private int cantActualSeguidores=0;
    private int max;
    private String seguidorMasReciente = "no tienes seguidores";


    public ListaSeguidores(int max){
        this.max = max;
        vecSeguidores = new Usuario[max];
    }


    public Usuario buscarSeguidoresNickname(String nickname){

        for (int i = 0; i < cantActualSeguidores; i++) {

            if(nickname.equalsIgnoreCase(vecSeguidores[i].getNickname())){
                return vecSeguidores[i];
            }
        }
        return null;
    }


    public boolean agregarSeguidores(Usuario usuario){

        if(cantActualSeguidores>=max){
            return false;
        }

        if(buscarSeguidoresNickname(usuario.getNickname())==null){
            vecSeguidores[cantActualSeguidores] = usuario;
            seguidorMasReciente = usuario.getNickname();
            cantActualSeguidores++;


            return true;

        }else{
            System.out.println("El nickname ya esta registrado");
            return false;
        }
    }

    public boolean eliminarSeguidores(String nickname){
        int j;

        for( j=0;j<cantActualSeguidores;j++){
            System.out.println("1");
            if(getVecSeguidores()[j].getNickname().equalsIgnoreCase(nickname)){
                System.out.println("2");
                break;
            }
        }


        if(j==cantActualSeguidores){//no lo encontro
            System.out.println("Usuario no encontrado0");
            return false;
        }else{//lo encontro
            for (int k = j; k < cantActualSeguidores-1; k++) {
                getVecSeguidores()[k] = getVecSeguidores()[k+1];
            }
            cantActualSeguidores--;
            return true;
        }
    }



    public void desplegarSeguidores(){
        for (int i = 0; i < cantActualSeguidores; i++) {
            System.out.println(getVecSeguidores()[i].getNickname());

        }
    }

    public String getSeguidorMasReciente() {

        return seguidorMasReciente;
    }

    public void setSeguidorMasReciente(String seguidorMasReciente) {
        this.seguidorMasReciente = seguidorMasReciente;
    }

    public Usuario[] getVecSeguidores() {
        return vecSeguidores;
    }

    public void setVecSeguidores(Usuario[] vecSeguidores) {
        this.vecSeguidores = vecSeguidores;
    }

    public int getCantActualSeguidores() {
        return cantActualSeguidores;
    }

    public void setCantActualSeguidores(int cantActualSeguidores) {
        this.cantActualSeguidores = cantActualSeguidores;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
