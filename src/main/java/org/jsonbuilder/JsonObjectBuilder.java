package org.jsonbuilder;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jsonbuilder.interfaces.Builder;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.ObjectNode;

public class JsonObjectBuilder implements Builder {
  
  private final LinkedList<JsonNode> stack;
  private final JsonArrayBuilder parent;
  private final Map<String, JsonArrayBuilder> arrayBuilders;
  private final JsonAdapter factory;
  
  private JsonObjectBuilder(JsonArrayBuilder parent, JsonAdapter adapter) {
    this.factory = adapter;
    stack = new LinkedList<JsonNode>();
    arrayBuilders = new HashMap<String, JsonArrayBuilder>();
    this.parent = parent;
  }
  
  public static JsonObjectBuilder create(JsonAdapter factory) {
    return create(null, factory);
  }
  
  public static JsonObjectBuilder create(JsonArrayBuilder builder, JsonAdapter factory) {
    return new JsonObjectBuilder(builder, factory);
  }
  
  public JsonObjectBuilder object(String name, Object value) {
    this.getCurrent().addProperty(name, value);
    return this;
  }
  
  public JsonObjectBuilder object(String name, String value) {
    this.getCurrent().addProperty(name, value);
    return this;
  }
  
  public JsonObjectBuilder object(String name, Number number) {
    this.getCurrent().addProperty(name, number);
    return this;
  }
  
  public JsonObjectBuilder object(String name, Boolean value) {
    this.getCurrent().addProperty(name, value);
    return this;
  }
  
  public JsonArrayBuilder array(String name) {
    JsonArrayBuilder builder;
    if(arrayBuilders.containsKey(name)) {
      builder = arrayBuilders.get(name);
    } else {
      builder = JsonArrayBuilder.create(this, factory);
    }
    this.getCurrent().add(name, builder.build());
    arrayBuilders.put(name, builder);
    return builder;
  }
  
  public JsonObjectBuilder object(String name) {
    ObjectNode object = factory.getObjectNode();
    this.getCurrent().add(name, object);
    this.setCurrent(object);
    return this;
  }

  public JsonObjectBuilder up() {
    if(!stack.isEmpty()) {
      stack.removeFirst();
    }
    return this;
  }
  
  public JsonArrayBuilder upArray() {
    return parent;
  }
  
  public ObjectNode build() {
    if(stack.isEmpty()) {
      stack.add(factory.getObjectNode());
    }
    return (ObjectNode) stack.getLast(); 
  }
  
  private ObjectNode getCurrent() {
    if(stack.isEmpty()) {
      this.setCurrent(factory.getObjectNode());
    }
    return (ObjectNode) stack.getFirst();
  }
  
  private void setCurrent(ObjectNode object) {
    stack.addFirst(object);
  }
}