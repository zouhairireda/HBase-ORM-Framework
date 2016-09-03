package sgcib.eliot.ewos.datalake.HBernate_orm.connections;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ParsingException;
import sgcib.eliot.ewos.datalake.HBernate_orm.models.Properties;

public class ClusterConnectionTest {

	private Connection connection;

	public ClusterConnectionTest() {
		super();
	}

	@Test
	public void connectionWithDefaultProperties() throws ClassNotFoundException, SQLException {
		connection = ClusterConnection.connect(null);
		assertNotNull(connection);
	}

	@Test
	public void connectionWithNewProperties() {
		try {
			connection = ClusterConnection.connect(new Properties("config.xml"));
		} catch (ClassNotFoundException | SQLException | IOException | ParsingException e) {

		}
		assertNotNull(connection);
	}
}
