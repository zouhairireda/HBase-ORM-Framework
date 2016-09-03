package sgcib.eliot.ewos.datalake.HBernate_orm.services;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import net.sf.json.JSON;

public class DeserializerTest {

	private static final String CONFIG_FILE_NAME = "config.xml";

	private JSON jsonObject;

	public DeserializerTest() throws IOException {
		super();
		jsonObject = XmlToJSON.xmlToJSON(CONFIG_FILE_NAME);
	}

	@Test
	public void testDeserializingDone() {
		assertNotNull(Deserializer.deserialize(jsonObject));
	}
}
