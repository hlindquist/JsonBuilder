package org.jsonbuilder.implementation.jackson;

import org.jsonbuilder.JsonArrayBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonArrayBuilderTestJackson extends JsonArrayBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new JacksonAdapter();
  }
  
}