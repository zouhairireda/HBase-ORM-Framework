package sgcib.eliot.ewos.datalake.HBernate_orm.access;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.FieldNotMappedException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.TableNotFoundException;

public interface IGenericDAO<T> {

	/**
	 *
	 * @return list of objects imported from Apache HBase via Apache Phoenix
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws FieldNotMappedException
	 */
	public List<T> findAll()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, SQLException, InstantiationException, FieldNotMappedException;

	/**
	 *
	 * @param classToUpsert
	 *            - bean to be saved or updated on Apache HBase via Apache Phoenix
	 * @return bean who will be saved or updated on Apache HBase via Apache Phoenix
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws ColumnNotFoundException
	 * @throws SQLException
	 */
	public T saveOrUpdate(T classToUpsert)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, ColumnNotFoundException, SQLException;

	/**
	 *
	 * @param parameter
	 *            - parameter who will be used to searching
	 * @param value
	 *            - value of input parameter
	 * @return list of results
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws InstantiationException
	 */
	public List<T> find(String parameter, String value)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			TableNotFoundException, AnnotationNotFoundException, InstantiationException;

	/**
	 *
	 * @param whereClause
	 *            - to add a custom conditions - with string 'WHERE'
	 * @return list of results
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws SQLException
	 * @throws InstantiationException
	 */
	public List<T> find(String whereClause)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, SQLException, InstantiationException;

	/**
	 *
	 * @return boolean that inform the user if the delete action has been done
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws SQLException
	 */
	public boolean deleteAll() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			TableNotFoundException, AnnotationNotFoundException, SQLException;

	/**
	 *
	 * @param parameter
	 *            - parameter who will be used for searching
	 * @param value
	 *            - value of input parameter
	 * @return boolean that inform if the delete action has been done
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws SQLException
	 */
	public boolean delete(String parameter, String value) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TableNotFoundException, AnnotationNotFoundException, SQLException;

	/**
	 *
	 * @param queryToExecute
	 *            - manual query to execute on Apache HBase via Apache Phoenix
	 * @return native Apache Phoenix ResultSet of result
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String queryToExecute) throws SQLException;

	/**
	 *
	 * @return count of data on Apache HBase
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 */
	public long count() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SQLException, TableNotFoundException, AnnotationNotFoundException;

	/**
	 *
	 * @param queryToExecute
	 *            - manual query to send to Apache Phoenix for execution on Apache HBase
	 * @return state of query execution
	 * @throws SQLException
	 */
	public int executeUpdate(String queryToExecute) throws SQLException;

}
