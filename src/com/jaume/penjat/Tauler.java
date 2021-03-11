package com.jaume.penjat;

public class Tauler {

    private char[] paraulaSecreta;
    private String[] palabraEndevinada;
    private int intents;
    private int totalIntents;

    /**
     * 
     * @return Palabra a adivinar
     */
    public char[] getParaulaSecreta() {
        return paraulaSecreta;
    }

    /**
     * 
     * @return Intentos restantes
     */
    public int getIntents() {
        return intents;
    }

    /**
     * 
     * @return Intentos totales
     */
    public int getTotalIntents() {
        return totalIntents;
    }

    /**
     * 
     * @return Palabra que se habria adivinado
     */
    public String[] getPalabraEndevinada() {
        return palabraEndevinada;
    }

    /**
     * 
     * @param palabraEndevinada Determinar cual es la palabra que se habria adivinado
     */
    public void setPalabraEndevinada(String[] palabraEndevinada) {
        this.palabraEndevinada = palabraEndevinada;
    }

    /**
     * 
     * @param intents Determinar el numero de intentos restantes
     */
    public void setIntents(int intents) {
        this.intents = intents;
    }

    /**
     * 
     * @param paraulaSecreta Determinar la palabra a adivinar
     */
    public void setParaulaSecreta(char[] paraulaSecreta) {
        this.paraulaSecreta = paraulaSecreta;
    }

    /**
     * 
     * @param totalIntents Determinar el numero total de intentos
     */
    public void setTotalIntents(int totalIntents) {
        this.totalIntents = totalIntents;
    }

    /**
     * Genera un nuevo tablero
     */
    public Tauler() {
        paraulaSecreta = new char[0];
        palabraEndevinada = new String[0];
        intents = 0;
        totalIntents = 0;
    }

    /**
     * Iniciar una partida con la palabra a adivinar y el numero de intentos
     * 
     * @param p palabra a adivinar
     * @param i numero de intentos
     */
    public void inicialitzarPartida(String p, int i){
        paraulaSecreta = new char[p.length()];
        for (int n = 0; n < p.length(); n++) {
            paraulaSecreta[n] = p.charAt(n) ;
        }
        palabraEndevinada = new String[p.length()];
        for (int n = 0; n < p.length(); n++) {
            if(p.charAt(n) == new Character(' ')){
                palabraEndevinada[n] = " " ;
            }
        }
        intents = i;
        totalIntents = i;

    }

    /**
     * 
     * @return String con la palabra que se esta adivinando
     */
    public String imprimir() {
        String result = "";
        for (int i = 0; i < palabraEndevinada.length; i++) {
            if (palabraEndevinada[i] == null) {
                result = result+"_";
            }else if(palabraEndevinada[i] == " "){
                result = result+" ";
            }else {
                result = result+palabraEndevinada[i];
            }
        }
        return result;
    }

    /**
     * Verifica si la letra introducida esta en la palabra a adivinar
     * 
     * @param letra Letra a verificar
     * @return 
     */
    public String verificar(String letra) {
        if(letra.length() > 1){
            return "Lletra incorrecte";
        }else{
            boolean exist = false;
            for (int i = 0; i < paraulaSecreta.length; i++) {
                if (paraulaSecreta[i] == letra.charAt(0)) {
                    exist = true;
                    palabraEndevinada[i] = letra;
                }
            }
            if(!exist){
                restarIntent();
            }
        }
        return"";
    }

    /**
     * 
     * @return String con el numero de vidas restantes
     */
    public String imprimirVides(){
        String message = String.format("Et queden %s vides de %s",intents, totalIntents);
        if(intents == 1) message = String.format("Et queda %s vida de %s",intents, totalIntents);
        return message;
    }

    /**
     * Restar un intento
     */
    public void restarIntent() {
        this.intents--;
    }

    /**
     * 
     * @return Booleano
     *  True - La partida ha sido ganada
     *  False - La partida aun no ha finalizado o se ha perdido
     */
    public boolean hasGuanyat() {
        boolean valor = true;
        for (int i = 0; i < palabraEndevinada.length; i++) {
            if (palabraEndevinada[i] == null) {
                return false;
            }
        }
        return valor;
    }

}
