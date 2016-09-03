package sgcib.eliot.datalake.HBernate.orm.connections;

import java.sql.Connection;

public interface ThreadLocalSingleton {
	public static ThreadLocal<Connection> eINSTANCE = new ThreadLocal<>();
}
