package com.dmjsistemas.util;

public class Configuracion {

    //**Windows**//
//    private static final String conexDB = "jdbc:sqlserver://10.220.221.158\\SQLEXPRESS;databaseName=DBFM30";
//    private static final String conexUser = "sa";
//    private static final String conexPwd = "sa";
    
    //**Linux**//
    private static final String conexDBSae = "jdbc:sqlserver://localhost;databaseName=SAE80Empre01";
    private static final String conexDBCoi = "jdbc:sqlserver://localhost;databaseName=COI90EMPRE1";
    private static final String conexUser = "sa";
    private static final String conexPwd = "Dev23DMJMS1t3m45";

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
