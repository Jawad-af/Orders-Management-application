package org.ordersmgmtapp.genericdao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * GenericDAO class provides generic methods for CRUD operations on any table entity.
 *
 * @param <T> The type of entity handled by this DAO.
 */
public class GenericDAO<T> implements GenericDAORepo<T>{
    private Connection connection;

    /**
     * Constructs a new GenericDAO with the given database connection.
     *
     * @param connection The database connection to be used.
     */
    public GenericDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a new record in the database for the specified entity.
     *
     * @param obj                  The entity object to be created.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If an SQL error occurs.
     */
    public void create(T obj, String tableName, String primaryKeyColumnName) throws SQLException {
        String insertQuery = generateInsertQuery(obj, tableName, primaryKeyColumnName);

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            setInsertParameters(statement, obj, primaryKeyColumnName);
            statement.executeUpdate();
        }
    }

    /**
     * Updates an existing record in the database for the specified entity.
     *
     * @param obj                  The entity object to be updated.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If an SQL error occurs.
     */
    public void edit(T obj, String tableName, String primaryKeyColumnName) throws SQLException {
        String updateQuery;
        try {
            updateQuery = generateUpdateQuery(obj, tableName, primaryKeyColumnName);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            setEditParameters(statement, obj, primaryKeyColumnName);
            statement.executeUpdate();
        }
    }

    /**
     * Deletes a record from the database based on the specified primary key value.
     *
     * @param id                   The primary key value of the record to be deleted.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If an SQL error occurs.
     */
    public void delete(Object id, String tableName, String primaryKeyColumnName) throws SQLException {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + primaryKeyColumnName + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Finds a record in the database based on the specified primary key value.
     *
     * @param id                   The primary key value of the record to be found.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @param clazz                The class of the entity.
     * @return The entity object if found, or null if not found.
     * @throws SQLException            If an SQL error occurs.
     * @throws InstantiationException If an instantiation error occurs.
     * @throws IllegalAccessException If an access error occurs.
     */
    public T find(Object id, String tableName, String primaryKeyColumnName, Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException {
        String selectQuery = "SELECT * FROM " + tableName + " WHERE " + primaryKeyColumnName + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                T obj = clazz.newInstance();
                mapResultSetToObject(resultSet, obj);
                return obj;
            }
        }
        return null;
    }

    /**
     * Generates the SQL INSERT query for the specified entity.
     *
     * @param obj                  The entity object.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @return The SQL INSERT query string.
     */
    private String generateInsertQuery(T obj, String tableName, String primaryKeyColumnName) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : fields) {
            if (field.getName().equals(primaryKeyColumnName)) {
                continue;
            }
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value != null) {
                        if (columns.length() > 0) {
                            columns.append(", ");
                            values.append(", ");
                        }
                        columns.append(field.getName());
                        values.append("?");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }

    /**
     * Generates the SQL UPDATE query for the specified entity.
     *
     * @param obj                  The entity object.
     * @param tableName            The name of the table in the database.
     * @param primaryKeyColumnName The name of the primary key column.
     * @return The SQL UPDATE query string.
     * @throws IllegalAccessException If an access error occurs.
     */
    private String generateUpdateQuery(T obj, String tableName, String primaryKeyColumnName) throws IllegalAccessException {
        StringBuilder queryBuilder = new StringBuilder("UPDATE ");
        queryBuilder.append(tableName).append(" SET ");

        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (!field.getName().equals(primaryKeyColumnName)) {
                queryBuilder.append(field.getName()).append(" = ?");
                if (i < fields.length - 1) {
                    queryBuilder.append(", ");
                }
            }
        }
        queryBuilder.append(" WHERE ").append(primaryKeyColumnName).append(" = ?");

        return queryBuilder.toString();
    }


    /**
     * Maps the result set to the specified entity object.
     *
     * @param resultSet The result set from the database query.
     * @param obj       The entity object to map to.
     * @throws SQLException       If an SQL error occurs.
     * @throws IllegalAccessException If an access error occurs.
     */
    private void mapResultSetToObject(ResultSet resultSet, T obj) throws SQLException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = resultSet.getObject(field.getName());
            field.set(obj, value);
        }
    }

    /**
     * Sets the parameters for the SQL UPDATE statement.
     *
     * @param statement            The prepared statement for the UPDATE query.
     * @param obj                  The entity object.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If an SQL error occurs.
     */
    private void setEditParameters(PreparedStatement statement, T obj, String primaryKeyColumnName) throws SQLException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(primaryKeyColumnName)) {
                continue;
            }
            try {
                Object value = field.get(obj);
                statement.setObject(parameterIndex++, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try {
            Field primaryKeyField = clazz.getDeclaredField(primaryKeyColumnName);
            primaryKeyField.setAccessible(true);
            Object primaryKeyValue = primaryKeyField.get(obj);
            statement.setObject(parameterIndex, primaryKeyValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the parameters for the SQL INSERT statement.
     *
     * @param statement            The prepared statement for the INSERT query.
     * @param obj                  The entity object.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If an SQL error occurs.
     */
    private void setInsertParameters(PreparedStatement statement, T obj, String primaryKeyColumnName) throws SQLException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(primaryKeyColumnName)) {
                continue;
            }
            try {
                Object value = field.get(obj);

                statement.setObject(parameterIndex++, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
