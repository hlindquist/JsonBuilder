package org.jsonbuilder.implementation.mongodb;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.ObjectNode;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class MongoDbObjectNode implements ObjectNode {
  
  private final BasicDBObject basicDbObject;
  
  public MongoDbObjectNode(){
    basicDbObject = new BasicDBObject();
  }

  @Override
  public Object getNative() {
    return basicDbObject;
  }

  @Override
  public void addProperty(String name, String value) {
    basicDbObject.append(name, value);
  }

  @Override
  public void addProperty(String name, Number value) {
    basicDbObject.append(name, value);
  }

  @Override
  public void addProperty(String name, Boolean value) {
    basicDbObject.append(name, value);
  }
  
  @Override
  public void addProperty(String name, Object value) {
    basicDbObject.append(name, value);
  }

  @Override
  public void add(String name, ArrayNode node) {
    basicDbObject.append(name, node.getNative());
  }

  @Override
  public void add(String name, ObjectNode node) {
    basicDbObject.append(name, node.getNative());
  }
  
  public String toString() {
    return basicDbObject.toString();
  }
}