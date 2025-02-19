package edu.masanz.da.ta.dto;

/**
 * Representa una puja realizada sobre un item, lo mismo que la clase Puja pero con más información.
 * Servirá para tener toda la información que queremos mostra de una puja (y el item asociado) en pantalla.
 * Es un DTO (Data Transfer Object) que se usa para transferir datos entre subsistemas de un programa.
 * Java no tiene herencia multiple, por lo que no se puede heredar de Item y Puja a la vez.
 * Cuando veamos la herencia veremos qué quiere decir esto.
 * Hay muchos atributos repetidos de las clases Puja e Item, no se podría mejorar esto? Lo veremos.
 */
public class PujaItem {
    private long idItem;
    private String nombreItem;
    private int precioInicio;
    private String urlImagen;
    private String nombrePropietario;
    private String nombreComprador;
    private int precioPujado;
    private String instanteTiempo;

    public PujaItem(long idItem, String nombreItem, int precioInicio, String urlImagen, String nombrePropietario, String nombreComprador, int precioPujado, String instanteTiempo) {
        this.idItem = idItem;
        this.nombreItem = nombreItem;
        this.precioInicio = precioInicio;
        this.urlImagen = urlImagen;
        this.nombrePropietario = nombrePropietario;
        this.nombreComprador = nombreComprador;
        this.precioPujado = precioPujado;
        this.instanteTiempo = instanteTiempo;
    }

    public PujaItem(Puja puja) {
        this(puja.getIdItem(), "", 0, "", "", puja.getNombreUsuario(), puja.getPrecioPujado(), puja.getInstanteTiempo());
    }

    public PujaItem() {
        this(0, "", 0, "", "", "", 0, "");
    }

    // region Getters and Setters
    public void setBid(Puja puja) {
        this.setIdItem(puja.getIdItem());
        this.setPrecioPujado(puja.getPrecioPujado());
        this.setInstanteTiempo(puja.getInstanteTiempo());
        this.setNombreComprador(puja.getNombreUsuario());
    }

    public void setItem(Item item) {
        this.setIdItem(item.getId());
        this.setNombreItem(item.getNombre());
        this.setPrecioInicio(item.getPrecioInicio());
        this.setUrlImagen(item.getUrlImagen());
        this.setNombrePropietario(item.getNombreUsuario());
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
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

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
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

    public void setInstanteTiempo(String instanteTiempo) {
        this.instanteTiempo = instanteTiempo;
    }

    // endregion

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %4d | %-25s | %4d € | %4d € | %23s |\n",
                idItem, nombreItem, precioInicio, precioPujado, instanteTiempo));
        sb.append(String.format("| %-76s |\n", urlImagen));
        sb.append(String.format("|%s|", "-".repeat(78)));
        return sb.toString();
    }

    public String getTransferInfo() {
        return String.format("| %4d | %-25s | %4d € | %-6s | %4d € | %-6s | %10s |",
                idItem, nombreItem, precioInicio, nombrePropietario, precioPujado, nombreComprador, instanteTiempo.substring(0,10));
    }

}