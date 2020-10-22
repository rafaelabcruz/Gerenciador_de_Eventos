package br.senai.sc.gerenciadordeeventos.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {

    private int id;
    private String nomeEvento;
    private String dataEvento;
    private String localEvento;

    public Evento(int id, String nomeEvento, String dataEvento, String localEvento){
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.localEvento = localEvento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String data_evento) {
        this.dataEvento = dataEvento;
    }


    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeEvento + " - " + dataEvento + " - " + localEvento;
    }
}
