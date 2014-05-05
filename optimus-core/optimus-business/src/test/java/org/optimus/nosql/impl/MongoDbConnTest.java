package org.optimus.nosql.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.optimus.model.event.GenericEvent;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoDbConnTest {

	protected void setUpMockito() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	// assume there is a class MyDatabase
	@Mock
	DB dbMock;

	@Mock
	DBCollection collectionMock;

	@Test
	public void testStore() throws Exception {
		setUpMockito();
		MongoDbConn conn = new MongoDbConn(dbMock);

		Mockito.when(dbMock.getCollection("test")).thenReturn(collectionMock);

		List<GenericEvent> list = new ArrayList<GenericEvent>();
		list.add(new GenericEvent("{}"));
		list.get(0).put("file", "c:\\temp\\fichier");
		list.add(new GenericEvent("{\"file\":\"test\"}"));

		conn.store("test", list);
	}
}
