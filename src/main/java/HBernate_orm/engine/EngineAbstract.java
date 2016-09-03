package sgcib.eliot.ewos.datalake.HBernate_orm.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.TableNotFoundException;

public abstract class EngineAbstract {

	/**
	 *
	 * @param <T>
	 * @return all fields name of current bean
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 * @throws ColumnNotFoundException
	 */
	public abstract <T> List<String> getFieldsName(Class<T> className)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, TableNotFoundException,
			AnnotationNotFoundException, ColumnNotFoundException;

	/**
	 *
	 * @param <T>
	 * @return string containing a table name from the current bean who will be processed
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws TableNotFoundException
	 * @throws AnnotationNotFoundException
	 */
	public abstract <T> String getTableName(Class<T> className) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TableNotFoundException, AnnotationNotFoundException;

}
