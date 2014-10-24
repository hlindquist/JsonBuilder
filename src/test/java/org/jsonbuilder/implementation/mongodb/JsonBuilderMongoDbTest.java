package org.jsonbuilder.implementation.mongodb;

import org.jsonbuilder.JsonBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonBuilderMongoDbTest extends JsonBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new MongoDbAdapter();
  }
}