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

package org.jsonbuilder.interfaces;

import org.jsonbuilder.interfaces.ArrayNode;
import org.jsonbuilder.interfaces.JsonNode;
import org.jsonbuilder.interfaces.NullNode;

/**
 * @author Håkon Lindquist
 */
public interface ObjectNode extends JsonNode {
  
  public void addProperty(String name, String value);
  public void addProperty(String name, Number value);
  public void addProperty(String name, Boolean value);
  public void addProperty(String name, Object value);
  public void add(String name, ArrayNode node);
  public void add(String name, ObjectNode node);
  public void add(String name, NullNode node);
}