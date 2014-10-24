package org.jsonbuilder.implementation.mongodb;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.jsonbuilder.JsonBuilder;
import org.jsonbuilder.JsonBuilderTest;
import org.jsonbuilder.implementations.mongodb.MongoDbAdapter;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.junit.Test;

import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

public class JsonBuilderMongoDbTest extends JsonBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new MongoDbAdapter();
  }
  
  @Test
  public void shouldAllowObjectIdToBeBuildt() {
    StringBuilder person = new StringBuilder();
    person.append("{")
      .append("\"_id\":")
        .append("{\"$oid\":\"541427d67be92ba24260833c\"}")
      .append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("_id", new ObjectId("541427d67be92ba24260833c")).build();
    String serialized = JSON.serialize(json);
    assertEquals(person.toString(), new JsonParser().parse(serialized).toString());
  }
}