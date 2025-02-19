package edu.masanz.da.ta.menus;

/**
 * Interfaz para los menús. Los menús deben implementar esta interfaz. Veremos que quiere decir esto más adelante.
 * Por ahora, simplemente que los menús deben tener un método run que se encargue de ejecutar el menú.
 * Es como en las colecciones de Java, declaramos una variable de un tipo genérico (e.g. Set, List, Map)
 * y luego la inicializamos con un objeto de una clase que implementa esa interfaz (e.g. HashSet, ArrayList, HashMap).
 * E.g. En el Main, declaramos una variable de tipo Menu y la inicializamos con un objeto de la clase Login.
 * Menu menu = new Login();
 * Se podía haber hecho con cualquier otra clase que implemente la interfaz Menu, por ejemplo MenuAdmPrincipal.
 * Menu menu = new MenuAdmPrincipal();
 * De esta forma no habría que autenticarse, directamente entraríamos en el menú de administración.
 */
public interface Menu {
    void run();
}
