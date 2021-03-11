package com.jaume.penjat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Util {

    /**
     * 
     * @param enunciat String importada para imprimir la pregunta
     * @return Valor introducido
     */
    public static String makeQuestion(String enunciat){
        String valor = " ";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader (isr);
            System.out.print(enunciat+"  : ");
            valor = br.readLine();
        } catch (IOException ex) {
            System.out.println("Error");
        }
        return valor;
        }

    /**
     * 
     * @param cadena String para analizar
     * @return Booleano que determina si la String 'cadena' es o no numerica
     */
    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

}
