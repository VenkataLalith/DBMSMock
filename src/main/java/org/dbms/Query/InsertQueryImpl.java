package org.dbms.Query;

import java.util.Map;
/*
* To Clarify:
*   1) Validate Table MetaData - Table name, columns name, constraints
*   2) QUery Implementation class should read the details from the query string just return the query data and metadata ?
*       Or need to validate it with db data as well ?
* */
public class InsertQueryImpl extends BaseQueryImpl{

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
