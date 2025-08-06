package com.tpi.vehiculos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
		"spring.datasource.driverClassName=org.h2.Driver",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class TpiVehiculosServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}