package org.jsonbuilder.implementation.mongodb;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.BooleanNode;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.NumberNode;
import org.jsonbuilder.interfaces.ObjectNode;
import org.jsonbuilder.interfaces.StringNode;

public class MongoDbAdapter implements JsonAdapter {

  @Override
  public ObjectNode getObjectNode() {
    return new MongoDbObjectNode();
  }

  @Override
  public ArrayNode getArrayNode() {
    return new MongoDbArrayNode();
  }

  @Override
  public NullNode getNullNode() {
    return new MongoDbNullNode();
  }

  @Override
  public NumberNode getNumberNode(Number number) {
    return new MongoDbNumberNode(number);
  }

  @Override
  public StringNode getStringNode(String value) {
    return new MongoDbStringNode(value);
  }

  @Override
  public BooleanNode getBooleanNode(Boolean value) {
    return new MongoDbBooleanNode(value);
  }
}