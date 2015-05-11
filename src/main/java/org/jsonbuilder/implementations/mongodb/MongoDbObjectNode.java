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

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.NullNode;
import org.jsonbuilder.interfaces.ObjectNode;

import com.mongodb.BasicDBObject;

/**
 * @author Håkon Lindquist
 */
public class MongoDbObjectNode implements ObjectNode {

  private final BasicDBObject basicDbObject;

  public MongoDbObjectNode() {
    basicDbObject = new BasicDBObject();
  }

  @Override
  public Object getNative() {
    return basicDbObject;
  }

  @Override
  public ObjectNode addProperty(String name, String value) {
    basicDbObject.append(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Number value) {
    basicDbObject.append(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Boolean value) {
    basicDbObject.append(name, value);
    return this;
  }

  @Override
  public ObjectNode addProperty(String name, Object value) {
    basicDbObject.append(name, value);
    return this;
  }

  @Override
  public ObjectNode add(String name, ArrayNode node) {
    basicDbObject.append(name, node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, ObjectNode node) {
    basicDbObject.append(name, node.getNative());
    return this;
  }

  @Override
  public ObjectNode add(String name, NullNode node) {
    basicDbObject.append(name, node.getNative());
    return this;
  }

  public String toString() {
    return basicDbObject.toString();
  }
}