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

package org.jsonbuilder.implementation.mongodb;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.jsonbuilder.JsonBuilder;
import org.jsonbuilder.JsonBuilderTest;
import org.jsonbuilder.implementations.mongodb.MongoDbAdapter;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.junit.Test;

import com.google.gson.JsonParser;
import com.mongodb.util.JSON;

/**
 * @author Håkon Lindquist
 */
public class JsonBuilderMongoDbTest extends JsonBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new MongoDbAdapter();
  }
  
  @Test
  public void shouldAllowObjectIdToBeBuildt() {
    StringBuilder person = new StringBuilder();
    person.append("{")
      .append("\"_id\":")
        .append("{\"$oid\":\"541427d67be92ba24260833c\"}")
      .append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("_id", new ObjectId("541427d67be92ba24260833c")).build();
    String serialized = JSON.serialize(json);
    assertEquals(person.toString(), new JsonParser().parse(serialized).toString());
  }
}