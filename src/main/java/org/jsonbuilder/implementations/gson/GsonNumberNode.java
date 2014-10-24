package org.jsonbuilder.implementations.gson;

import org.jsonbuilder.interfaces.NumberNode;

import com.google.gson.JsonPrimitive;

public class GsonNumberNode implements NumberNode {
  
  private final JsonPrimitive primitive;
  
  public GsonNumberNode(Number number) {
    primitive = new JsonPrimitive(number);
  }

  @Override
  public Object getNative() {
    return primitive;
  }
  
}