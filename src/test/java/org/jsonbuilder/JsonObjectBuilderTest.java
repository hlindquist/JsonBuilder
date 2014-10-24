package org.jsonbuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.jsonbuilder.interfaces.JsonAdapter;

public abstract class JsonObjectBuilderTest {
  
  protected abstract JsonAdapter getAdapter();
  
  @Test
  public void shouldCreateSimpleRoot() {
    String simpleObject = "{\"simple\":\"object\"}";
    JsonObjectBuilder builder = JsonObjectBuilder.create(this.getAdapter());
    builder.object("simple", "object");
    assertEquals(simpleObject, builder.build().toString());
  }
  
  @Test
  public void shouldAllowAllJsonValuesInObject() {
    StringBuilder allTypes = new StringBuilder();
    allTypes.append("{")
      .append("\"string\":\"value\",")
      .append("\"number\":5,")
      .append("\"object\":{\"in\":\"side\"},")
      .append("\"array\":[1,4,6],")
      .append("\"true\":true,")
      .append("\"false\":false,")
      .append("\"null\":null")
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
  public void shouldEasilyBuildObjectsWithinArrayWithinObject() {
    StringBuilder shoppingCart = new StringBuilder();
    shoppingCart.append("{")
      .append("\"cartItems\":")
        .append("[")
          .append("{\"name\":\"bicycle\",\"price\":450},")
          .append("{\"name\":\"lego\",\"price\":972}")
        .append("],")
      .append("\"total\":1432")
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
  public void shouldElegantlyAddValuesToArrayFromLoop() {
    StringBuilder contacts = new StringBuilder();
    contacts.append("{")
        .append("\"title\":\"My Contacts\",")
        .append("\"contacts\":")
          .append("[")
            .append("\"Messi\",")
            .append("\"Xavi\",")
            .append("\"Inesita\"")
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
}