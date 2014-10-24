package org.jsonbuilder.implementations.gson;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class GsonArrayNode implements ArrayNode {
  
  private final JsonArray jsonArray;
  
  public GsonArrayNode() {
    this.jsonArray = new JsonArray();
  }

  @Override
  public void add(JsonNode node) {
    jsonArray.add((JsonElement) node.getNative());
  }

  @Override
  public Object getNative() {
    return this.jsonArray;
  }
  
  @Override
  public String toString() {
    return jsonArray.toString();
  }
}