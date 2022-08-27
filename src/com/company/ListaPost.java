package com.company;

public class ListaPost {
    private Post[] vecPost;
    private int cantActual;
    private int max;

    public ListaPost(int max){
        this.max = max;
        cantActual = 0;
        vecPost = new Post[max];
    }

    public Post buscarPostAutorNickname(String autorNickname){
        for (int i = 0; i < cantActual; i++) {

            if(autorNickname.equalsIgnoreCase(vecPost[i].getAutorNickname())){
                return vecPost[i];
            }
        }
        return null;
    }

    public boolean agregarPost(Post post){
        if(cantActual>=max){
            return false;
        }
            vecPost[cantActual] = post;
            cantActual++;
            System.out.println("agregado");
            return true;

    }


    public void desplegarPostMasResiente(){
        int i;
        if(getCantActual()==0){
            System.out.println("No hay posts");
        }else{
            for (i = 0; i < getCantActual(); i++) {
            }

            System.out.print(getVecPost()[i-1].getContenido());
        }

    }



    public Post[] getVecPost() {
        return vecPost;
    }

    public void setVecPost(Post[] vecPost) {
        this.vecPost = vecPost;
    }

    public int getCantActual() {
        return cantActual;
    }

    public void setCantActual(int cantActual) {
        this.cantActual = cantActual;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
