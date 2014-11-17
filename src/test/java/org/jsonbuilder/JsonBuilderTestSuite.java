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

package org.jsonbuilder;

import org.jsonbuilder.implementation.gson.JsonBuilderGsonTest;
import org.jsonbuilder.implementation.jackson.JsonBuilderJacksonTest;
import org.jsonbuilder.implementation.mongodb.JsonBuilderMongoDbTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  JsonBuilderGsonTest.class,
  JsonBuilderJacksonTest.class,
  JsonBuilderMongoDbTest.class
})

/**
 * @author Håkon Lindquist
 */
public class JsonBuilderTestSuite {
}