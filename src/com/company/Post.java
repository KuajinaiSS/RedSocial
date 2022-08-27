package com.company;
import java.time.LocalDateTime;

public class Post {
    String autorNickname;
    String contenido;

    public Post(String autorNickname, String contenido) {
        this.autorNickname = autorNickname;
        this.contenido = contenido;
    }

    public String getAutorNickname() {
        return autorNickname;
    }

    public void setAutorNickname(String autorNickname) {
        this.autorNickname = autorNickname;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}