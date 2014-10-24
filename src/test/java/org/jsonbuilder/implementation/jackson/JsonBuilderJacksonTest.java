package org.jsonbuilder.implementation.jackson;

import org.jsonbuilder.JsonBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonBuilderJacksonTest extends JsonBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new JacksonAdapter();
  }
}