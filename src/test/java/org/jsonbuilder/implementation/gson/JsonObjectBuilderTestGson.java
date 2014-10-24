package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.JsonObjectBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonObjectBuilderTestGson extends JsonObjectBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new GsonAdapter();
  }
  
}