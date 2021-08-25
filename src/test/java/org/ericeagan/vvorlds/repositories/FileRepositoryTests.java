package org.ericeagan.vvorlds.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.models.User;
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
class FileRepositoryTests {

	private FileRepository fr;
	private FileTypeRepository ftr;
	private UserRepository ur;
	private File expected;
	private User user;
	
	@Autowired
	public FileRepositoryTests(FileRepository fr, FileTypeRepository ftr, UserRepository ur) {
		this.fr = fr;
		this.ftr = ftr;
		this.ur = ur;
	}
	
	@BeforeAll
	void setup() {
		user = ur.getByUsername("admin");
		expected = new File(user, Set.of(user), ftr.getById(1), "Name", "Path");
		user.getOwnedFiles().add(expected);
		user.getSharedFiles().add(expected);
		expected = fr.save(expected);
		user = ur.save(user);
		expected = fr.save(expected);
	}
	
	@AfterAll
	void clearSetup() {
		user.getOwnedFiles().remove(expected);
		user.getSharedFiles().remove(expected);
		expected.getSharers().remove(user);
		ur.save(user);
		fr.save(expected);
		fr.delete(expected);
	}
	
	@Test
	void testGetById() {
		File actual = fr.getById(expected.getFileId());
		assertEquals(expected.getFileId(), actual.getFileId());
	}
	
	@Test
	void testGetByOwnerId() {
		List<File> actual = fr.getByOwnerId(expected.getOwner().getId());
		assertTrue(actual.contains(expected));
	}
	
	@Test
	void testGetBySharers_Id() {
		List<File> actual = fr.getBySharers_Id(expected.getSharers().iterator().next().getId());
		assertTrue(actual.contains(expected));
	}
}
