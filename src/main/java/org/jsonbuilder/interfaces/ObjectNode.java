package org.jsonbuilder.interfaces;

public interface ObjectNode extends JsonNode {
  
  public void addProperty(String name, String value);
  public void addProperty(String name, Number value);
  public void addProperty(String name, Boolean value);
  public void addProperty(String name, Object value);
  public void add(String name, ArrayNode node);
  public void add(String name, ObjectNode node);
}