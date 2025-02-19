package edu.masanz.da.ta.dto;

public class Usuario {

    private String nombre;
    private String sal;
    private String hashPwSal;
    private String rol;

    // region Constructors
    public Usuario(String nombre, String sal, String hashPwSal, String rol) {
        this.nombre = nombre;
        this.sal = sal;
        this.hashPwSal = hashPwSal;
        this.rol = rol;
    }

    public Usuario() {
        this("", "", "", "");
    }
    // endregion

    // region Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getHashPwSal() {
        return hashPwSal;
    }

    public void setHashPwSal(String hashPwSal) {
        this.hashPwSal = hashPwSal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // endregion

    @Override
    public String toString() {
        return nombre;
    }

}
