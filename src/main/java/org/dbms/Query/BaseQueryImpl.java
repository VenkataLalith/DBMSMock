package org.dbms.Query;
import java.util.Map;
public abstract class BaseQueryImpl implements  QueryParser, QueryProcessor{

    private String tableName;
    private Map<String,String> columnData;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getColumnData() {
        return this.columnData;
    }

    public void setColumnData(Map<String, String> columnData) {
        this.columnData = columnData;
    }

    public  String getTableName(String query){
        return this.tableName;
    }
}
