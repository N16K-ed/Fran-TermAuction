package edu.masanz.da.ta.menus;

import edu.masanz.da.ta.dao.Dao;
import edu.masanz.da.ta.dto.Item;
import edu.masanz.da.ta.ui.Gui;

import java.util.List;

import static edu.masanz.da.ta.conf.Ctes.*;

public class MenuUsrPujarArticulos  implements Menu {

    public void run() {
        Gui.mostrarMenuPujarArticulos();
        int opc = Gui.leerOpcion("Opción", 0, 2);
        while (opc != OPC_USR_PU_EXIT) {
            switch (opc) {
                case OPC_USR_PU_MOSTRAR_ITEMS_PUJABLES:
                    mostrarItemsPujables();
                    break;
                case OPC_USR_PU_PUJAR:
                    pujar();
                    break;
                default:
            }
            Gui.mostrarMenuPujarArticulos();
            opc = Gui.leerOpcion("Opción", 0, 2);
        }
    }

    private void mostrarItemsPujables() {
        List<Item> articulos = Dao.obtenerArticulosPujables();
        if (articulos.isEmpty()) {
            Gui.mostrarNoHayArticulosPujables();
            return;
        }
        List<String> texto = articulos.stream().map(Item::getInfo).toList();
        Gui.mostrarArticulos(texto);
    }

    private void pujar() {
        String nombre = Gui.leerTexto("Nombre usuario: ", 24);
        String password = Gui.leerTexto("Contraseña: ", 24);
        boolean esUsuario = Dao.autenticar(nombre, password) && !Dao.esAdmin(nombre);
        if (!esUsuario) {
            Gui.mostrarAutenticacion(false);
            return;
        }
        long idArt = Gui.leerNumero("Número artículo: ", 24);
        Item item = Dao.obtenerArticuloPujable(idArt);
        if (item == null) {
            Gui.mostrarArticuloNoDisponible();
            return;
        }
        int precio = (int)Gui.leerNumero("Precio que se puja: ", 24);
        if (precio < item.getPrecioInicio()) {
            Gui.mostrarPrecioInsuficiente();
            return;
        }
        boolean b = Gui.leerConfirmacion("Confirma la puja [s/N]: ", 24, "s");
        if (b) {
            b = Dao.pujarArticulo(idArt, nombre, precio);
        }
        Gui.mostrarPujaRealizada(b);
    }

}
