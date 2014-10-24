package org.jsonbuilder.implementation.jackson;

import org.jsonbuilder.interfaces.StringNode;

import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class JacksonStringNode implements StringNode {
  
  private final ValueNode value;
  
  public JacksonStringNode(String value) {
    this.value = new TextNode(value);
  }

  @Override
  public Object getNative() {
    return value;
  }
}