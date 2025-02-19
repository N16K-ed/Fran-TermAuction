package edu.masanz.da.ta.menus;

import edu.masanz.da.ta.dao.Dao;
import edu.masanz.da.ta.ui.Gui;

public class Login implements Menu {
    public void run() {
        while(true) {
            Gui.mostrarTituloAutenticacion();
            String username = Gui.leerTexto("Usuario   : ", -12);
            String password = Gui.leerTexto("Contraseña: ", -12);
            if (username.isEmpty() || password.isEmpty()) {
                Gui.mostrarTituloExit();
                break;
            }
            boolean b = Dao.autenticar(username, password);
            Gui.mostrarAutenticacion(b);
            if (b) {
                if (Dao.esAdmin(username)) {
                    Menu menu = new MenuAdmPrincipal();
                    menu.run();
                } else {
                    Menu menu = new MenuUsrPrincipal(username);
                    menu.run();
                }
            }
        }
    }
}
