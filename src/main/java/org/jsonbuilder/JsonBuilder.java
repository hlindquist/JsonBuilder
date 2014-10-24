package org.jsonbuilder;

import java.util.HashMap;
import java.util.LinkedList;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.Builder;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.JsonBuilderInterface;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.ObjectNode;

public class JsonBuilder implements JsonBuilderInterface {
  
  private final LinkedList<JsonNode> stack = new LinkedList<JsonNode>();
  private final JsonAdapter adapter;
  
  public JsonBuilder(JsonAdapter adapter) {
    this.adapter = adapter;
  }
  
  public JsonBuilder array() {
    JsonNode emptyArray = adapter.getArrayNode();
    if(stack.isEmpty()) {
      stack.add(emptyArray);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(emptyArray);
      stack.add(emptyArray);
    }
    return this;
  }
  
  public JsonBuilder array(String ... strings) {
    this.insertIntoArray(strings);
    return this;
  }
  
  public JsonBuilder array(Number ... numbers) {
    this.insertIntoArray(numbers);
    return this;
  }
  
  public JsonBuilder array(Boolean ... booleans) {
    this.insertIntoArray(booleans);
    return this;
  }
  
  public JsonBuilder object(String name, String value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  public JsonBuilder object(String name, Number value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  public JsonBuilder object(String name, Boolean value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  public JsonBuilder object(String name, Object value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  public JsonBuilder object(String name) {
    ObjectNode objectNode = adapter.getObjectNode();
    if(stack.isEmpty()) {
      ObjectNode emptyNode = adapter.getObjectNode();
      objectNode.add(name, emptyNode);
      stack.add(objectNode);
      stack.add(emptyNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).add(name, objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  public JsonBuilder up() {
    stack.removeLast();
    return this;
  }
  
  public Object build() {
    if(stack.isEmpty()) {
      stack.add(adapter.getObjectNode());
    }
    return stack.getFirst().getNative(); 
  }
  
  private void insertIntoArray(Object... values) {
    if(stack.isEmpty()) {
      stack.add(adapter.getArrayNode());
    }
    Object currentNode = stack.getLast();
    if(currentNode instanceof ArrayNode) {
      for(Object value : values) {
        if(value == null) {
          ((ArrayNode) currentNode).add(adapter.getNullNode());
        } else if(value instanceof String) {
          ((ArrayNode) currentNode).add(adapter.getStringNode((String) value));
        } else if(value instanceof Number) {
          ((ArrayNode) currentNode).add(adapter.getNumberNode((Number) value));
        } else if(value instanceof Boolean) {
          ((ArrayNode) currentNode).add(adapter.getBooleanNode((Boolean) value));
        }
      }
    }
  }
}