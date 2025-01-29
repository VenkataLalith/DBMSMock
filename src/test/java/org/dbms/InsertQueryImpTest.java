package org.dbms;
import org.dbms.Query.InsertQueryImpl;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.Map;

public class InsertQueryImpTest {
    private InsertQueryImpl insertQuery;

    @Test
    public void testValidQuery() {
        insertQuery = new InsertQueryImpl("INSERT INTO Users (name, email) VALUES ('John', 'john@example.com');");
        assertTrue(insertQuery.isQueryValid());
    }

    @Test
    public void testNullQuery() {
        insertQuery = new InsertQueryImpl(null);
        assertFalse(insertQuery.isQueryValid());
    }

    @Test
    public void testEmptyQuery() {
        insertQuery = new InsertQueryImpl("");
        assertFalse(insertQuery.isQueryValid());
    }

    @Test
    public void testInvalidSyntax() {
        insertQuery = new InsertQueryImpl("INSERT Users name, email VALUES 'John', 'john@example.com'");
        assertFalse(insertQuery.isQueryValid());
    }

    @Test
    public void testMismatchedColumnsAndValues() {
        insertQuery = new InsertQueryImpl("INSERT INTO Users (name, email) VALUES ('John');");
        assertFalse(insertQuery.isQueryValid());
    }

    @Test
    public void testProcessValidQuery() {
        insertQuery = new InsertQueryImpl("INSERT INTO Users (name, email) VALUES ('John', 'john@example.com');");
        Map<String, Map<String, String>> result = insertQuery.processQuery();

        assertNotNull(result);
        assertTrue(result.containsKey("Users"));
        Map<String, String> columnMap = result.get("Users");
        assertEquals("'John'", columnMap.get("name"));
        assertEquals("'john@example.com'", columnMap.get("email"));
    }

    @Test
    public void testProcessWithoutValidation() {
        insertQuery = new InsertQueryImpl("INSERT INTO Users (name, age) VALUES ('John', 25);");
        Map<String, Map<String, String>> result = insertQuery.processQuery();

        assertNotNull(result);
        Map<String, String> columnMap = result.get("Users");
        assertEquals("'John'", columnMap.get("name"));
        assertEquals("25", columnMap.get("age"));
    }

    @Test
    public void testProcessInvalidQuery() {
        insertQuery = new InsertQueryImpl("INVALID QUERY");
        Map<String, Map<String, String>> result = insertQuery.processQuery();
        assertNull(result);
    }

    @Test
    public void testCaseSensitivity() {
        insertQuery = new InsertQueryImpl("INSERT INTO Users (firstName, lastName) VALUES ('John', 'Doe');");
        Map<String, Map<String, String>> result = insertQuery.processQuery();

        assertNotNull(result);
        Map<String, String> columnMap = result.get("Users");
        assertTrue(columnMap.containsKey("firstName"));
        assertFalse(columnMap.containsKey("FIRSTNAME"));
    }

    @Test
    public void testMultipleSpaces() {
        insertQuery = new InsertQueryImpl("INSERT   INTO   Users    (name,   email)   VALUES    ('John',   'john@example.com');");
        assertTrue(insertQuery.isQueryValid());
    }
}
