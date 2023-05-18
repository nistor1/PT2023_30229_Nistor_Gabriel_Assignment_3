package org.example.dataAccess;

import org.example.connection.ConnectionFactory;
import org.example.model.Bill;
import java.sql.*;
import java.util.logging.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AbstractDAO<T> {
    private Connection connection;
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private int maxId = 0;
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * @param field the field after which we want to select
     * @return sb the query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * @return sb the query for selecting all
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }
    /**
     * @return sb the query for deleting all
     */
    private String deleteByIdQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ID =?");
        return sb.toString();
    }
    /**
     * @param object the object we want to update
     * @return sb the query
     */
    private String updateQuery(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : object.getClass().getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                sb.append(field.getName());
                sb.append(" = ?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ID = ?");
        return sb.toString();
    }
    /**
     * @param object the object we want to insert
     * @return sb the query
     */
    private String createInsertQuery(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for (Field field : object.getClass().getDeclaredFields()) {
            sb.append(field.getName());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");
        for (Field field : object.getClass().getDeclaredFields()) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }
    /**
     * @param object the object we want to insert
     * @return sb the query
     */
    private String createInsertWithNoIDQuery(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for (Field field : object.getClass().getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                sb.append(field.getName());
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") VALUES (");
        for (Field field : object.getClass().getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                sb.append("?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }
    /**
     * @param id the result set
     * @return list the list of objects
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ID");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * @return list the list of objects
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
  /**
     * @param value the value of the field after which we want to selecT
     * @param targetType the field after which we want to select
     * @return id the id of the inserted object
     */
    private Object convertValue(Object value, Class<?> targetType) {
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value.toString());
        } else if (targetType == double.class || targetType == Double.class) {
            String valueString = value.toString().replace(",", ".");
            return Double.parseDouble(valueString);
        }

        // Adaugă conversii pentru alte tipuri dacă este necesar

        return value;
    }

    /**
     * @param resultSet the object we want to insert
     * @return list the list of objects
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance;

                if (type.equals(Bill.class)) {
                    // Obține obiectul de tip Bill direct din resultSet
                    instance = (T) new Bill(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("productName"), resultSet.getInt("quantity"));
                } else {
                    // Crează instanța utilizând constructorul
                    instance = (T) ctor.newInstance();
                    for (Field field : type.getDeclaredFields()) {
                        String fieldName = field.getName();
                        Object value = resultSet.getObject(fieldName);
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method setterMethod = propertyDescriptor.getWriteMethod();

                        // Obține tipul parametrului metodei setter
                        Class<?> parameterType = setterMethod.getParameterTypes()[0];

                        // Efectuează conversia valorii dacă tipurile nu coincid
                        if (!parameterType.isInstance(value)) {
                            value = convertValue(value, parameterType);
                        }

                        setterMethod.invoke(instance, value);
                    }
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Connection getConnection() {
        connection = ConnectionFactory.getConnection();
        return connection;
    }

    /**
     * @param t the object we want to insert
     * @return id the id of the inserted object
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = null;
        int id = 0;
        try{
            Field fieldId = t.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            System.out.println(fieldId.get(t));
            id = (Integer)fieldId.get(t);
            if(id <= 0) {
                query = createInsertWithNoIDQuery(t);
            }else {
                query = createInsertQuery(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    if((int)field.get(t) != 0 && id > 0) {
                        statement.setObject(i, field.get(t));
                        i++;
                    }
                }else {
                    statement.setObject(i, field.get(t));
                    i++;
                }
            }
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }
    /**
     * @param t the object we want to delete
     * @return id the id of the deleted object
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                if (field.getName().equals("id")) {
                    field.setAccessible(true);
                    statement.setObject(5, field.get(t));
                } else {
                    field.setAccessible(true);
                    statement.setObject(i, field.get(t));
                    i++;
                }
            }
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }
    /**
     * @param id the id of the object we want to delete
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = deleteByIdQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

}
