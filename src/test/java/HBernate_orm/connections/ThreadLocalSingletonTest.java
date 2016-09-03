package sgcib.eliot.datalake.HBernate_orm.connections;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import sgcib.eliot.datalake.HBernate.orm.connections.ThreadLocalSingleton;

public class ThreadLocalSingletonTest {

	@Test
	public void threadLocalSingletonTesting() {
		assertNotNull(ThreadLocalSingleton.eINSTANCE);
	}
}
