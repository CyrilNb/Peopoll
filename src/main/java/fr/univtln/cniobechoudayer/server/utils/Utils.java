package fr.univtln.cniobechoudayer.server.utils;

public class Utils {

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
