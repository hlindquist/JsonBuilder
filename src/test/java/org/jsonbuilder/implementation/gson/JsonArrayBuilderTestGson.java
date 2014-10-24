package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.JsonArrayBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonArrayBuilderTestGson extends JsonArrayBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new GsonAdapter();
  }
  
}