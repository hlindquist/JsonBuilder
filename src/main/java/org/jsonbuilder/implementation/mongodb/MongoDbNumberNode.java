package org.jsonbuilder.implementation.mongodb;

import org.jsonbuilder.interfaces.NumberNode;

public class MongoDbNumberNode implements NumberNode {
  
  private final Number number;
  
  public MongoDbNumberNode(Number number) {
    this.number = number;
  }

  @Override
  public Object getNative() {
    return number;
  }
}