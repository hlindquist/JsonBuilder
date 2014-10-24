package org.jsonbuilder.interfaces;

public interface JsonAdapter {
  
  public ObjectNode getObjectNode();
  public ArrayNode getArrayNode();
  public NullNode getNullNode();
  public NumberNode getNumberNode(Number number);
  public StringNode getStringNode(String value);
  public BooleanNode getBooleanNode(Boolean value);
}