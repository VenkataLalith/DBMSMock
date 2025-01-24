package org.dbms.Query;

import java.util.Map;

public class SelectQueryImpl extends BaseQueryImpl{

    SelectQueryImpl(String queryString){
        super(queryString);
    }

    @Override
    public boolean isQueryValid() {
        return false;
    }


    @Override
    public Map<String, Map<String, String>> processQuery() {
        return null;
    }
}
