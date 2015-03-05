/*
 * Copyright (C) Håkon Lindquist
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jsonbuilder;

import java.util.LinkedList;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.JsonBuilderInterface;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.ObjectNode;

/**
 * @author Håkon Lindquist
 */
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
    if (!suspendedNames.isEmpty()) {
      buildSuspendedBranch(emptyArray);
    } else if (stack.isEmpty()) {
      stack.add(emptyArray);
    } else if (stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(emptyArray);
      stack.add(emptyArray);
    }
    return this;
  }

  @Override
  public JsonBuilder array(String... strings) {
    this.insertIntoArray((Object[]) strings);
    return this;
  }

  @Override
  public JsonBuilder array(Number... numbers) {
    this.insertIntoArray((Object[]) numbers);
    return this;
  }

  @Override
  public JsonBuilder array(Boolean... booleans) {
    this.insertIntoArray((Object[]) booleans);
    return this;
  }

  @Override
  public JsonBuilder array(Object... objects) {
    this.insertIntoArray(objects);
    return this;
  }

  @Override
  public JsonBuilder object(String name, String value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if (!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if (stack.isEmpty()) {
      stack.add(objectNode);
    } else if (stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if (stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }

  @Override
  public JsonBuilder object(String name, Number value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if (!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if (stack.isEmpty()) {
      stack.add(objectNode);
    } else if (stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if (stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }

  @Override
  public JsonBuilder object(String name, Boolean value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if (!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if (stack.isEmpty()) {
      stack.add(objectNode);
    } else if (stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if (stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }

  @Override
  public JsonBuilder object(String name, Object value) {
    ObjectNode objectNode = adapter.getObjectNode();
    objectNode.addProperty(name, value);
    if (!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if (stack.isEmpty()) {
      stack.add(objectNode);
    } else if (stack.getLast() instanceof ObjectNode) {
      ((ObjectNode) stack.getLast()).addProperty(name, value);
    } else if (stack.getLast() instanceof ArrayNode) {
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
    if (!suspendedNames.isEmpty()) {
      this.buildSuspendedBranch(objectNode);
    } else if (stack.isEmpty()) {
      stack.add(objectNode);
    } else if (stack.getLast() instanceof ArrayNode) {
      ((ArrayNode) stack.getLast()).add(objectNode);
      stack.add(objectNode);
    }
    return this;
  }
  
  private void buildSuspendedBranch(JsonNode node) {
    LinkedList<JsonNode> tempNodes = new LinkedList<JsonNode>();
    tempNodes.add(node);
    while (suspendedNames.size() > 1) {
      String suspendedName = suspendedNames.pollLast();
      ObjectNode suspendedObject = adapter.getObjectNode();
      Object lastNode = tempNodes.getLast();
      if(lastNode instanceof ArrayNode) {
        suspendedObject.add(suspendedName, (ArrayNode) lastNode);
      } else if(lastNode instanceof ObjectNode) {
        suspendedObject.add(suspendedName, (ObjectNode) lastNode);
      }
      tempNodes.add(suspendedObject);
    }
    if(!tempNodes.isEmpty() && !stack.isEmpty() && stack.getLast() instanceof ObjectNode) {
      Object lastNode = tempNodes.getLast();
      if(lastNode instanceof ArrayNode) {
        ((ObjectNode) stack.getLast()).add(suspendedNames.pollLast(), (ArrayNode) lastNode);
      } else if(lastNode instanceof ObjectNode) {
        ((ObjectNode) stack.getLast()).add(suspendedNames.pollLast(), (ObjectNode) lastNode);
      }
    } else {
      ObjectNode suspendedObject = adapter.getObjectNode();
      Object lastNode = tempNodes.getLast();
      if(lastNode instanceof ArrayNode) {
        suspendedObject.add(suspendedNames.pollLast(), (ArrayNode) lastNode);
      } else if(lastNode instanceof ObjectNode) {
        suspendedObject.add(suspendedNames.pollLast(), (ObjectNode) lastNode);
      }
      stack.add(suspendedObject);
    }
    while (!tempNodes.isEmpty()) {
      stack.add(tempNodes.pollLast());
    }
  }
  
  @Override
  public JsonBuilder root() {
    while(stack.size() > 1) {
      stack.removeLast();
    }
    return this;
  }

  @Override
  public JsonBuilder up() {
    if(stack.size() > 1) {
      stack.removeLast();
    }
    return this;
  }

  @Override
  public Object build() {
    if (stack.isEmpty()) {
      stack.add(adapter.getObjectNode());
    }
    return stack.getFirst().getNative();
  }

  private void insertIntoArray(Object... values) {
    if (!suspendedNames.isEmpty()) {
      buildSuspendedBranch(adapter.getArrayNode());
    } else if (stack.isEmpty()) {
      stack.add(adapter.getArrayNode());
    }
    Object currentNode = stack.getLast();
    if (currentNode instanceof ArrayNode) {
      for (Object value : values) {
        if (value == null) {
          ((ArrayNode) currentNode).add(adapter.getNullNode());
        } else if (value instanceof String) {
          ((ArrayNode) currentNode).add(adapter.getStringNode((String) value));
        } else if (value instanceof Number) {
          ((ArrayNode) currentNode).add(adapter.getNumberNode((Number) value));
        } else if (value instanceof Boolean) {
          ((ArrayNode) currentNode)
              .add(adapter.getBooleanNode((Boolean) value));
        }
      }
    }
  }
}