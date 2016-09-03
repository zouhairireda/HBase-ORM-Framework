package sgcib.eliot.datalake.HBernate_orm.connections;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import sgcib.eliot.datalake.HBernate.orm.connections.ClusterConnection;
import sgcib.eliot.datalake.HBernate.orm.exceptions.ParsingException;
import sgcib.eliot.datalake.HBernate.orm.models.Properties;

public class ClusterConnectionTest {

	private static final String CONFIG_NAME = "configTemp.xml";

	private Connection connection;

	public ClusterConnectionTest() {
		super();
	}

	// @Test
	public void connectionWithDefaultProperties() throws ClassNotFoundException, SQLException {
		connection = ClusterConnection.connect(null);
		assertNotNull(connection);
	}

	// @Test
	public void connectionWithNewProperties() {
		try {
			connection = ClusterConnection.connect(new Properties(CONFIG_NAME));
		} catch (ClassNotFoundException | SQLException | IOException | ParsingException e) {

		}
		assertNotNull(connection);
	}
}
