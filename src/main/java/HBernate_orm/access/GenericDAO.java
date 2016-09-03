package sgcib.eliot.ewos.datalake.HBernate_orm.access;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import sgcib.eliot.ewos.datalake.HBernate_orm.connections.ClusterConnection;
import sgcib.eliot.ewos.datalake.HBernate_orm.engine.Engine;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.FieldNotMappedException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.TableNotFoundException;

public class GenericDAO<T> implements IGenericDAO<T> {

	/**
	 * Object who will be processed
	 */
	protected Class<T> className;

	/**
	 * Object connection for create a connection with Apache Phoenix
	 */
	protected static Connection connection;

	/**
	 * engine for extract necessary data
	 */
	private static Engine engine;

	static {
		engine = Engine.getInstance();
		try {
			connection = ClusterConnection.connect(null);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public GenericDAO(Class<T> className) {
		super();
		this.className = className;
	}

	@Override
	public T saveOrUpdate(T classToUpsert)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, ColumnNotFoundException, SQLException {

		List<String> listColumns = engine.getFieldsName(className);
		String querySQLToExecute = "UPSERT INTO " + engine.getTableName(className) + "(";

		for (String column : listColumns) {
			querySQLToExecute += column + ",";
		}
		querySQLToExecute = querySQLToExecute.substring(0, querySQLToExecute.length() - 1);

		querySQLToExecute += ") VALUES(";

		Field[] fieldsOfClassToUpsert = classToUpsert.getClass().getDeclaredFields();

		List<Field> fields = Arrays.asList(fieldsOfClassToUpsert)
				.stream().filter(field -> (field.getModifiers() == Modifier.PRIVATE
						|| field.getModifiers() == Modifier.PUBLIC || field.getModifiers() == Modifier.PROTECTED))
				.collect(Collectors.toList());

		if (fields.size() == listColumns.size()) {

			for (Field field : fieldsOfClassToUpsert) {
				field.setAccessible(true);
				Object obj = field.get(classToUpsert);
				querySQLToExecute += "'" + obj + "',";
			}
			querySQLToExecute = querySQLToExecute.substring(0, querySQLToExecute.length() - 1);
			querySQLToExecute += ")";

			connection.prepareStatement(querySQLToExecute).executeUpdate();

			return classToUpsert;
		} else {
			throw new AnnotationNotFoundException();
		}
	}

	@Override
	public List<T> findAll()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, SQLException, InstantiationException, FieldNotMappedException {
		List<T> listAll = new ArrayList<T>();
		String sqlPhoenix = "";
		ResultSet result = null;
		String nameColumn = "";

		sqlPhoenix = "SELECT * FROM " + engine.getTableName(className);

		PreparedStatement p = connection.prepareStatement(sqlPhoenix);
		result = p.executeQuery();
		ResultSetMetaData metaData = result.getMetaData();

		while (result.next()) {

			T classToFind = (T) className.newInstance();
			Field[] fields = classToFind.getClass().getDeclaredFields();
			List<Field> fieldsOfT = Arrays.asList(fields).stream()
					.filter(field -> (field.getModifiers() == Modifier.PRIVATE
							|| field.getModifiers() == Modifier.PUBLIC || field.getModifiers() == Modifier.PROTECTED))
					.collect(Collectors.toList());

			if (metaData.getColumnCount() == fieldsOfT.size()) {
				for (Field field : fieldsOfT) {
					field.setAccessible(true);
					Annotation annotation = field.getAnnotations()[0];

					Class<? extends Annotation> specificAnnotationType = annotation.annotationType();
					for (Method method : specificAnnotationType.getDeclaredMethods()) {
						Object value = method.invoke(annotation, (Object[]) null);
						value = value.toString().trim();

						if (value != null && !value.toString().isEmpty()) {
							nameColumn = value.toString().toUpperCase();
						} else {
							nameColumn = field.getName().toUpperCase();
						}
					}
					field.set(classToFind, result.getString(nameColumn));
				}
			} else {
				throw new FieldNotMappedException(
						"Fields are not mapped correctly with table" + engine.getTableName(className));
			}

			listAll.add(classToFind);
		}

		return listAll;

	}

	@Override
	public List<T> find(String parameter, String value)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			TableNotFoundException, AnnotationNotFoundException, InstantiationException {
		List<T> listAll = new ArrayList<T>();
		String sqlPhoenix = "";
		ResultSet result = null;
		String nameColumn = "";

		sqlPhoenix = "SELECT * FROM " + engine.getTableName(className) + " WHERE " + parameter + "= '" + value + "'";

		PreparedStatement p = connection.prepareStatement(sqlPhoenix);
		result = p.executeQuery();
		ResultSetMetaData metaData = result.getMetaData();

		while (result.next()) {

			@SuppressWarnings("unchecked")
			T classToFind = (T) className.getClass().newInstance();
			Field[] fields = classToFind.getClass().getDeclaredFields();

			List<Field> fieldsOfT = Arrays.asList(fields).stream()
					.filter(field -> (field.getModifiers() == Modifier.PRIVATE
							|| field.getModifiers() == Modifier.PUBLIC || field.getModifiers() == Modifier.PROTECTED))
					.collect(Collectors.toList());

			if (metaData.getColumnCount() == fieldsOfT.size()) {
				for (Field field : fieldsOfT) {
					field.setAccessible(true);
					Annotation annotation = field.getAnnotations()[0];
					Class<? extends Annotation> specificAnnotationType = annotation.annotationType();
					for (Method method : specificAnnotationType.getDeclaredMethods()) {
						Object valuee = method.invoke(annotation, (Object[]) null);
						valuee = valuee.toString().trim();

						if (valuee != null && !valuee.toString().isEmpty()) {
							nameColumn = valuee.toString().toUpperCase();
						}
						nameColumn = className.getSimpleName().toUpperCase();
					}

					field.set(classToFind, result.getString(nameColumn));
				}
			} else {
				throw new AnnotationNotFoundException();
			}

			listAll.add(classToFind);
		}

		return listAll;
	}

	@Override
	public List<T> find(String whereClause)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, SQLException, InstantiationException {
		List<T> listAll = new ArrayList<T>();
		String sqlPhoenix = "";
		ResultSet result = null;
		String nameColumn = "";

		sqlPhoenix = "SELECT * FROM " + engine.getTableName(className) + " " + whereClause;

		PreparedStatement p = connection.prepareStatement(sqlPhoenix);
		result = p.executeQuery();
		ResultSetMetaData metaData = result.getMetaData();

		while (result.next()) {

			T classToFind = (T) className.newInstance();
			Field[] fields = classToFind.getClass().getDeclaredFields();
			List<Field> fieldsOfT = Arrays.asList(fields).stream()
					.filter(field -> (field.getModifiers() == Modifier.PRIVATE
							|| field.getModifiers() == Modifier.PUBLIC || field.getModifiers() == Modifier.PROTECTED))
					.collect(Collectors.toList());

			if (metaData.getColumnCount() == fieldsOfT.size()) {
				for (Field field : fieldsOfT) {
					field.setAccessible(true);
					Annotation annotation = field.getAnnotations()[0];
					Class<? extends Annotation> specificAnnotationType = annotation.annotationType();
					for (Method method : specificAnnotationType.getDeclaredMethods()) {
						Object value = method.invoke(annotation, (Object[]) null);
						value = value.toString().trim();

						if (value != null && !value.toString().isEmpty()) {
							nameColumn = value.toString().toUpperCase();
						} else {
							nameColumn = field.getName().toUpperCase();
						}
					}

					field.set(classToFind, result.getString(nameColumn));
				}
			} else {
				throw new AnnotationNotFoundException();
			}

			listAll.add(classToFind);
		}

		return listAll;
	}

	@Override
	public boolean deleteAll() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			TableNotFoundException, AnnotationNotFoundException, SQLException {
		String querySQLToExecute = "DELETE FROM " + engine.getTableName(className);
		connection.prepareStatement(querySQLToExecute).executeUpdate();
		return true;
	}

	@Override
	public boolean delete(String parameter, String value) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TableNotFoundException, AnnotationNotFoundException, SQLException {
		String querySQLToExecute = "DELETE FROM " + engine.getTableName(className) + " WHERE " + parameter + "='"
				+ value + "'";
		connection.prepareStatement(querySQLToExecute).executeUpdate();
		return true;
	}

	@Override
	public long count() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SQLException, TableNotFoundException, AnnotationNotFoundException {
		ResultSet resultSet = connection.prepareStatement("SELECT COUNT(*) FROM " + engine.getTableName(className))
				.executeQuery();
		long count = 0;
		while (resultSet.next()) {
			count = resultSet.getLong(1);
			break;
		}

		return count;
	}

	@Override
	public ResultSet executeQuery(String querySelectToExecute) throws SQLException {
		return connection.prepareStatement(querySelectToExecute).executeQuery();
	}

	@Override
	public int executeUpdate(String queryUpsertToExecute) throws SQLException {
		return connection.prepareStatement(queryUpsertToExecute).executeUpdate();
	}

}
