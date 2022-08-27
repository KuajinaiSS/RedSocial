package com.company;
import java.awt.*;

import ucn.Registro;
import ucn.ArchivoEntrada;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        SistemaTeleBookImpl sys = new SistemaTeleBookImpl();


        try{
            sys.iniciarSistema();
        }catch (Exception e){

            System.out.println("_____________,--,\n" +
                    "| | | | | | |/ .-.\\   SANTA FELL\n" +
                    "|_|_|_|_|_|_/ /   `.      IN\n"+
                    " |_|__|__|_; |      \\" + " '["+e+"] ERROR'   \n"+
                    " |___|__|_/| |     .'`}\n" +
                    " |_|__|__/ | |   .'.'`\\\n" +
                    " |__|__|/  ; ;  / /    \\.-\"-.\n" +
                    " ||__|_;   \\ \\  ||    /`___. \\\n" +
                    " |_|___/ \\  /;.`,\\\\   {_'___.;{}\n" +
                    " |__|_/ `;`__|`-.;|  |C` T T`\\\n" +
                    " |___`L  \\__|__|__|  | `'-o-' }\n" +
                    " ||___|\\__)___|__||__|\\   ^  /`\\\n" +
                    " |__|__|__|__|__|_{___}'.__.`\\_.'}\n" +
                    " ||___|__|__|__|__;\\_)-'`\\   {_.-;\n" +
                    " |__|__|__|__|__|/` (`\\__/     '-'\n" );
        }
    }
}
