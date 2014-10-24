package org.jsonbuilder.implementations.mongodb;

import org.jsonbuilder.interfaces.StringNode;

public class MongoDbStringNode implements StringNode {

  private final String value;
  
  public MongoDbStringNode(String value) {
    this.value = value;
  }
  
  @Override
  public Object getNative() {
    return value;
  }
  
}