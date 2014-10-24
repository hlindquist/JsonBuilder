package org.jsonbuilder.implementation.jackson;

import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class JacksonNullNode implements org.jsonbuilder.interfaces.NullNode {
  
  private final ValueNode value;
  
  public JacksonNullNode() {
    value = NullNode.instance;
  }

  @Override
  public Object getNative() {
    return value;
  }
}