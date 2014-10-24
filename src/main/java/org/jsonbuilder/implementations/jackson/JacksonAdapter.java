package org.jsonbuilder.implementations.jackson;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.BooleanNode;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.NumberNode;
import org.jsonbuilder.interfaces.ObjectNode;
import org.jsonbuilder.interfaces.StringNode;

public class JacksonAdapter implements JsonAdapter {
  
  @Override
  public ObjectNode getObjectNode() {
    return new JacksonObjectNode();
  }

  @Override
  public ArrayNode getArrayNode() {
    return new JacksonArrayNode();
  }

  @Override
  public NullNode getNullNode() {
    return new JacksonNullNode();
  }

  @Override
  public NumberNode getNumberNode(Number number) {
    return new JacksonNumberNode(number);
  }

  @Override
  public StringNode getStringNode(String value) {
    return new JacksonStringNode(value);
  }

  @Override
  public BooleanNode getBooleanNode(Boolean value) {
    return new JacksonBooleanNode(value);
  }
}