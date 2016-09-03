package sgcib.eliot.datalake.HBernate.orm.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import sgcib.eliot.datalake.HBernate.orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.datalake.HBernate.orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.datalake.HBernate.orm.exceptions.TableNotFoundException;

public class Engine extends EngineAbstract {

	private static Engine INSTANCE = new Engine();

	private Engine(){
		super();
	}

	public static Engine getInstance(){
		return INSTANCE;
	}

	@Override
	public <T> String getTableName(Class<T> className) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TableNotFoundException, AnnotationNotFoundException {
		Annotation[] generalAnnotationsOfClass = className.getAnnotations();
		if (generalAnnotationsOfClass != null && generalAnnotationsOfClass.length != 0) {
			for (Annotation annotation : generalAnnotationsOfClass) {
				Class<? extends Annotation> specificAnnotationType = annotation.annotationType();
				if (specificAnnotationType.getName().endsWith("Table")) {
					for (Method method : specificAnnotationType.getDeclaredMethods()) {
						Object value = method.invoke(annotation, (Object[]) null);
						value = value.toString().trim();
						if (value != null && !value.toString().isEmpty()) {
							return value.toString();
						}
						return className.getSimpleName().toUpperCase();
					}
				} else {
					throw new TableNotFoundException(
							"Table associated with class '" + className.getSimpleName() + "' not found");
				}
			}
		}

		throw new AnnotationNotFoundException("Annotation @Table not found");
	}

	@Override
	public <T> List<String> getFieldsName(Class<T> className) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, TableNotFoundException, AnnotationNotFoundException, ColumnNotFoundException {
		List<String> listFieldsName = new ArrayList<String>();

		for (Field field : className.getDeclaredFields()) {
			field.setAccessible(true);

			if ((field.getModifiers() == Modifier.PRIVATE || field.getModifiers() == Modifier.PUBLIC
					|| field.getModifiers() == Modifier.PROTECTED)) {
				Annotation[] generalAnnotationsOfField = field.getAnnotations();

				if (generalAnnotationsOfField != null && generalAnnotationsOfField.length != 0) {
					for (Annotation annotation : generalAnnotationsOfField) {
						Class<? extends Annotation> specificAnnotationType = annotation.annotationType();
						if (specificAnnotationType.getName().endsWith("Column")) {
							for (Method method : specificAnnotationType.getDeclaredMethods()) {
								Object value = method.invoke(annotation, (Object[]) null);
								value = value.toString().toUpperCase().trim();
								if (value == null || value.toString().isEmpty()) {

									value = field.getName().toUpperCase();
								}
								listFieldsName.add(value.toString());

							}
						} else {
							throw new ColumnNotFoundException(
									"Column associated with field '" + field.getName() + "' not found");
						}
					}
				} else {
					throw new AnnotationNotFoundException("Annotation @Column not found");

				}

			}
		}

		return listFieldsName;

	}

}
