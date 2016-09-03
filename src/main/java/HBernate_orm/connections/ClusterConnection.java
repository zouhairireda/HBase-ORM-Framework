package sgcib.eliot.ewos.datalake.HBernate_orm.connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import sgcib.eliot.ewos.datalake.HBernate_orm.exceptions.ParsingException;
import sgcib.eliot.ewos.datalake.HBernate_orm.models.Properties;

public class ClusterConnection {

	/**
	 * properties that will be sent for connection with cluster - it can be a default properties of a new properties
	 */
	private static Optional<Properties> properties;

	public ClusterConnection() {
		super();
	}

	/**
	 *
	 * @param manualProperties
	 *            - if we would use a new configuration of driver and cluster
	 * @return connection object who represent a connection with Apache Phoenix
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection connect(Properties manualProperties) throws ClassNotFoundException, SQLException {
		try {
			properties = Optional.of(new Properties("config.xml"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e = new ParsingException("Unable to parse config.xml");
		}

		Class.forName(properties.orElse(manualProperties).getDriver());
		ThreadLocalSingleton.eINSTANCE
				.set(DriverManager.getConnection(properties.orElse(manualProperties).getCluster()));
		ThreadLocalSingleton.eINSTANCE.get().setAutoCommit(true);
		return ThreadLocalSingleton.eINSTANCE.get();
	}

}
