package sgcib.eliot.ewos.datalake.HBernate_orm.services;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ParsingException;
import sgcib.eliot.ewos.datalake.HBernate_orm.models.Properties;

public class ParsingTest {

	private static final String CONFIG_FILE_NAME = "config.xml";

	private Properties properties;

	public ParsingTest() throws IOException, ParsingException {
		super();
		properties = Parsing.getProperties(CONFIG_FILE_NAME);
	}

	@Test
	public void testParsingDone() {
		assertNotNull(properties);
		assertNotNull(properties.getCluster());
		assertNotNull(properties.getDriver());
	}
}
