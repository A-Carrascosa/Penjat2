/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaume.penjat;

import com.jaume.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class PuntuacioTest {

    private Puntuacio puntuacio;

    @BeforeEach
    void initTest() {
        this.puntuacio = new Puntuacio();
    }

    /**
     * 
     * @param difficulty 
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 7})
    void getParaulaSecretaDificultat_dificultat(int difficulty) {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        int tries = 0;
        switch (difficulty) {
            case 1:
                tries = 5;
                break;
            case 2:
                tries = 4;
                break;
            case 3:
                tries = 3;
                break;
            case 7:
                tries = -1;
                break;
            default:
                break;
        }
        if (tries < 0) {
            assertEquals("err", this.puntuacio.getParaulaSecretaDificultat(difficulty));
        } else {
            assertEquals(tries, this.puntuacio.getIntents());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getParaulaSecretaDificultat_paraules(int difficulty) {
        String randomWord = this.puntuacio.getParaulaSecretaDificultat(difficulty);
        switch (difficulty) {
            case 1:
                assertTrue(Arrays.asList(this.puntuacio.getParaules1()).contains(randomWord));
                break;
            case 2:
                assertTrue(Arrays.asList(this.puntuacio.getParaules2()).contains(randomWord));
                break;
            case 3:
                assertTrue(Arrays.asList(this.puntuacio.getParaules3()).contains(randomWord));
                break;
            default:
                break;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getPuntuacioParaulaCorrecte(int difficulty) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        puntuacio.setParaula("cicleidomastoide");
        String[] secretWord = {"c", "i", "c", "l", "e", "i", "d", "o", "m", "a", "s", "t", "o", "i", "d", "e"};
        float score = 0.0f;
        switch (difficulty) {
            case 1:
                score = 200.0f;
                break;
            case 2:
                score = 400.0f;
                break;
            case 3:
                score = 600.0f;
                break;
            default:
                break;
        }
        assertEquals(score, puntuacio.calcularPuntuacio(secretWord, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getPuntuacioParaulaCorrecteTemps(int difficulty) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        puntuacio.setParaula("cicleidomastoide");
        String[] secretWord = {"c", "i", "c", "l", "e", "i", "d", "o", "m", "a", "s", "t", "o", "i", "d", "e"};
        float score = 0.0f;
        switch (difficulty) {
            case 1:
                score = 185.0f;
                break;
            case 2:
                score = 385.0f;
                break;
            case 3:
                score = 585.0f;
                break;
            default:
                break;
        }
        TimeUnit.SECONDS.sleep(15);
        assertEquals(score, puntuacio.calcularPuntuacio(secretWord, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getPuntuacioParaulaIncorrecte(int difficulty) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        puntuacio.setParaula("periquito");
        String[] secretWord = {"p", null, "r", null, "q", null, null, null, "o"};
        float score = 0.0f;
        switch (difficulty) {
            case 1:
                score = 40.0f;
                break;
            case 2:
                score = 80.0f;
                break;
            case 3:
                score = 120.0f;
                break;
            default:
                break;
        }
        assertEquals(score, puntuacio.calcularPuntuacio(secretWord, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getPuntuacioParaulaBuida(int difficulty) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        puntuacio.setParaula("porc");
        String[] secretWord = {null, null, null, null};
        float score = 0.0f;
        assertEquals(score, puntuacio.calcularPuntuacio(secretWord, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void getPuntuacioParaulaCorrecteBonus(int difficulty) throws InterruptedException {
        puntuacio.getParaulaSecretaDificultat(difficulty);
        puntuacio.setParaula("escopinyes");
        String[] secretWord = {"e", "s", "c", "o", "p", "i", "n", "y", "e", "s"};
        float score = 0.0f;
        switch (difficulty) {
            case 1:
                score = 210.0f;
                break;
            case 2:
                score = 410.0f;
                break;
            case 3:
                score = 610.0f;
                break;
            default:
                break;
        }
        assertEquals(score, puntuacio.calcularPuntuacio(secretWord, 1));
    }

}
