package org.ordersmgmtapp.genericdao;

import java.sql.SQLException;

/**
 * This interface represents a generic DAO repository for CRUD operations on entities.
 *
 * @param <T> The type of the entity.
 */
public interface GenericDAORepo<T> {

    /**
     * Creates a new entity in the database.
     *
     * @param obj The entity object to create.
     * @param tableName The name of the database table.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If a database access error occurs.
     */
    void create(T obj, String tableName, String primaryKeyColumnName) throws SQLException;

    /**
     * Updates an existing entity in the database.
     *
     * @param obj The entity object to update.
     * @param tableName The name of the database table.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If a database access error occurs.
     */
    void edit(T obj, String tableName, String primaryKeyColumnName) throws SQLException;

    /**
     * Deletes an entity from the database.
     *
     * @param id The ID of the entity to delete.
     * @param tableName The name of the database table.
     * @param primaryKeyColumnName The name of the primary key column.
     * @throws SQLException If a database access error occurs.
     */
    void delete(Object id, String tableName, String primaryKeyColumnName) throws SQLException;

    /**
     * Finds an entity by its ID in the database.
     *
     * @param id The ID of the entity to find.
     * @param tableName The name of the database table.
     * @param primaryKeyColumnName The name of the primary key column.
     * @param clazz The class of the entity.
     * @return The found entity object.
     * @throws SQLException If a database access error occurs.
     * @throws InstantiationException If the instantiation of the entity fails.
     * @throws IllegalAccessException If access to the entity is illegal.
     */
    T find(Object id, String tableName, String primaryKeyColumnName, Class<T> clazz)
            throws SQLException, InstantiationException, IllegalAccessException;
}
