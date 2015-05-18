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
package org.jsonbuilder.implementations.jsonsimple;

import org.json.simple.JSONArray;
import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;

/**
 * @author Håkon Lindquist
 */
public class SimpleArrayNode implements ArrayNode {
  
  private final JSONArray jsonArray;
  
  public SimpleArrayNode() {
    this.jsonArray = new JSONArray();
  }

  @Override
  public void add(JsonNode node) {
    this.jsonArray.add(node.getNative());
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