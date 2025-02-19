package edu.masanz.da.ta;

import edu.masanz.da.ta.dao.Dao;
import edu.masanz.da.ta.menus.*;

public class Main {

    public static void main(String[] args) {
        Dao.ini();//Inicializa la base de datos simulada
//        Menu menu = new MenuUsrPrincipal();
//        Menu menu = new MenuUsrPujarArticulos();
//        Menu menu = new MenuAdmUsuarios();
//        Menu menu = new MenuAdmValidacion();
//        Menu menu = new MenuAdmPrincipal();
        Menu menu = new Login();
        menu.run();
    }

}