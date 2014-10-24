package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.interfaces.BooleanNode;

import com.google.gson.JsonPrimitive;

public class GsonBooleanNode implements BooleanNode {

  private final JsonPrimitive primitive;
  
  public GsonBooleanNode(Boolean value) {
    primitive = new JsonPrimitive(value);
  }

  @Override
  public Object getNative() {
    return primitive;
  }
}