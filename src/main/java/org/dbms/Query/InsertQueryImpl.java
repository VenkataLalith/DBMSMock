package org.dbms.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InsertQueryImpl extends BaseQueryImpl{

    private String[] columnNames;
    private String[] columnVals;
    private String insertRegex;
    private String tableName;
    private  Map<String, Map<String,String>> result;
    private Map<String,String> columnValuesMap;
    private boolean isValidatedFlag;
    InsertQueryImpl(String queryString){
        super(queryString);
        this.insertRegex = "^INSERT\\s+INTO\\s+" +  // INSERT INTO
                "([A-Za-z_][A-Za-z0-9_]*)\\s*" +  // Table name (valid identifier)
                "\\(([^)]+)\\)\\s*" +  // Columns in parentheses
                "VALUES\\s*" +  // VALUES keyword
                "\\(([^)]+)\\)\\s*" +  // Values in parentheses
                ";?$";  // Optional semicolon at the end
        this.isValidatedFlag = false;
    }
    /*
    * Validates whether given input string is syntactically correct or not
    * Returns true if a valid string
    * Returns false if an invalid string
    * */
    @Override
    public boolean isQueryValid() {
        if(this.queryString == null)
            return false;

        String tempQueryString = this.queryString.trim();
        Pattern pattern = Pattern.compile(this.insertRegex);
        Matcher matcher = pattern.matcher(tempQueryString);
        if(!matcher.matches())
            return false;
        this.columnNames =  matcher.group(2).split("\\s*,\\s*");
        this.columnVals = matcher.group(3).split("\\s*,\\s*");
        this.isValidatedFlag = true;
        return columnNames.length == columnVals.length;
    }

    /*
    * Checks if query string is already validated or not. If not validated, validates, else extracts
    *  data from the query string
    *  returns a Map with key as String ( Table Name)
    *  and value as a Map<String,String> (Column Name-Value Map)
    * */
    @Override
    public Map<String, Map<String, String>> processQuery() {
        if(!isValidatedFlag){
            if(!isQueryValid())
                return null;
        }
       Pattern pattern = Pattern.compile(this.insertRegex);
       Matcher matcher = pattern.matcher(queryString);

       matcher.find();
       this.tableName = matcher.group(1);
       if(columnNames == null)
            this.columnNames = matcher.group(2).split("\\s*,\\s*");
       if(columnVals == null)
           this.columnVals = matcher.group(3).split("\\s*,\\s*");
       this.result = new HashMap<>();
       this.columnValuesMap = new HashMap<>();
       for(int i=0;i<columnNames.length;i++){
            this.columnValuesMap.put(columnNames[i].trim(),columnVals[i].trim());
       }
       this.result.put(this.tableName, this.columnValuesMap);
        return this.result;
    }
}
