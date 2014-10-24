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

public class JsonBuilderTestSuite {
}