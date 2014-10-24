package org.jsonbuilder.implementations.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;

import com.mongodb.util.JSON;

public class MongoDbArrayNode implements ArrayNode {
  
  private final List<Object> list;

  public MongoDbArrayNode() {
    list = new ArrayList<Object>();
  }

  @Override
  public void add(JsonNode node) {
    list.add(node.getNative());
  }
  
  @Override
  public Object getNative() {
    return list;
  }
  
  public String toString() {
    return JSON.serialize(list);
  }
}