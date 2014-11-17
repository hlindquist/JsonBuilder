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

import org.jsonbuilder.JsonBuilder;

/**
 * @author Håkon Lindquist
 */
public interface JsonBuilderInterface {

  public JsonBuilder array();

  public JsonBuilder array(Number... numbers);

  public JsonBuilder array(String... values);

  public JsonBuilder array(Boolean... booleans);

  public JsonBuilder array(Object... objects);

  public JsonBuilder object(String name, String value);

  public JsonBuilder object(String name, Number value);

  public JsonBuilder object(String name, Boolean value);

  public JsonBuilder object(String name, Object value);

  public JsonBuilder object(String name);

  public JsonBuilder object();

  public JsonBuilder up();

  public Object build();
}