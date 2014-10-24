package org.jsonbuilder.implementation.mongodb;

import static org.junit.Assert.assertEquals;

import org.jsonbuilder.JsonArrayBuilder;
import org.jsonbuilder.JsonArrayBuilderTest;
import org.jsonbuilder.implementation.jackson.JacksonAdapter;
import org.junit.Test;

public class JsonArrayBuilderTestMongoDb extends JsonArrayBuilderTest {

  @Test
  @Override
  public void shouldAllowAllJsonValuesInArray() {
    StringBuilder allValues = new StringBuilder();
    allValues.append("[")
      .append(" \"string\" ,")
      .append(" 5 ,")
      .append(" { \"object\" : 7} ,")
      .append(" [ 4 , 6 , 7] ,")
      .append(" true ,")
      .append(" false ,")
      .append("  null")
      .append(" ]");
    
    JsonArrayBuilder builder = JsonArrayBuilder.create(this.getAdapter());
    String none = null;
    builder.value("string")
      .value(5)
      .object("object", 7)
      .array().value(4,6,7).upArray()
      .value(true)
      .value(false)
      .value(none);
    assertEquals(allValues.toString(), builder.build().toString());
  }
  
  @Override
  @Test
  public void shouldAllowMultiplesOfAllTypes() {
    StringBuilder allValues = new StringBuilder();
    allValues.append("[")
      .append(" \"one\" ,")
      .append(" \"two\" ,")
      .append(" \"three\" ,")
      .append(" 1 ,")
      .append(" 2 ,")
      .append(" 3 ,")
      .append(" true ,")
      .append(" false ,")
      .append(" true ,")
      .append("  null  ,")
      .append("  null  ,")
      .append("  null")
      .append(" ]");
    
    JsonArrayBuilder builder = JsonArrayBuilder.create(this.getAdapter());
    String none = null;
    builder.value("one", "two", "three")
      .value(1, 2, 3)
      .value(true, false, true)
      .value(none, none, none);
    assertEquals(allValues.toString(), builder.build().toString());
  }
  
  @Override
  protected MongoDbAdapter getAdapter() {
    return new MongoDbAdapter();
  }
}