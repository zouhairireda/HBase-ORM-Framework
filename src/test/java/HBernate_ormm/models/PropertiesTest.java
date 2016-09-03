package sgcib.eliot.ewos.datalake.HBernate_orm.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ParsingException;

public class PropertiesTest {

	private String fileConfig;

	public PropertiesTest() {
		super();
		fileConfig = "config.xml";
	}

	@Test
	public void mapToPropertiesTest() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("driver", "fakeDriver");
		map.put("cluster", "fakeCluster");
		Properties p = Properties.mapToProperties(map);
		assertNotNull(p);
		assertNotNull(p.getCluster());
		assertNotNull(p.getDriver());
		assertEquals("fakeDriver", p.getDriver());
		assertEquals("fakeCluster", p.getCluster());
	}

	@Test
	public void propertiesNotNull() throws IOException, ParsingException {
		Properties p = new Properties(fileConfig);
		assertNotNull(p);
		assertNotNull(p.getCluster());
		assertNotNull(p.getDriver());
	}

}
