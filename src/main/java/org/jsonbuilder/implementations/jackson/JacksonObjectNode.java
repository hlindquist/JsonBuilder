/*
 * Copyright (C) Håkon Lindquist
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jsonbuilder.implementations.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Håkon Lindquist
 */
public class JacksonObjectNode implements org.jsonbuilder.interfaces.ObjectNode {
  
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final ObjectNode objectNode;
  
  public JacksonObjectNode() {
    this.objectNode = objectMapper.createObjectNode();
  }

  @Override
  public void addProperty(String name, String value) {
    this.objectNode.put(name, value);
  }

  @Override
  public void addProperty(String name, Number value) {
    this.objectNode.put(name, (Integer) value);
  }

  @Override
  public void addProperty(String name, Boolean value) {
    this.objectNode.put(name, value);
  }
  
  @Override
  public void addProperty(String name, Object value) {
    if(value != null) {
      this.objectNode.put(name, value.toString());
    } else {
      String nill = null;
      this.objectNode.put(name, nill);
    }
  }

  @Override
  public Object getNative() {
    return this.objectNode;
  }

  @Override
  public void add(String name, org.jsonbuilder.interfaces.ArrayNode node) {
    this.objectNode.put(name, (ArrayNode) node.getNative());
  }

  @Override
  public void add(String name, org.jsonbuilder.interfaces.ObjectNode node) {
    this.objectNode.put(name, (ObjectNode) node.getNative());
  }
  
  @Override
  public void add(String name, org.jsonbuilder.interfaces.NullNode node) {
    this.objectNode.put(name, (NullNode) node.getNative());
  }
  
  @Override
  public String toString() {
    return this.objectNode.toString();
  }
}