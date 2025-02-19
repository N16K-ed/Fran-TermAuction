package edu.masanz.da.ta.ui;

import edu.masanz.da.ta.conf.Ctes;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Gui {

    private static Scanner scanner = new Scanner(System.in);

    private static void mostrarTitulo(String titulo) {
        int n = Ctes.LONG_TITULO, m1, m2;
        m1 = n - titulo.length();
        m2 = m1 / 2 - 1;
        m1 = m1 % 2 == 0? m2 : m2+1;
        System.out.println("*".repeat(n));
        System.out.println("*" + " ".repeat(m1) + titulo + " ".repeat(m2) + "*");
        System.out.println("*".repeat(n));
    }

    public static void mostrarTituloMenuAdmin() {
        String s = "ADMINISTRACIÓN DE LAS SUBASTAS";
        mostrarTitulo(s);
    }

    public static void mostrarTituloMenuUser() {
        String s = "SUBASTA A CIEGAS";
        mostrarTitulo(s);
    }

    public static void mostrarTituloMenuAdmUsuarios() {
        String s = "Gestión de usuarios";
        mostrarTitulo(s);
    }

    public static void mostrarTituloValidacion() {
        String s = "Validación de artículos";
        mostrarTitulo(s);
    }

    public static void mostrarTituloPujarArticulos() {
        String s = "Ver y pujar artículos";
        mostrarTitulo(s);
    }

    public static void mostrarTituloAutenticacion() {
        String s = "A U T E N T I C A C I Ó N";
        mostrarTitulo(s);
        System.out.println("Para salir deje algún campo vacío");
        System.out.println();
    }

    public static void mostrarTituloExit() {
        System.out.println();
        String s = "GRACIAS Y HASTA LA PRÓXIMA";
        mostrarTitulo(s);
    }

    public static void mostrarReseteado(boolean b) {
        if (b) {
            System.out.println("Subasta reseteada");
        } else {
            System.out.println("No se ha podido resetear la subasta");
        }
    }

    public static void mostrarAutenticacion(boolean resultado) {
        if (resultado) {
            System.out.println("Autenticación correcta");
        } else {
            System.out.println("Autenticación incorrecta");
        }
        System.out.println();
    }

    public static long leerIdArticulo(String txt) {
        return (long)leerOpcion(txt, 0, Integer.MAX_VALUE);
    }

    public static int leerOpcion(String txt, int min, int max) {
        int opc = -1;
        if (max == Integer.MAX_VALUE) {
            System.out.printf("%s: ", txt);
        } else {
            System.out.printf("%s [%d,%d]: ", txt, min, max);
        }
        while(opc<min || opc>max) {
            try {
                opc = Integer.parseInt(scanner.nextLine().trim());
            }catch (Exception e) { }
            System.out.println();
        }
        return opc;
    }

    public static boolean leerConfirmacion(String txtMsg, int tam, String txtOk) {
        System.out.printf("%" + tam + "s", txtMsg);
        String resp = scanner.nextLine().trim();
        return resp.toUpperCase().equals(txtOk.toUpperCase());
    }

    public static String leerTexto(String txt, int tam) {
        System.out.printf("%" + tam + "s", txt);
        return scanner.nextLine().trim();
    }

    public static long leerNumero(String txt, int tam) {
        System.out.printf("%" + tam + "s", txt);
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static void mostrarMenuPrincipalAdministrador() {
        System.out.println();
        System.out.println("1. Validar artículos");
        System.out.println("2. Listar pujas de los artículos");
        System.out.println("3. Resetear sistema");
        System.out.println("4. Histórico ganadores");
        System.out.println("5. Gestionar usuarios");
        System.out.println("0. Exit");
    }

    public static void mostrarMenuUsuarios() {
        System.out.println();
        System.out.println("1. Mostrar usuarios y roles");
        System.out.println("2. Crear usuario");
        System.out.println("3. Modificar password");
        System.out.println("4. Modificar rol");
        System.out.println("5. Eliminar usuario");
        System.out.println("0. Exit");
    }

    public static void mostrarMenuValidacion() {
        System.out.println();
        System.out.println("1. Mostrar artículos pendientes");
        System.out.println("2. Validar un artículo");
        System.out.println("3. Validar todos los artículos");
        System.out.println("4. Invalidar un artículo");
        System.out.println("0. Exit");
    }

    public static void mostrarMenuPrincipalUsuarios() {
        System.out.println();
        System.out.println("1. Ver artículos y pujar");
        System.out.println("2. Listar pujas realizadas");
        System.out.println("3. Ofertar un artículo");
        System.out.println("0. Exit");
    }

    public static void mostrarMenuPujarArticulos() {
        System.out.println();
        System.out.println("1. Mostrar artículos");
        System.out.println("2. Pujar por un artículo");
        System.out.println("0. Exit");
    }

    public static void mostrarListado(List<String> lista) {
        for (Object obj : lista) {
            System.out.println(obj);
        }
    }

    public static void mostrarUsuarioCreado(boolean b) {
        if (b) {
            System.out.println("Usuario creado");
        } else {
            System.out.println("No se ha podido crear el usuario");
        }
    }

    public static void mostrarPasswordCambiada(boolean b) {
        if (b) {
            System.out.println("Se ha cambiado la password");
        } else {
            System.out.println("No se ha podido cambiar la password");
        }
    }

    public static void mostrarRolUsuarioCambiado(boolean b) {
        if (b) {
            System.out.println("Se ha cambiado el rol");
        } else {
            System.out.println("No se ha podido cambiar el rol");
        }
    }

    public static void mostrarUsuarioEliminado(boolean b) {
        if (b) {
            System.out.println("Se ha eliminado el usuario");
        } else {
            System.out.println("No se ha eliminado el usuario");
        }
    }

    public static void mostrarArticuloNoDisponible() {
        System.out.println("Artículo no disponible");
    }

    public static void mostrarNoHayArticulosPujables() {
        System.out.println("No hay artículos pujables");
    }

    public static void mostrarPrecioInsuficiente() {
        System.out.println("El precio debe ser mayor o igual al de salida");
    }
    
    public static void mostrarArticuloValidado(boolean b) {
        if (b) {
            System.out.println("Artículo validado\n");
        } else {
            System.out.println("No se ha podido validar el artículo\n");
        }
    }

    public static void mostrarArticuloInvalidado(boolean b) {
        if (b) {
            System.out.println("Artículo invalidado\n");
        } else {
            System.out.println("No se ha podido invalidar el artículo\n");
        }
    }

    public static void mostrarArticulosValidos(boolean b) {
        if (b) {
            System.out.println("Validados todos los artículos");
        } else {
            System.out.println("No se han podido validar todos los artículos");
        }
    }

    public static void mostrarPujaRealizada(boolean b) {
        if (b) {
            System.out.println("Puja realizada");
        } else {
            System.out.println("Puja NO realizada");
        }
    }


    public static void mostrarArticuloOfrecido(boolean b) {
        if (b) {
            System.out.println("Artículo ofertado pendiente de validar.");
        } else {
            System.out.println("Artículo NO ofertado");
        }
    }

    public static void mostrarMapa(Map<String,String> mapa, String tit1, String tit2, int n1, int n2) {
        String s0 = String.format("|%s|%s|", "-".repeat(n1), "-".repeat(n2));
        String s1 = String.format("|%-"+n1+"s|%-"+n2+"s|", tit1, tit2);
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s0);
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("|%-"+n1+"s|%-"+n2+"s|\n", key, value);
        }
        System.out.println(s0);
    }

    public static void mostrarHistoricoGanadores(List<String> texto) {
        String s0 = "|------|---------------------------|--------|--------|--------|--------|------------|";
        String s1 = "| N.id | Nombre del artículo       | P.ini. | Propi. | P.com. | Compr. | Fecha puja |";
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s0);
        if (texto.size() == 0) {
            System.out.printf("| %-81s |\n","No hay historico de ganadores");
        }else{
            mostrarListado(texto);
        }
        System.out.println(s0);
    }

    public static void mostrarArticulos(List<String> texto) {
        String s0 = String.format("|%s|", "-".repeat(44));
        String s1 = "| N.id | Nombre del artículo        | P.ini. |";
        String s2 = "| Descripción artículo.  Enlace a la imagen. |";
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s0);
        if (texto.size() == 0) {
            System.out.printf("| %-42s |\n","No hay artículos");
            System.out.println(s0);
        }else{
            mostrarListado(texto);
        }
    }

    public static void mostrarInfoItem(String info) {
        String s0 = String.format("|%s|", "-".repeat(44));
        String s1 = "| N.A. | Nombre del artículo        | P.ini. |";
        String s2 = "| Descripción artículo & Enlace a la imagen. |";
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s0);
        System.out.println(info);
    }

    public static void mostrarPujasUsuario(List<String> texto) {
        String s0 = String.format("|%s|", "-".repeat(78));
        String s1 = String.format("| %-4s | %-25s | %-6s | %-6s | %-23s |",
                "N.id", "Nombre del artículo", "P.ini.", "P.com.", "Instante de tiempo");
        System.out.println(s0);
        System.out.println(s1);
        System.out.println(s0);
        if (texto.size() == 0) {
            System.out.printf("| %-81s |\n","No se han realizado pujas");
            System.out.println(s0);
        }else{
            mostrarListado(texto);
        }
    }

}
