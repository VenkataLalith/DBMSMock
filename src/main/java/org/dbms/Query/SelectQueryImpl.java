package org.dbms.Query;

import java.util.Map;

public class SelectQueryImpl extends BaseQueryImpl{

    /**
     * @param query
     * @return
     */
    @Override
    public boolean isQueryValid(String query) {
        return false;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public Map<String, Map<String, String>> processQuery(String query) {
        return null;
    }
}
