package org.jsonbuilder.implementation.jackson;

import org.jsonbuilder.JsonObjectBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonObjectBuilderTestJackson extends JsonObjectBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new JacksonAdapter();
  }
}