package org.jsonbuilder.implementation.gson;

import org.jsonbuilder.JsonBuilderTest;
import org.jsonbuilder.interfaces.JsonAdapter;

public class JsonBuilderGsonTest extends JsonBuilderTest {

  @Override
  protected JsonAdapter getAdapter() {
    return new GsonAdapter();
  }
}