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

package org.jsonbuilder.implementations.jsonorg;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.ObjectNode;

/**
 * @author Håkon Lindquist
 */
public class JsonOrgObjectNode implements ObjectNode {
  
  private final JSONObject jsonObject;
  
  public JsonOrgObjectNode() {
    this.jsonObject = new JSONObject();
  }

  @Override
  public ObjectNode addProperty(String name, String value) {
    this.jsonObject.put(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Number value) {
    this.jsonObject.put(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Boolean value) {
    this.jsonObject.put(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Object value) {
    this.jsonObject.put(name, value);
    return this;
  }

  @Override
  public ObjectNode add(String name, ArrayNode node) {
    this.jsonObject.put(name, (JSONArray) node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, ObjectNode node) {
    this.jsonObject.put(name, (JSONObject) node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, NullNode node) {
    String nullValue = null;
    this.jsonObject.put(name, nullValue);
    return this;
  }
  
  @Override
  public Object getNative() {
    return this.jsonObject;
  }
}