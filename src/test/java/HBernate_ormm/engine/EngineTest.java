package sgcib.eliot.ewos.datalake.HBernate_orm.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgcib.eliot.ewos.datalake.HBernate_orm.access.Person;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.TableNotFoundException;

public class EngineTest {

	private static Engine engine;

	@Before
	public void setUp() {
		engine = Engine.getInstance();
	}

	@Test
	public void getTableNameTest() {
		try {
			assertEquals(engine.getTableName(Person.class), "PERSON");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | TableNotFoundException
				| AnnotationNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getFieldsNameTest() {
		List<String> list = new ArrayList<>();
		list.add("ID");
		list.add("FIRSTNAME");
		list.add("LASTNAME");
		list.add("AGE");

		List<String> listResult = null;
		try {
			listResult = engine.getFieldsName(Person.class);
			assertEquals(list.size(), listResult.size());
			for (String sTemp : list) {
				assertTrue(listResult.contains(sTemp));
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | TableNotFoundException
				| AnnotationNotFoundException | ColumnNotFoundException e) {
			e.printStackTrace();
		}

	}
}
