package org.dbms.Query;

public class QueryHandlerFactory {
    public static BaseQueryImpl getQueryHandler(String query){
        query = query.trim().toUpperCase();
        if (query.startsWith("INSERT")) {
            return new InsertQueryImpl(query);
        } else if (query.startsWith("SELECT")) {
            return new SelectQueryImpl(query);
        }
        throw new IllegalArgumentException("Unsupported query type");
    }
}
