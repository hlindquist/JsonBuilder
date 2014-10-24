package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.interfaces.NullNode;

import com.google.gson.JsonNull;

public class GsonNullNode implements NullNode {
  
  private final JsonNull jsonNull;
  
  public GsonNullNode() {
    this.jsonNull = JsonNull.INSTANCE;
  }

  @Override
  public Object getNative() {
    return jsonNull;
  }
}