package edu.masanz.da.ta.conf;

public class Ctes {

    // region roles
    public static final String ROL_ADMIN = "ADMIN";
    public static final String ROL_USER = "USER";
    // endregion

    // region configuración gráfica
    public static final int LONG_TITULO = 85;
    // endregion

    // region estados de los items
    public static final int EST_CANCELADO = -2;
    public static final int EST_RECHAZADO = -1;
    public static final int EST_PENDIENTE = 0;
    public static final int EST_ACEPTADO = 1;
    // endregion

    // region opciones principales administración
    public static final int OPC_ADM_PR_EXIT = 0;
    public static final int OPC_ADM_PR_VALIDAR_ITEMS = 1;
    public static final int OPC_ADM_PR_MOSTRAR_ITEMS_CON_PUJAS = 2;
    public static final int OPC_ADM_PR_RESETEAR_SUBASTA = 3;
    public static final int OPC_ADM_PR_MOSTRAR_HISTORICO_GANADORES = 4;
    public static final int OPC_ADM_PR_GESTIONAR_USUARIOS = 5;
    // endregion

    // region opciones validación administración
    public static final int OPC_ADM_VA_EXIT = 0;
    public static final int OPC_ADM_VA_MOSTRAR_ITEMS_PENDIENTES = 1;
    public static final int OPC_ADM_VA_VALIDAR_ITEM = 2;
    public static final int OPC_ADM_VA_VALIDAR_TODOS = 3;
    public static final int OPC_ADM_VA_INVALIDAR_ITEM = 4;
    // endregion

    // region opciones gestión de usuarios administración
    public static final int OPC_ADM_US_EXIT = 0;
    public static final int OPC_ADM_US_MOSTRAR_USUARIOS = 1;
    public static final int OPC_ADM_US_CREAR_USUARIO = 2;
    public static final int OPC_ADM_US_MODIFICAR_PASSWORD = 3;
    public static final int OPC_ADM_US_MODIFICAR_ROL = 4;
    public static final int OPC_ADM_US_ELIMINAR_USUARIO = 5;
    // endregion

    // region opciones principales de usuario normal
    public static final int OPC_USR_PR_EXIT = 0;
    public static final int OPC_USR_PR_PUJAR_ITEMS = 1;
    public static final int OPC_USR_PR_MOSTRAR_PUJAS_USUARIO = 2;
    public static final int OPC_USR_PR_OFERTAR_ITEM = 3;
    // endregion

    // region opciones ver artículos y pujar
    public static final int OPC_USR_PU_EXIT = 0;
    public static final int OPC_USR_PU_MOSTRAR_ITEMS_PUJABLES = 1;
    public static final int OPC_USR_PU_PUJAR = 2;
    // endregion

}
