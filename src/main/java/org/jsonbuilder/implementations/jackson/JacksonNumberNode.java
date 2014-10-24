package org.jsonbuilder.implementations.jackson;

import org.jsonbuilder.interfaces.NumberNode;

import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class JacksonNumberNode implements NumberNode {
  
  private final ValueNode value;
  
  public JacksonNumberNode(Number number) {
    value = new LongNode((Integer) number);
  }

  @Override
  public Object getNative() {
    return value;
  }
  
}