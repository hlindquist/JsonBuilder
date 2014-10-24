package org.jsonbuilder.implementation.jackson;

import org.jsonbuilder.interfaces.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BaseJsonNode;

public class JacksonArrayNode implements org.jsonbuilder.interfaces.ArrayNode {
  
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final ArrayNode arrayNode;
  
  public JacksonArrayNode() {
    this.arrayNode = objectMapper.createArrayNode();
  }

  @Override
  public void add(JsonNode node) {
    arrayNode.add((BaseJsonNode) node.getNative());
  }

  @Override
  public Object getNative() {
    return this.arrayNode;
  }
  
  @Override
  public String toString() {
    return this.arrayNode.toString();
  }
}