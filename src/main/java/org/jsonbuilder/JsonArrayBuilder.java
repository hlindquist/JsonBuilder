package org.jsonbuilder;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.Builder;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.ObjectNode;

public class JsonArrayBuilder implements Builder {
  
  private final ArrayNode array;
  private final Builder parentBuilder;
  private final JsonAdapter adapter;
  
  private JsonArrayBuilder(Builder parentBuilder, JsonAdapter adapter) {
    this.adapter = adapter;
    array = adapter.getArrayNode();
    this.parentBuilder = parentBuilder;
  }
  
  public static JsonArrayBuilder create(JsonAdapter factory) {
    return create(null, factory);
  }
  
  static JsonArrayBuilder create(Builder parentBuilder, JsonAdapter factory) {
    return new JsonArrayBuilder(parentBuilder, factory);
  }
  
  public JsonObjectBuilder object() {
    JsonObjectBuilder builder = JsonObjectBuilder.create(this, this.adapter);
    array.add(builder.build());
    return builder;
  }
  
  public JsonArrayBuilder object(String name, String value) {
    ObjectNode object = this.adapter.getObjectNode();
    object.addProperty(name, value);
    array.add(object);
    return this;
  }
  
  public JsonArrayBuilder object(String name, Number value) {
    ObjectNode object = this.adapter.getObjectNode();
    object.addProperty(name, value);
    array.add(object);
    return this;
  }
  
  public JsonArrayBuilder object(String name, Boolean value) {
    ObjectNode object = this.adapter.getObjectNode();
    object.addProperty(name, value);
    array.add(object);
    return this;
  }
  
  public JsonArrayBuilder object(String name, Object value) {
    ObjectNode object = this.adapter.getObjectNode();
    object.addProperty(name, value);
    array.add(object);
    return this;
  }
  
  public JsonArrayBuilder value(Number ... numbers) {
    for(Number number : numbers) {
      if(number == null) {
        array.add(this.adapter.getNullNode());
      } else {
        array.add(this.adapter.getNumberNode(number));
      }
    }
    return this;
  }
  
  public JsonArrayBuilder value(String ... values) {
    for(String value : values) {
      if(value == null) {
        array.add(this.adapter.getNullNode());
      } else {
        array.add(this.adapter.getStringNode(value));
      }
    }
    return this;
  }
  
  public JsonArrayBuilder value(Boolean ... booleans) {
    for(Boolean bool : booleans) {
      if(bool == null) {
        array.add(this.adapter.getNullNode());
      } else {
        array.add(this.adapter.getBooleanNode(bool));
      }
    }
    return this;
  }
  
  public JsonArrayBuilder array() {
    JsonArrayBuilder builder = JsonArrayBuilder.create(this, adapter);
    array.add(builder.build());
    return builder;
  }
  
  public JsonObjectBuilder up() {
    return (JsonObjectBuilder) this.parentBuilder;
  }
  
  public JsonArrayBuilder upArray() {
    return (JsonArrayBuilder) this.parentBuilder; 
  }
  
  public ArrayNode build() {
    return array;
  }
}