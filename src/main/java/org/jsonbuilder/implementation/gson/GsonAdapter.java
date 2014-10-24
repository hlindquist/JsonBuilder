package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.BooleanNode;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.NumberNode;
import org.jsonbuilder.interfaces.ObjectNode;
import org.jsonbuilder.interfaces.StringNode;

public class GsonAdapter implements JsonAdapter {
  
  @Override
  public ObjectNode getObjectNode() {
    return new GsonObjectNode();
  }

  @Override
  public ArrayNode getArrayNode() {
    return new GsonArrayNode();
  }

  @Override
  public NullNode getNullNode() {
    return new GsonNullNode();
  }

  @Override
  public NumberNode getNumberNode(Number number) {
    return new GsonNumberNode(number);
  }

  @Override
  public StringNode getStringNode(String value) {
    return new GsonStringNode(value);
  }

  @Override
  public BooleanNode getBooleanNode(Boolean value) {
    return new GsonBooleanNode(value);
  }
  
  
}