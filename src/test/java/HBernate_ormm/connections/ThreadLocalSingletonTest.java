package sgcib.eliot.ewos.datalake.HBernate_orm.connections;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ThreadLocalSingletonTest {

	@Test
	public void threadLocalSingletonTesting() {
		assertNotNull(ThreadLocalSingleton.eINSTANCE);
	}
}
