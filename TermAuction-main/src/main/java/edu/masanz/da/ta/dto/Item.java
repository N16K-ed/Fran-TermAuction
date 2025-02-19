package edu.masanz.da.ta.dto;

import java.util.ArrayList;
import java.util.List;

import static edu.masanz.da.ta.conf.Ctes.EST_PENDIENTE;

public class Item {
    private long id;
    private String nombre;
    private String desc;
    private int precioInicio;
    private String urlImagen;
    private String nombreUsuario;
    private int estado;
    private boolean historico;

    // region Constructors
    public Item(long id, String nombre, String desc, int precioInicio, String urlImagen, String nombreUsuario, int estado, boolean historico) {
        this.id = id;
        this.nombre = nombre;
        this.desc = desc;
        this.precioInicio = precioInicio;
        this.urlImagen = urlImagen;
        this.nombreUsuario = nombreUsuario;
        this.estado = estado;
        this.historico = historico;
    }

    public Item(String nombre, String desc, int precioInicio, String urlImagen, String nombreUsuario) {
        this(0, nombre, desc, precioInicio, urlImagen, nombreUsuario, EST_PENDIENTE, false);
    }

    public Item() {
        this(0, "", "", 0, "", "", EST_PENDIENTE, false);
    }
    // endregion

    // region Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrecioInicio() {
        return precioInicio;
    }

    public void setPrecioInicio(int precioInicio) {
        this.precioInicio = precioInicio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isHistorico() {
        return historico;
    }

    public void setHistorico(boolean historico) {
        this.historico = historico;
    }
    // endregion

    public String getInfo() {
        return getInfo(true);
    }

    public String getInfo(boolean conDesc) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %4d | %-26s | %4d € |\n",id, nombre, precioInicio));
        if (conDesc) {
            List<String> lineasDesc = chopString(desc, 42);
            for (String linea : lineasDesc) {
                sb.append(String.format("| %-42s |\n", linea));
            }
        }
        sb.append(String.format("| %-42s |\n", urlImagen));
        sb.append(String.format("|%s|", "-".repeat(44)));
        return sb.toString();
    }

    private List<String> chopString(String desc, int n) {
        List<String> lineas = new ArrayList<>();
        int len = desc.length();
        int i = 0;
        while (i < len) {
            int j = i + n;
            if (j > len) {
                j = len;
            }
            lineas.add(desc.substring(i, j));
            i = j;
        }
        return lineas;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %d Euros", id, nombre, precioInicio);
    }

}
