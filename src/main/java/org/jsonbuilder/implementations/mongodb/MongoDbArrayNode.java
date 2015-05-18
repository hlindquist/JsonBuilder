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

package org.jsonbuilder.implementations.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;

import com.mongodb.util.JSON;

/**
 * @author Håkon Lindquist
 */
public class MongoDbArrayNode implements ArrayNode {

  private final List<Object> list;

  public MongoDbArrayNode() {
    list = new ArrayList<Object>();
  }

  @Override
  public ArrayNode add(JsonNode node) {
    list.add(node.getNative());
    return this;
  }

  @Override
  public Object getNative() {
    return list;
  }

  public String toString() {
    return JSON.serialize(list);
  }
}