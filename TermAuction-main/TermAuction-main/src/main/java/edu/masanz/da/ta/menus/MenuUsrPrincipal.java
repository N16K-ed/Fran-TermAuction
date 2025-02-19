package edu.masanz.da.ta.menus;

import edu.masanz.da.ta.dao.Dao;
import edu.masanz.da.ta.dto.PujaItem;
import edu.masanz.da.ta.dto.Item;
import edu.masanz.da.ta.ui.Gui;

import java.util.List;

import static edu.masanz.da.ta.conf.Ctes.*;

public class MenuUsrPrincipal implements Menu {

    private String username;

    //region Constructors
    public MenuUsrPrincipal(String username) {
        this.username = username;
    }

    public MenuUsrPrincipal() {
        username = Gui.leerTexto("Usuario   : ", -12);
    }
    //endregion

    //region Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //endregion

    public void run() {
        Gui.mostrarTituloMenuUser();
        Gui.mostrarMenuPrincipalUsuarios();
        int opc = Gui.leerOpcion("Opción", 0, 3);
        while (opc != OPC_USR_PR_EXIT) {
            switch (opc) {
                case OPC_USR_PR_PUJAR_ITEMS:
                    menuPujarItems();
                    Gui.mostrarTituloMenuUser();
                    break;
                case OPC_USR_PR_MOSTRAR_PUJAS_USUARIO:
                    mostrarPujasUsuario();
                    break;
                case OPC_USR_PR_OFERTAR_ITEM:
                    ofertarItem();
                    break;
                default:
            }
            Gui.mostrarMenuPrincipalUsuarios();
            opc = Gui.leerOpcion("Opción", 0, 3);
        }
    }

    private void menuPujarItems() {
        Gui.mostrarTituloPujarArticulos();
        Menu menu = new MenuUsrPujarArticulos();
        menu.run();
    }

    private void mostrarPujasUsuario() {
        List<PujaItem> pujasArts = Dao.obtenerPujasVigentesUsuario(username);
        List<String> texto = pujasArts.stream().map(PujaItem::getInfo).toList();
        Gui.mostrarPujasUsuario(texto);
    }


    // {"id":6,"name":"Muñeco de Duke","desc":"Mascota de Java de 15 cm de alto hecha a punto rellena de algodón.",
    //  "price":20,"url":"https://i.postimg.cc/3RcGmk4s/duke.png","username":"Amaia","valid":true}//2468
    private void ofertarItem() {
        String nombreArticulo = Gui.leerTexto("Nombre artículo: ", 24);
        String descArticulo = Gui.leerTexto("Descripcion: ", 24);
        int precioSalida = (int)Gui.leerNumero("Precio de salida: ", 24);
        String urlImagen = Gui.leerTexto("Url imagen: ", 24);
        String nombreUsuario = Gui.leerTexto("Nombre usuario: ", 24);
        String passwordUsuario = Gui.leerTexto("Contraseña: ", 24);
        boolean esUsuario = Dao.autenticar(nombreUsuario, passwordUsuario) && !Dao.esAdmin(nombreUsuario);
        if (!esUsuario) {
            Gui.mostrarAutenticacion(false);
            return;
        }
        Item item = new Item(nombreArticulo, descArticulo, precioSalida, urlImagen, nombreUsuario);
        Gui.mostrarInfoItem(item.getInfo());
        boolean b = Gui.leerConfirmacion("Confirmar [s/N]: ", 24, "s");
        if (b) {
            b = Dao.ofrecerArticulo(item);
        }
        Gui.mostrarArticuloOfrecido(b);
    }
}
