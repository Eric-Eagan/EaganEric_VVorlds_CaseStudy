package org.ericeagan.vvorlds.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.List;

import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.repositories.FileTypeRepository;
import org.ericeagan.vvorlds.services.impl.FileTypeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FileTypeServiceTests {
	
	private static FileTypeService fts;
	private static FileTypeRepository ftr;
	
	@BeforeAll
	static void setup() {
		ftr = Mockito.mock(FileTypeRepository.class);
		fts = new FileTypeServiceImpl(ftr);
	}
	
	@Test
	void testSave() {
		FileType expected = new FileType();
		Mockito.when(ftr.save(Mockito.any(FileType.class))).then(
				returnsFirstArg());
		FileType actual = fts.save(expected);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetById() {
		FileType expected = new FileType();
		Mockito.when(ftr.getById(1)).thenReturn(
				expected);
		FileType actual = fts.getById(1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetByType() {
		FileType expected = new FileType();
		Mockito.when(ftr.getByType("Document")).thenReturn(
				expected);
		FileType actual = fts.getByType("Document");
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetByOwnerId() {
		List<FileType> expected = List.of(new FileType(), new FileType());
		Mockito.when(ftr.findAll()).thenReturn(
				expected);
		List<FileType> actual = fts.getAllFileTypes();
		
		assertEquals(expected, actual);
	}
}
