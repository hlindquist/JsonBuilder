package org.jsonbuilder.implementation.mongodb;

import org.jsonbuilder.interfaces.BooleanNode;

public class MongoDbBooleanNode implements BooleanNode {
  
  private final Boolean value;
  
  public MongoDbBooleanNode(Boolean value) {
    this.value = value;
  }

  @Override
  public Object getNative() {
    return value;
  }
}