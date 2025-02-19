package edu.masanz.da.ta.dto;

import java.util.List;

public class Puja {
    private long idItem;
    private String nombreUsuario;
    private int precioPujado;
    private String instanteTiempo;

    // region Constructors
    public Puja(long idItem, String nombreUsuario, int precio, String instanteTiempo) {
        this.idItem = idItem;
        this.nombreUsuario = nombreUsuario;
        this.precioPujado = precio;
        this.instanteTiempo = instanteTiempo;
    }


    public Puja(long idItem, String nombreUsuario, int precio) {
        this.idItem = idItem;
        this.nombreUsuario = nombreUsuario;
        this.precioPujado = precio;
        this.instanteTiempo = new java.sql.Timestamp(System.currentTimeMillis()).toString();
    }

    public Puja() {
        this(0, "", 0);
    }
    // endregion

    // region Getters and Setters
    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getPrecioPujado() {
        return precioPujado;
    }

    public void setPrecioPujado(int precioPujado) {
        this.precioPujado = precioPujado;
    }

    public String getInstanteTiempo() {
        return instanteTiempo;
    }
    // endregion

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %23s | %-7s | %4d € |\n",instanteTiempo, nombreUsuario, precioPujado));
        sb.append(String.format("|%s|", "-".repeat(44)));
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%s %-8s %4d", instanteTiempo, nombreUsuario, precioPujado);
    }

}
