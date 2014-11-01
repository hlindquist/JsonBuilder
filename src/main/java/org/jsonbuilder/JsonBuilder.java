package org.jsonbuilder;

import java.util.LinkedList;
import java.util.List;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.JsonBuilderInterface;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.ObjectNode;

public class JsonBuilder implements JsonBuilderInterface {
  
  private final LinkedList<JsonNode> stack = new LinkedList<JsonNode>();
  private final JsonAdapter adapter;
  private final LinkedList<String> suspendedNames = new LinkedList<String>();
  
  public JsonBuilder(JsonAdapter adapter) {
    this.adapter = adapter;
  }
  
  @Override
  public JsonBuilder array() {
    JsonNode emptyArray = adapter.getArrayNode();
    if(!suspendedNames.isEmpty()) {
      buildArraySuspendedBranch();
    } else if(stack.isEmpty()) {
      stack.add(emptyArray);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(emptyArray);
      stack.add(emptyArray);
    }
    return this;
  }
  
  @Override
  public JsonBuilder array(String ... strings) {
    this.insertIntoArray( (Object[]) strings);
    return this;
  }
  
  @Override
  public JsonBuilder array(Number ... numbers) {
    this.insertIntoArray((Object[]) numbers);
    return this;
  }
  
  @Override
  public JsonBuilder array(Boolean ... booleans) {
    this.insertIntoArray((Object[]) booleans);
    return this;
  }
  
  @Override
  public JsonBuilder array(Object ... objects) {
    this.insertIntoArray(objects);
    return this;
  }
  
  @Override
  public JsonBuilder object(String name, String value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  @Override
  public JsonBuilder object(String name, Number value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  @Override
  public JsonBuilder object(String name, Boolean value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  @Override
  public JsonBuilder object(String name, Object value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if(!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  @Override
  public JsonBuilder object(String name) {
    suspendedNames.add(name);
    return this;
  }
  
  @Override
  public JsonBuilder object() {
    ObjectNode objectNode = adapter.getObjectNode();
    if(!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if(stack.isEmpty()) {
      stack.add(objectNode);
    } else if(stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }

  private void buildSuspendedBranch(ObjectNode objectNode) {
    LinkedList<ObjectNode> tempNodes = new LinkedList<ObjectNode>();
    tempNodes.add(objectNode);
    while(!suspendedNames.isEmpty()) {
      String suspendedName = suspendedNames.pollLast(); 
      ObjectNode suspendedObject = adapter.getObjectNode();
      suspendedObject.add(suspendedName, tempNodes.getLast());
      tempNodes.add(suspendedObject);
    }
    while(!tempNodes.isEmpty()) {
      stack.add(tempNodes.pollLast());
    }
  }

  private void addNullValueObject(String name, ObjectNode objectNode) {
    NullNode nullNode = adapter.getNullNode();
    objectNode.add(name, nullNode);
    stack.add(objectNode);
    stack.add(nullNode);
  }
  
  @Override
  public JsonBuilder up() {
    stack.removeLast();
    return this;
  }
  
  @Override
  public Object build() {
    if(stack.isEmpty()) {
      stack.add(adapter.getObjectNode());
    }
    return stack.getFirst().getNative(); 
  }
  
  private void insertIntoArray(Object... values) {
    if(!suspendedNames.isEmpty()) {
      buildArraySuspendedBranch();
    } else if(stack.isEmpty()) {
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

  private void buildArraySuspendedBranch() {
    ArrayNode array = adapter.getArrayNode();
    String suspendedName = suspendedNames.pollLast(); 
    ObjectNode suspendedObject = adapter.getObjectNode();
    suspendedObject.add(suspendedName, array);
    LinkedList<ObjectNode> tempNodes = new LinkedList<ObjectNode>();
    tempNodes.add(suspendedObject);
    while(!suspendedNames.isEmpty()) {
      String suspendedName2 = suspendedNames.pollLast(); 
      ObjectNode suspendedObject2 = adapter.getObjectNode();
      suspendedObject2.add(suspendedName2, tempNodes.getLast());
      tempNodes.add(suspendedObject2);
    }
    while(!tempNodes.isEmpty()) {
      stack.add(tempNodes.pollLast());
    }
    stack.add(array);
  }
}