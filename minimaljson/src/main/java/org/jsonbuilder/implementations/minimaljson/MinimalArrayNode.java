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

package org.jsonbuilder.implementations.minimaljson;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

/**
 * @author Håkon Lindquist
 */
public class MinimalArrayNode implements ArrayNode {
  
  private final JsonArray jsonArray;
  
  public MinimalArrayNode() {
    this.jsonArray = new JsonArray();
  }
  
  @Override
  public ArrayNode add(JsonNode node) {
    Object value = node.getNative();
    if(value instanceof JsonValue) {
      this.jsonArray.add((JsonValue) value);
    } else if(value instanceof Integer) {
      this.jsonArray.add((Integer) value);
    } else if(value instanceof Boolean) {
      this.jsonArray.add((Boolean) value);
    } else if(value instanceof Double) {
      this.jsonArray.add((Double) value);
    } else if(value instanceof Float) {
      this.jsonArray.add((Float) value);
    } else if(value instanceof Long) {
      this.jsonArray.add((Long) value);
    } else if(value instanceof String) {
      this.jsonArray.add((String) value);
    } else if(value == null) {
      this.jsonArray.add((String) value);
    }
    return this;
  }

  @Override
  public Object getNative() {
    return this.jsonArray;
  }
  
  @Override
  public String toString() {
    return this.jsonArray.toString();
  }
}