package edu.masanz.da.ta.dao;

import edu.masanz.da.ta.conf.Ini;
import edu.masanz.da.ta.dto.*;
import edu.masanz.da.ta.utils.Security;

import javax.swing.*;
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
        HashMap<Long, Item> items = new HashMap<>();
        String[] splitted;
        for (String item : ITEMS){
            splitted = item.split(SPLITTER);
            long id = Long.parseLong(splitted[0]);
            int precio = Integer.parseInt(splitted[3]);
            int estado = Integer.parseInt(splitted[6]);
            boolean historico = Boolean.parseBoolean(splitted[7]);
            Item item1 = new Item(id,splitted[1],splitted[2],precio,splitted[4],splitted[5],estado,historico);

            items.put(id, item1);

        }
        mapaItems = items;
         /*Recorrer el hashMap para comprobar:

        for (Map.Entry<String, Item> entrada : items.entrySet()) {
            System.out.println(entrada.getKey() + entrada.getValue() + entrada.getValue().getUrlImagen() + entrada.getValue().getNombreUsuario() + entrada.getValue().getEstado() + entrada.getValue().isHistorico());
        }*/
    }

    private static void iniMapaPujas() {
        // TODO 03 iniMapaPujas
        HashMap<Long, List<Puja>> pujas = new HashMap<>();
        String[] splitted;


        //crear el mapa con claves = IDItem --> obtienes un mapa con listas de pujas para cada objeto;
        Set<Long> claves = mapaItems.keySet();

        for (Long id : claves){ // crea mapa con las claves de los  items y listas de pujas con valores nulos
            pujas.put(id, new ArrayList<>());
        }
        //Crea lista de pujas sin ordenar
        List<Puja> pujasSinOrdenar= new ArrayList<>();
        for (String pujasINI : PUJAS){
            splitted = pujasINI.split(SPLITTER);
            long idITEM = Long.parseLong(splitted[0]);
            int precio = Integer.parseInt(splitted[2]);
            Puja puja1 = new Puja(idITEM,splitted[1],precio, splitted[3]);
            pujasSinOrdenar.add(puja1);
        }

        // bucle recorriendo claves añadiendo listas de pujas --> anidado otro bucle rellenando las listas
        for (Long id : pujas.keySet()){ //recorre el mapa pujas
            List<Puja> listadoPujasPorID = new ArrayList<>();
           for (Puja puja : pujasSinOrdenar){//recorre el arraylist de pujas sin ordenar
              if(puja.getIdItem() == id){
                  listadoPujasPorID.add(puja);
              }
           }
           pujas.put(id,listadoPujasPorID);
        }
        /* Recorrer el hashMap para comprobar:
        for (Map.Entry<Long, List<Puja>> entrada : pujas.entrySet()) {
            System.out.println("ID Item: " + entrada.getKey());
            for (Puja puja : entrada.getValue()) {
                System.out.println("  " + puja);
            }
        }*/
    }
    //endregion

    //region Usuarios
    public static boolean autenticar(String nombreUsuario, String password) {
//        return password.equals("1234");
        // TODO 04 autenticar
        Usuario aAutenticar= mapaUsuarios.get(nombreUsuario);
        if (aAutenticar == null){
            return false;
        }

        String sal = aAutenticar.getSal();
        String hashEnBaseDeDatos = aAutenticar.getHashPwSal();

        return Security.validateHashedPasswordSalt(password,sal,hashEnBaseDeDatos);
    }

    public static boolean esAdmin(String nombreUsuario) {
//        return nombreUsuario.equalsIgnoreCase("Admin");
        // TODO 05 esAdmin
        Usuario esAdminAComprobar = mapaUsuarios.get(nombreUsuario);
        if (esAdminAComprobar == null){
            return false;
        }
        return esAdminAComprobar.getRol().equals("ADMIN") ;
    }

    public static List<Usuario> obtenerUsuarios() {
        // TODO 06 obtenerUsuarios
        return new ArrayList<>(mapaUsuarios.values()); // Logrado con Show context actions (herramienta del Intelli, la bombilla esa, porque me salia un warning de que se podía hacer mejor)
        /*
        Código original:

        List<Usuario> listado = new ArrayList<>();
        for (Usuario usuario : mapaUsuarios.values()) {
            listado.add(usuario);
        }
        return listado;
        */

    }

    public static boolean crearUsuario(String nombre, String password, boolean esAdmin) {
        // TODO 07 crearUsuario

        if(mapaUsuarios.containsKey(nombre)){
            System.out.println("El usuario " + nombre + " ya se encuientra registrado en la base de datos.");
            return false;
        }
        String nuevoSal = Security.generateSalt();
        String nuevoHashPwSal = Security.hash(password + nuevoSal);
        String rol;
        if (esAdmin){
            rol = "ADMIN";
        }else{
            rol = "USER";
        }
        Usuario nuevoUser = new Usuario(nombre, nuevoSal, nuevoHashPwSal, rol);
        mapaUsuarios.put(nombre, nuevoUser);
        System.out.println("Usuario " + nombre + " añadido.");
        return true;
    }

    public static boolean modificarPasswordUsuario(String nombre, String password) {
        // TODO 08 modificarPasswordUsuario
        if(!mapaUsuarios.containsKey(nombre)){
            System.out.println("Usuario no encontrado");
            return false;
        }
        Usuario userACambiar = mapaUsuarios.get(nombre);
        String nuevoSal = Security.generateSalt();
        String nuevoHash = Security.hash(password + nuevoSal);

        userACambiar.setSal(nuevoSal);
        userACambiar.setHashPwSal(nuevoHash);
        System.out.println("La nueva contraseña es " + password);
        return true;
    }

    public static boolean modificarRolUsuario(String nombre, String rol) {
        // TODO 09 modificarRolUsuario
        if(!mapaUsuarios.containsKey(nombre)){
            System.out.println("Usuario no encontrado");
            return false;
        }
        if(!rol.equalsIgnoreCase("ADMIN") && !rol.equalsIgnoreCase("USER")){
            System.out.println("El rol introducido no es válido.\nFormatos válidos:\n\t- USER\n\t- ADMIN");
            return false;
        }
        Usuario userAModificarRol = mapaUsuarios.get(nombre);
        userAModificarRol.setRol(rol.toUpperCase());//Asegura que el rol sea mayusculas si se introduce en minúsculas
        return true;
    }

    public static boolean eliminarUsuario(String nombre) {
        // TODO 10 eliminarUsuario

        if(!mapaUsuarios.containsKey(nombre)){
            System.out.println("Usuario no encontrado");
            return false;
        }
        mapaUsuarios.remove(nombre);

        return true;
    }

    //endregion

    //region Validación de artículos
    public static List<Item> obtenerArticulosPendientes() {
        // TODO 11 obtenerArticulosPendientes
        List<Item> pendientes = new ArrayList<>();
        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_PENDIENTE) {
                pendientes.add(item);
            }
        }
        return pendientes;
    }

    public static boolean validarArticulo(long id, boolean valido) {
        // TODO 12 validarArticulo

        if (!mapaItems.containsKey(id)) {
            System.out.println("El artículo con ID " + id + "no está registrado en nuestra base de datos.");
            return false;
        }

        Item itemAValidar = mapaItems.get(id);
        String estadoActual = "";
        switch (itemAValidar.getEstado()){
            case EST_ACEPTADO:
                estadoActual = "Aceptado";
                break;
            case EST_PENDIENTE:
                estadoActual = "Pendiente";
                break;
            case EST_RECHAZADO:
                estadoActual = "Actual";
                break;
            case EST_CANCELADO:
                estadoActual = "Cancelado";
                break;
        }

        if (itemAValidar.getEstado() != EST_PENDIENTE){
            System.out.println("El artículo con ID " + id + " ya ha sido validado.\nEstado: " + estadoActual);
            return false;
        }

        if (valido){
            itemAValidar.setEstado(EST_ACEPTADO);
            System.out.println("El Item " + itemAValidar.getNombre() + " ha sido validado.");
        }else{
            itemAValidar.setEstado(EST_RECHAZADO);
            System.out.println("El Item " + itemAValidar.getNombre() + " ha sido rechazado.");        }

        return true;
    }

    public static boolean validarTodos() {
        // TODO 13 validarTodos
        boolean hayPendientes = false;

        for (Item item : mapaItems.values()) {
            if (item.getEstado() == EST_PENDIENTE) {
                item.setEstado(EST_ACEPTADO);
                System.out.println("El Item " + item.getNombre() + " ha sido validado.");
                hayPendientes = true;
            }
        }

        return hayPendientes;
    }
    //endregion

    //region Gestión de artículos y pujas de administrador
    public static List<ItemPujas> obtenerArticulosConPujas() {
        // TODO 14 obtenerArticulosConPujas

        List<ItemPujas> lista = new ArrayList<>();

        for (Map.Entry<Long, List<Puja>> entry : mapaPujas.entrySet()) {
            long idItem = entry.getKey();
            List<Puja> pujas = entry.getValue();

            if (!pujas.isEmpty()) { // si hay al menos 1 puja asignada al item (con el id)
                Item item = mapaItems.get(idItem);
                lista.add(new ItemPujas(item, pujas));
            }
        }
        return lista;
    }

    public static boolean resetearSubasta() {
        // TODO 15 resetearSubasta
        for (Item item : mapaItems.values()) {
            item.setEstado(EST_PENDIENTE);
            item.setHistorico(false);
        }
        mapaPujas.clear();
        System.out.println("Todos los artículos están pendientes y las pujas se han eliminado.");
        return true;
    }

    public static List<PujaItem> obtenerHistoricoGanadores() {
        // TODO 16 obtenerHistoricoGanadores
        List<PujaItem> historicoGanadores = new ArrayList<>();

        for (Item item : mapaItems.values()) {
            if (item.isHistorico()) {
                List<Puja> pujas = mapaPujas.get(item.getId());// lista de todas las pujas (historicos)

                if (pujas != null && !pujas.isEmpty()) {
                    Puja pujaGanadora = pujas.get(0); //la puja ganadora por defecto es la primera
                    for (Puja puja : pujas) {
                        if (puja.getPrecioPujado() > pujaGanadora.getPrecioPujado()){ //si alguna gana a la ganadora actual actualiza la puja ganadora
                            pujaGanadora = puja;
                        }
                    }
                    historicoGanadores.add(new PujaItem(item.getId(), item.getNombre(), item.getPrecioInicio(), item.getUrlImagen(), item.getNombreUsuario(), pujaGanadora.getNombreUsuario(), pujaGanadora.getPrecioPujado(), pujaGanadora.getInstanteTiempo()));
                    //añade el item con su puja en la lista a devolver
                }
            }
        }
        return historicoGanadores;
    }
    //endregion

    //region Acciones por parte de usuario normal (no admin)

    public static Item obtenerArticuloPujable(long idArt) {
        // TODO 17 obtenerArticuloPujable

        Item itemParaPujar = mapaItems.get(idArt);

        if (itemParaPujar == null || itemParaPujar.getEstado() != EST_ACEPTADO) { //el articulo o no existe o no se puede pujar
            return null;
        }

        return itemParaPujar;
    }

    public static List<Item> obtenerArticulosPujables() {
        // TODO 18 obtenerArticulosPujables

        List<Item> pujables = new ArrayList<>();

        for (Item item : mapaItems.values()) {
            if (obtenerArticuloPujable(item.getId()) != null) {
                pujables.add(item);
            }
        }

        return pujables;

    }

    public static boolean pujarArticulo(long idArt, String nombre, int precio) {
        // TODO 19 pujarArticulo
        Item item = obtenerArticuloPujable(idArt);
        if (item == null) {
            System.out.println("El artículo no está disponible para pujar.");
            return false;
        }

        List<Puja> pujas = mapaPujas.get(idArt);

        for (Puja puja : pujas) {
            if (precio <= puja.getPrecioPujado()) {
                System.out.println("La puja debe ser mayor a la actual.");
                return false;
            }
        }

        Puja nuevaPuja = new Puja(idArt, nombre, precio);
        pujas.add(nuevaPuja);

        System.out.println("Puja realizada por " + nombre + " al artículo " + item.getNombre());
        return true;
    }

    public static List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario) {
        // TODO 20 obtenerPujasVigentesUsuario
        List<PujaItem> pujasVigentes = new ArrayList<>();

        for (Map.Entry<Long, List<Puja>> entry : mapaPujas.entrySet()) {
            long idItem = entry.getKey();
            List<Puja> pujas = entry.getValue();

            for (Puja puja : pujas) { //recorres las pujas (TODAS)
                if (puja.getNombreUsuario().equals(nombreUsuario)) { //compreubas si pertenecen al usuario mirado
                    Item item = mapaItems.get(idItem);
                    if (item != null && item.getEstado() == EST_ACEPTADO) { // Comprueba si la puja del item es vigente
                        pujasVigentes.add(new PujaItem(idItem, item.getNombre(), item.getPrecioInicio(), item.getUrlImagen(), item.getNombreUsuario(), puja.getNombreUsuario(), puja.getPrecioPujado(), puja.getInstanteTiempo()));
                    }
                }
            }
        }
        return pujasVigentes;
    }

    public static boolean ofrecerArticulo(Item item) {
        // TODO 21 ofrecerArticulo
        if (mapaItems.containsKey(item.getId())) {
            System.out.println("El artículo con ID " + item.getId() + " ya existe.");
            return false;
        }

        item.setEstado(EST_PENDIENTE);
        item.setHistorico(false);

        mapaItems.put(item.getId(), item);

        System.out.println("El Artículo '" + item.getNombre() + "' ha sido añadido con éxito a la base de datos y está en espera de ser validado.");

        return true;
    }

    //endregion
}
