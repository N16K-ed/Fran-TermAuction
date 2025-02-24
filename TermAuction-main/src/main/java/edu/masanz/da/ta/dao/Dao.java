package edu.masanz.da.ta.dao;

import edu.masanz.da.ta.conf.Ini;
import edu.masanz.da.ta.dto.*;
import edu.masanz.da.ta.utils.Security;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.*;

import static edu.masanz.da.ta.conf.Ctes.*;
import static edu.masanz.da.ta.conf.Ini.*;

/**
 * Clase que simula la capa de acceso a datos. Cuando veamos las interfaces crearemos una interfaz para esta clase.
 * También crearemos una clase que implemente esa interfaz y que se conecte a una base de datos relacional.
 * Y una clase servicio que podrá utilizar cualquiera de las dos implementaciones, la simulada, la real u otra.
 * Por ahora, simplemente es una clase con métodos estáticos que simulan la interacción con una base de datos.
 */
public class Dao {


    //region Colecciones que simulan la base de datos
    private static Map<String, Usuario> mapaUsuarios;

    private static Map<Long, Item> mapaItems;

    private static Map<Long, List<Puja>> mapaPujas;
    //endregion

    //region Inicialización de la base de datos simulada
    public static void ini() {
        iniMapaUsuarios();
        iniMapaItems();
        iniMapaPujas();
    }

    private static void iniMapaUsuarios() {
        // TODO 01 iniMapaUsuarios
        HashMap<String, Usuario> usuarios = new HashMap<>();
        String[] splitted;
        int contador = 1;
        for (String usuario : USUARIOS){
            splitted = usuario.split(SPLITTER);
            Usuario usuario1 = new Usuario(splitted[0], splitted[1],
                                           splitted[2], splitted[3]);
            usuarios.put(Integer.toString(contador), usuario1);
            contador++;
        }
        mapaUsuarios = usuarios;

        /*Recorrer el hashMap para comprobar:

        for (Map.Entry<String, Usuario> entrada : usuarios.entrySet()) {
            System.out.println(entrada.getKey() + " " + entrada.getValue().getNombre() + " ," + entrada.getValue().getSal() + " ," + entrada.getValue().getHashPwSal() + " ," + entrada.getValue().getRol());
        }*/
    }

    private static void iniMapaItems() {
        // TODO 02 iniMapaItems
        HashMap<String, Item> items = new HashMap<>();
        String[] splitted;
        int contador = 1;
        for (String item : ITEMS){
            String nombre = "Item" + contador;
            splitted = item.split(SPLITTER);
            long id = Long.parseLong(splitted[0]);
            int precio = Integer.parseInt(splitted[3]);
            int estado = Integer.parseInt(splitted[6]);
            boolean historico = Boolean.parseBoolean(splitted[7]);
            Item item1 = new Item(id,splitted[1],splitted[2],precio,splitted[4],splitted[5],estado,historico);

            items.put(Integer.toString(contador), item1);
            contador++;
        }
         /*Recorrer el hashMap para comprobar:

        for (Map.Entry<String, Item> entrada : items.entrySet()) {
            System.out.println(entrada.getKey() + entrada.getValue() + entrada.getValue().getUrlImagen() + entrada.getValue().getNombreUsuario() + entrada.getValue().getEstado() + entrada.getValue().isHistorico());
        }*/
    }

    private static void iniMapaPujas() {
        // TODO 03 iniMapaPujas
        HashMap<Integer, Puja> pujas = new HashMap<>();
        String[] splitted;
        int contador = 1;
        for (String puja : PUJAS){
            ArrayList<String> datos = new ArrayList<>();
            splitted = puja.split(SPLITTER);
            long id = Long.parseLong(splitted[0]);
            int precio = Integer.parseInt(splitted[2]) ;
            Puja nuevaPuja = new Puja(id,splitted[1],precio,splitted[3]);
            pujas.put(contador, nuevaPuja);
            contador++;
        }
        /* Recorrer el hashMap para comprobar:

        for (Map.Entry<Integer, Puja> entrada : pujas.entrySet()) {
            System.out.println(entrada.getKey().toString() + entrada.getValue() + ' ' + entrada.getValue().getIdItem());
        }*/
    }
    //endregion

    //region Usuarios
    public static boolean autenticar(String nombreUsuario, String password) {
//        return password.equals("1234");
        // TODO 04 autenticar
        return false;
    }

    public static boolean esAdmin(String nombreUsuario) {
//        return nombreUsuario.equalsIgnoreCase("Admin");
        // TODO 05 esAdmin
        return false;
    }

    public static List<Usuario> obtenerUsuarios() {
        // TODO 06 obtenerUsuarios
        return null;
    }

    public static boolean crearUsuario(String nombre, String password, boolean esAdmin) {
        // TODO 07 crearUsuario
        return true;
    }

    public static boolean modificarPasswordUsuario(String nombre, String password) {
        // TODO 08 modificarPasswordUsuario
        return false;
    }

    public static boolean modificarRolUsuario(String nombre, String rol) {
        // TODO 09 modificarRolUsuario
        return false;
    }

    public static boolean eliminarUsuario(String nombre) {
        // TODO 10 eliminarUsuario
        return true;
    }

    //endregion

    //region Validación de artículos
    public static List<Item> obtenerArticulosPendientes() {
        // TODO 11 obtenerArticulosPendientes
        return null;
    }

    public static boolean validarArticulo(long id, boolean valido) {
        // TODO 12 validarArticulo
        return false;
    }

    public static boolean validarTodos() {
        // TODO 13 validarTodos
        return true;
    }
    //endregion

    //region Gestión de artículos y pujas de administrador
    public static List<ItemPujas> obtenerArticulosConPujas() {
        // TODO 14 obtenerArticulosConPujas
        return null;
    }

    public static boolean resetearSubasta() {
        // TODO 15 resetearSubasta
        return true;
    }

    public static List<PujaItem> obtenerHistoricoGanadores() {
        // TODO 16 obtenerHistoricoGanadores
        return null;
    }
    //endregion

    //region Acciones por parte de usuario normal (no admin)

    public static Item obtenerArticuloPujable(long idArt) {
        // TODO 17 obtenerArticuloPujable
        return null;
    }

    public static List<Item> obtenerArticulosPujables() {
        // TODO 18 obtenerArticulosPujables
        return null;
    }

    public static boolean pujarArticulo(long idArt, String nombre, int precio) {
        // TODO 19 pujarArticulo
        return false;
    }

    public static List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario) {
        // TODO 20 obtenerPujasVigentesUsuario
        return null;
    }

    public static boolean ofrecerArticulo(Item item) {
        // TODO 21 ofrecerArticulo
        return true;
    }

    //endregion
}
