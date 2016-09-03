package sgcib.eliot.datalake.HBernate_orm.services;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import sgcib.eliot.datalake.HBernate.orm.exceptions.ParsingException;
import sgcib.eliot.datalake.HBernate.orm.models.Properties;
import sgcib.eliot.datalake.HBernate.orm.services.Parsing;

public class ParsingTest {

	private static final String CONFIG_FILE_NAME = "configTemp.xml";

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
