package org.jsonbuilder.implementation.mongodb;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.jsonbuilder.JsonObjectBuilder;
import org.jsonbuilder.JsonObjectBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class JsonObjectBuilderTestMongoDb extends JsonObjectBuilderTest {

  @Test
  @Override
  public void shouldCreateSimpleRoot() {
    String simpleObject = "{ \"simple\" : \"object\"}";
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.object("simple", "object");
    assertEquals(simpleObject, builder.build().toString());
  }
  
  @Test
  @Override
  public void shouldAllowAllJsonValuesInObject() {
    StringBuilder allTypes = new StringBuilder();
    allTypes.append("{")
      .append(" \"string\" : \"value\" ,")
      .append(" \"number\" : 5 ,")
      .append(" \"object\" : { \"in\" : \"side\"} ,")
      .append(" \"array\" : [ 1 , 4 , 6] ,")
      .append(" \"true\" : true ,")
      .append(" \"false\" : false ,")
      .append(" \"null\" :  null ")
      .append("}");
    
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.object("string", "value")
      .object("number", 5)
      .object("object")
        .object("in", "side").up()
      .array("array").value(1,4,6).up()
      .object("true", true)
      .object("false", false);
    String none = null;
    builder.object("null", none);
    assertEquals(allTypes.toString(), builder.build().toString());
  }
  
  @Test
  @Override
  public void shouldElegantlyAddValuesToArrayFromLoop() {
    StringBuilder contacts = new StringBuilder();
    contacts.append("{")
        .append(" \"title\" : \"My Contacts\" ,")
        .append(" \"contacts\" : ")
          .append("[")
            .append(" \"Messi\" ,")
            .append(" \"Xavi\" ,")
            .append(" \"Inesita\"")
          .append("]")
      .append("}");
    String [] myContacts = {"Messi", "Xavi", "Inesita"};
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.object("title", "My Contacts");
    for(String contact : myContacts) {
      builder.array("contacts")
        .value(contact);
    }
    assertEquals(contacts.toString(), builder.build().toString());
  }
  
  @Test
  @Override
  public void shouldEasilyBuildObjectsWithinArrayWithinObject() {
    StringBuilder shoppingCart = new StringBuilder();
    shoppingCart.append("{")
      .append(" \"cartItems\" : ")
        .append("[ ")
          .append("{ \"name\" : \"bicycle\" , \"price\" : 450} ,")
          .append(" { \"name\" : \"lego\" , \"price\" : 972}")
        .append("] ,")
      .append(" \"total\" : 1432")
      .append("}");
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.array("cartItems")
        .object()
          .object("name", "bicycle")
          .object("price", 450).upArray()
        .object()
          .object("name", "lego")
          .object("price", 972).upArray()
        .up()
      .object("total", 1432);
    assertEquals(shoppingCart.toString(), builder.build().toString());
  }
  
  @Test
  public void shouldAllowObjectIdToBeBuildt() {
    StringBuilder person = new StringBuilder();
    person.append("{")
      .append(" \"_id\" : ")
        .append("{ \"$oid\" : \"541427d67be92ba24260833c\"}")
      .append("}");
//    DBObject query = new BasicDBObject("_id", new ObjectId("541427d67be92ba24260833c"));
//    System.out.println(query.toString());
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.object("_id", new ObjectId("541427d67be92ba24260833c"));
    assertEquals(person.toString(), builder.build().toString());
  }
  
  @Override
  protected JsonAdapter getAdapter() {
    return new MongoDbAdapter();
  }
}