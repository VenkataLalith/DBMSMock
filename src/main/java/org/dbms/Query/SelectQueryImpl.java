package org.dbms.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectQueryImpl extends BaseQueryImpl{

    private String selectRegex;
    private boolean isValidated;

    private String tableName;

    private String whereClause;
    private Map<String, Map<String,String>> tableData;

    SelectQueryImpl(String queryString){
        super(queryString);
        selectRegex = "^SELECT\\s+" +  // SELECT keyword
                "([*]|[\\w\\s,]+)\\s*" +  // Column names or *
                "FROM\\s+" +  // FROM keyword
                "([A-Za-z_][A-Za-z0-9_]*)" +  // Table name
                "(\\s+WHERE\\s+([^;]+))?" +  // Optional WHERE clause
                ";?$";  // Optional semicolon

    }

    @Override
    public boolean isQueryValid() {
        if(this.queryString == null)
            return false;
        Pattern pattern = Pattern.compile(this.selectRegex);
        Matcher matcher = pattern.matcher(this.queryString);
        this.isValidated = true;
        return matcher.matches();
    }


    @Override
    public Map<String, Map<String, String>> processQuery() {
        if(!this.isValidated){
            if(!isQueryValid()){
                return null;
            }
        }
        Pattern pattern = Pattern.compile(this.selectRegex);
        Matcher matcher = pattern.matcher(this.queryString);
        matcher.find();

        this.tableName = matcher.group(2);
        String columns = matcher.group(1);
        String[] columnNames = columns.equals("*") ? new String[]{"*"}: matcher.group(1).split("\\s*,\\s*");
        Map<String,String> columnData = new HashMap<>();
        for(String columnName : columnNames){
            columnData.put(columnName, null);
        }
        this.tableData.put(this.tableName,columnData);
        return this.tableData;
    }
    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }
}
