package org.dbms.Query;
import java.util.Map;

public interface QueryProcessor {
    //Returns Map containing table name as key and table data Map as value
    public Map<String,Map<String,String>> processQuery();
}
