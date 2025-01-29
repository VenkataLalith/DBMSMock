package org.dbms;


import org.dbms.Query.SelectQueryImpl;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class SelectQueryImplTest {
    private SelectQueryImpl selectQuery;

    @Test
    public void testValidSimpleQuery() {
        selectQuery = new SelectQueryImpl("SELECT * FROM Users;");
        assertTrue(selectQuery.isQueryValid());
    }

    @Test
    public void testValidQueryWithColumns() {
        selectQuery = new SelectQueryImpl("SELECT name, email FROM Users;");
        assertTrue(selectQuery.isQueryValid());
    }

    @Test
    public void testValidQueryWithWhere() {
        selectQuery = new SelectQueryImpl("SELECT * FROM Users WHERE age > 18;");
        assertTrue(selectQuery.isQueryValid());
    }

    @Test
    public void testNullQuery() {
        selectQuery = new SelectQueryImpl(null);
        assertFalse(selectQuery.isQueryValid());
    }

    @Test
    public void testEmptyQuery() {
        selectQuery = new SelectQueryImpl("");
        assertFalse(selectQuery.isQueryValid());
    }

    @Test
    public void testInvalidSyntax() {
        selectQuery = new SelectQueryImpl("SELEC * FORM Users");
        assertFalse(selectQuery.isQueryValid());
    }

    @Test
    public void testProcessAllColumns() {
        selectQuery = new SelectQueryImpl("SELECT * FROM Users;");
        Map<String, Map<String, String>> result = selectQuery.processQuery();

        assertNotNull(result);
        assertTrue(result.containsKey("Users"));
        Map<String, String> columnMap = result.get("Users");
        assertTrue(columnMap.containsKey("*"));
    }

    @Test
    public void testProcessSpecificColumns() {
        selectQuery = new SelectQueryImpl("SELECT name, age FROM Users;");
        Map<String, Map<String, String>> result = selectQuery.processQuery();

        assertNotNull(result);
        Map<String, String> columnMap = result.get("Users");
        assertTrue(columnMap.containsKey("name"));
        assertTrue(columnMap.containsKey("age"));
    }

    @Test
    public void testWhereClause() {
        selectQuery = new SelectQueryImpl("SELECT * FROM Users WHERE age > 18;");
        selectQuery.setWhereClause("age > 18");
        assertEquals("age > 18", selectQuery.getWhereClause());
    }

    @Test
    public void testProcessInvalidQuery() {
        selectQuery = new SelectQueryImpl("INVALID QUERY");
        Map<String, Map<String, String>> result = selectQuery.processQuery();
        assertNull(result);
    }

    @Test
    public void testMultipleSpaces() {
        selectQuery = new SelectQueryImpl("SELECT    name,   email    FROM    Users   WHERE   age > 18;");
        assertTrue(selectQuery.isQueryValid());
    }

    @Test
    public void testCaseSensitivity() {
        selectQuery = new SelectQueryImpl("SELECT firstName, lastName FROM Users;");
        Map<String, Map<String, String>> result = selectQuery.processQuery();

        assertNotNull(result);
        Map<String, String> columnMap = result.get("Users");
        assertTrue(columnMap.containsKey("firstName"));
        assertFalse(columnMap.containsKey("FIRSTNAME"));
    }

    @Test
    public void testQueryWithoutSemicolon() {
        selectQuery = new SelectQueryImpl("SELECT * FROM Users");
        assertTrue(selectQuery.isQueryValid());
    }
}