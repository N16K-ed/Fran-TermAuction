package edu.masanz.da.ta.menus;

import edu.masanz.da.ta.dao.Dao;
import edu.masanz.da.ta.ui.Gui;
import edu.masanz.da.ta.dto.Item;

import java.util.List;

import static edu.masanz.da.ta.conf.Ctes.*;

public class MenuAdmValidacion implements Menu {

    public void run() {
        Gui.mostrarMenuValidacion();
        int opc = Gui.leerOpcion("Opción", OPC_ADM_VA_EXIT, OPC_ADM_VA_INVALIDAR_ITEM);
        while (opc != OPC_ADM_VA_EXIT) {
            switch (opc) {
                case OPC_ADM_VA_MOSTRAR_ITEMS_PENDIENTES:
                    mostrarItemsPendientes();
                    break;
                case OPC_ADM_VA_VALIDAR_ITEM:
                    validarItem(true);
                    break;
                case OPC_ADM_VA_VALIDAR_TODOS:
                    validarTodos();
                    break;
                case OPC_ADM_VA_INVALIDAR_ITEM:
                    validarItem(false);
                    break;
                default:
            }
            Gui.mostrarMenuValidacion();
            opc = Gui.leerOpcion("Opción", OPC_ADM_VA_EXIT, OPC_ADM_VA_INVALIDAR_ITEM);
        }
    }

    private void mostrarItemsPendientes() {
        List<String> texto = Dao.obtenerArticulosPendientes().stream().map(Item::getInfo).toList();
        Gui.mostrarArticulos(texto);
    }

    private void validarItem(boolean valido) {
        long id = Gui.leerIdArticulo("Id del artículo");
        boolean b  = Dao.validarArticulo(id, valido);
        if (valido) {
            Gui.mostrarArticuloValidado(b);
        } else {
            Gui.mostrarArticuloInvalidado(b);
        }
    }

    private void validarTodos() {
        boolean b  = Dao.validarTodos();
        Gui.mostrarArticulosValidos(b);
    }

}
