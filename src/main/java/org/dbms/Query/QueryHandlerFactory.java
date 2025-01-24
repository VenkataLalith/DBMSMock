package org.dbms.Query;

public class QueryHandlerFactory {
    public static BaseQueryImpl getQueryHandler(String query){
        query = query.trim().toUpperCase();
        if (query.startsWith("INSERT")) {
            return new InsertQueryImpl();
        } else if (query.startsWith("SELECT")) {
            return new SelectQueryImpl();
        }
        throw new IllegalArgumentException("Unsupported query type");
    }
}
