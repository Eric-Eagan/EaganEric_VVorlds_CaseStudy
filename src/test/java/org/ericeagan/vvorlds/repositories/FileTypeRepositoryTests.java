package org.ericeagan.vvorlds.repositories;

import static org.junit.Assert.assertEquals;

import org.ericeagan.vvorlds.models.FileType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileTypeRepositoryTests {

	private FileTypeRepository ftr;
	private FileType expected;
	
	@Autowired
	public FileTypeRepositoryTests(FileTypeRepository ftr) {
		this.ftr = ftr;
	}
	
	@BeforeAll
	void setup() {
		expected = new FileType("test", "test");
		expected = ftr.save(expected);
	}
	
	@AfterAll
	void clearSetup() {
		ftr.delete(expected);
	}
	
	@Test
	void testGetById() {
		FileType actual = ftr.getById(expected.getTypeId());
		assertEquals(expected.getTypeId(), actual.getTypeId());
	}
	
	@Test
	void testGetByType() {
		FileType actual = ftr.getByType(expected.getType());
		assertEquals(expected, actual);
	}
}
