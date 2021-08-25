package org.ericeagan.vvorlds.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.List;

import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.repositories.FileRepository;
import org.ericeagan.vvorlds.services.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FileServiceTests {
	
	private static FileService fs;
	private static FileRepository fr;
	
	@BeforeAll
	static void setup() {
		fr = Mockito.mock(FileRepository.class);
		fs = new FileServiceImpl(fr);
	}
	
	@Test
	void testSave() {
		File expected = new File();
		Mockito.when(fr.save(Mockito.any(File.class))).then(
				returnsFirstArg());
		File actual = fs.save(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetById() {
		File expected = new File();
		Mockito.when(fr.getById(1)).thenReturn(
				expected);
		File actual = fs.getById(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetByOwnerId() {
		List<File> expected = List.of(new File(), new File());
		Mockito.when(fr.getByOwnerId(1)).thenReturn(
				expected);
		List<File> actual = fs.getByOwnerId(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetBySharerId() {
		List<File> expected = List.of(new File(), new File());
		Mockito.when(fr.getBySharers_Id(1)).thenReturn(
				expected);
		List<File> actual = fs.getBySharerId(1);
		
		assertEquals(expected, actual);
	}
}
