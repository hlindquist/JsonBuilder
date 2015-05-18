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
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.ObjectNode;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

/**
 * @author Håkon Lindquist
 */
public class MinimalObjectNode implements ObjectNode {
  
  private final JsonObject jsonObject;

  public MinimalObjectNode() {
    this.jsonObject = new JsonObject();
  }
  
  @Override
  public ObjectNode addProperty(String name, String value) {
    this.jsonObject.add(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Number value) {
    if(value instanceof Double) {
      this.jsonObject.add(name, (Double) value);
    } else if(value instanceof Integer) {
      this.jsonObject.add(name, (Integer) value);
    } else if(value instanceof Float) {
      this.jsonObject.add(name, (Float) value);
    } else if(value instanceof Long) {
      this.jsonObject.add(name, (Long) value);
    }
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Boolean value) {
    this.jsonObject.add(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Object value) {
    if (value != null) {
      this.jsonObject.add(name, value.toString());
    } else {
      String nill = null;
      this.jsonObject.add(name, nill);
    }
    return this;
  }

  @Override
  public ObjectNode add(String name, ArrayNode node) {
    this.jsonObject.add(name, (JsonArray) node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, ObjectNode node) {
    this.jsonObject.add(name, (JsonObject) node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, NullNode node) {
    String nullValue = null;
    this.jsonObject.add(name, nullValue);
    return this;
  }
  
  @Override
  public Object getNative() {
    return this.jsonObject;
  }
}