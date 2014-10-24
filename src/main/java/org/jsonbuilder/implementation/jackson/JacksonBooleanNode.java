package org.jsonbuilder.implementation.jackson;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ValueNode;

public class JacksonBooleanNode implements org.jsonbuilder.interfaces.BooleanNode {

  private final ValueNode value;
  
  public JacksonBooleanNode(Boolean value) {
    if(value) {
      this.value = BooleanNode.TRUE;
    } else {
      this.value = BooleanNode.FALSE;
    }
  }

  @Override
  public Object getNative() {
    return value;
  }
}