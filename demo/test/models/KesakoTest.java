package models;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class KesakoTest extends UnitTest {
	@Before
	public void setUp(){
		Fixtures.deleteAllModels();
		Fixtures.loadModels("data.yml");
	}
	@Test
	public void testFindAll(){
		List<Kesako> kesakoList = Kesako.findAll();
		assertEquals(2, kesakoList.size());
	}
}
