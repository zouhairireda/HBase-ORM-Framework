package sgcib.eliot.ewos.datalake.HBernate_orm.connections;

import java.sql.Connection;

public interface ThreadLocalSingleton {
	public static ThreadLocal<Connection> eINSTANCE = new ThreadLocal<>();
}
