package sgcib.eliot.ewos.datalake.HBernate_orm.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.AnnotationNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ColumnNotFoundException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.FieldNotMappedException;
import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.TableNotFoundException;

public class GenericDAOTest {

	private IGenericDAO<Person> dao;

	@Before
	public void setUp() {
		dao = new GenericDAO<Person>(Person.class);
	}

	@Test
	public void deleteTest() {
		try {
			assertTrue(dao.delete("id", "1"));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException
				| TableNotFoundException | AnnotationNotFoundException e) {

		}
	}

	@Test
	public void deleteAllTest() {
		try {
			assertTrue(dao.deleteAll());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException
				| TableNotFoundException | AnnotationNotFoundException e) {

		}
	}

	@Test
	public void findAllTest() {
		try {
			List<Person> persons = dao.findAll();
			assertNotNull(persons);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| SQLException | TableNotFoundException | AnnotationNotFoundException | FieldNotMappedException e) {

		}
	}

	@Test
	public void findByParameterAndValueTest() {
		try {
			List<Person> persons = dao.find("id", "1");
			assertNotNull(persons);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| SQLException | TableNotFoundException | AnnotationNotFoundException e) {

		}
	}

	@Test
	public void executeQueryInsertTest() {
		try {
			int persons = dao.executeUpdate("UPSERT INTO PERSON VALUES('55445','firstNameTest','lastNameTest','78')");
			assertTrue(persons == 1);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryDeleteOneTest() {
		try {
			int persons = dao.executeUpdate("DELETE FROM PERSON WHERE id = '1'");
			assertTrue(persons == 1);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryDeleteAllTest() {
		try {
			int persons = dao.executeUpdate("DELETE FROM PERSON");
			assertTrue(persons == 1);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryFindOneTest() {
		try {
			ResultSet persons = dao.executeQuery("SELECT * FROM PERSON WHERE id = '1'");
			assertNotNull(persons);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryFindAllTest() {
		try {
			ResultSet persons = dao.executeQuery("SELECT * FROM PERSON");
			assertNotNull(persons);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryUpdateTest() {
		try {
			int persons = dao.executeUpdate("UPSERT INTO PERSON VALUES('2','Laurentt','Bocquet','36')");
			assertTrue(persons == 1);

		} catch (SQLException e) {

		}
	}

	@Test
	public void executeQueryRandomUpsertTest() {
		try {
			int persons = dao.executeUpdate("UPSERT INTO PERSON VALUES('6554','Laurentt','Bocquet','36')");
			assertTrue(persons == 1);

		} catch (SQLException e) {

		}
	}

	@Test
	public void findByWhereClause() {
		try {
			List<Person> persons = dao.find("WHERE 'id' = 1");
			assertNotNull(persons);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| SQLException | TableNotFoundException | AnnotationNotFoundException e) {

		}
	}

	@Test
	public void getCountTest() {
		try {
			if (dao.deleteAll()) {
				assertEquals(0, dao.count());
			}

			if (dao.saveOrUpdate(new Person("1111", "Olivier", "Alphand", "37")) != null) {
				assertTrue(dao.count() != 0);
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException
				| TableNotFoundException | AnnotationNotFoundException | ColumnNotFoundException e) {

		}
	}

	@Test
	public void saveOrUpdateTest() {

		try {
			Person person = dao.saveOrUpdate(new Person("7558775", "FirstNameTest", "LastNameTest", "65"));
			assertNotNull(person);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException
				| TableNotFoundException | AnnotationNotFoundException | ColumnNotFoundException e) {

		}
	}
}
