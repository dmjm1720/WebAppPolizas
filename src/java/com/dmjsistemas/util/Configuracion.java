package com.dmjsistemas.util;

public class Configuracion {

    //**Windows**//
    private static final String conexDBSae = "jdbc:sqlserver://192.168.1.37\\SQLEXPRESS;databaseName=SAE80Empre01";
    private static final String conexDBCoi = "jdbc:sqlserver://192.168.1.37\\SQLEXPRESS;databaseName=COI90EMPRE1";
    private static final String conexUser = "sa";
    private static final String conexPwd = "Aspel**2013";
   
//    private static final String conexDBSae = "jdbc:sqlserver://localhost;databaseName=SAE80Empre01";
//    private static final String conexDBCoi = "jdbc:sqlserver://localhost;databaseName=COI90EMPRE1";
//    private static final String conexUser = "sa";
//    private static final String conexPwd = "Pr4xi5A5a*";
//   
    //**Linux**//


    public static String getConexDBSae() {
        return conexDBSae;
    }

    public static String getConexDBCoi() {
        return conexDBCoi;
    }

    public static String getConexUser() {
        return conexUser;
    }

    public static String getConexPwd() {
        return conexPwd;
    }

}
