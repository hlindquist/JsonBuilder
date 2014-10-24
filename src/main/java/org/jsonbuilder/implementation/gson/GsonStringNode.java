package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.interfaces.StringNode;

import com.google.gson.JsonPrimitive;

public class GsonStringNode implements StringNode {
  
  private final JsonPrimitive primitive;
  
  public GsonStringNode(String value) {
    primitive = new JsonPrimitive(value);
  }

  @Override
  public Object getNative() {
    return primitive;
  }
}