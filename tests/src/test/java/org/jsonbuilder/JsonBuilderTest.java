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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.jsonbuilder.interfaces.JsonAdapter;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Håkon Lindquist
 */
public abstract class JsonBuilderTest {
  
  protected abstract JsonAdapter getAdapter();
  
  @Test
  public void upShouldNeverGoThroughTheFloor() throws ParseException, JSONException {
    String allValues = "{\"string\":\"hello\",\"integer\":5}";
    Object json = new JsonBuilder(this.getAdapter()).
        object("string", "hello").up().up().up().up().
        object("integer", 5).build();
    JSONAssert.assertEquals(allValues, json.toString(), false);
  }
  
  @Test
  public void shouldBeAbleToGoStraightToRoot() throws ParseException, JSONException {
    String allValues = "{\"string\":{\"level1\":{\"level2\":\"hello\"}},\"integer\":5}";
    Object json = new JsonBuilder(this.getAdapter()).
        object("string").object("level1").object("level2", "hello").root().
        object("integer", 5).build();
    JSONAssert.assertEquals(allValues, json.toString(), false);
  }
  
  @Test
  public void shouldAllowAllJsonValuesInArray() throws JSONException {
    StringBuilder allValues = new StringBuilder();
    allValues.append("[")
      .append("\"string\",")
      .append("\"stringtwo\",")
      .append("5,")
      .append("223,")
      .append("true,")
      .append("false,")
      .append("null,")
      .append("null,")
      .append("[4,6,7],")
      .append("{\"object\":7}")
      .append("]");
    Object json = new JsonBuilder(this.getAdapter()).
      array("string", "stringtwo").
      array(5, 223).
      array(true, false).
      array((String) null, (String) null).
      array().array(4,6,7).up().
      object("object", 7).build();
    JSONAssert.assertEquals(allValues.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMultilevelArraysWithObjects() throws JSONException {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("[").
      append("4,6,[10,{\"last\":true}]").
      append("]");
    Object json = new JsonBuilder(this.getAdapter()).
        array(4,6).
        array().array(10).
        object("last", true).build();
    JSONAssert.assertEquals(multiLevel.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMultiStringValueObjects() throws ParseException, net.minidev.json.parser.ParseException, JSONException {
    StringBuilder multiValue = new StringBuilder();
    multiValue.append("{").
      append("\"first\":\"one\",").
      append("\"second\":\"two\",").
      append("\"third\":3,").
      append("\"fourth\":null").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first", "one").
        object("second", "two").
        object("third", 3).
        object("fourth", (String) null).build();
    JSONAssert.assertEquals(multiValue.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMulitLevelObjectsOnInstantiatedTree() throws ParseException, JSONException {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("{").
      append("\"sibling\":\"first\",").
      append("\"first\":{\"second\":{\"third\":\"ok\",\"fourth\":\"sibling\"}}").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("sibling", "first").
        object("first").
          object("second").
            object("third", "ok").
            object("fourth", "sibling").build();
    JSONAssert.assertEquals(multiLevel.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMulitLevelObjectsOnNullTree() throws ParseException, JSONException {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("{").
      append("\"first\":{\"second\":{\"third\":\"ok\",\"fourth\":\"sibling\"}}").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first").
          object("second").
            object("third", "ok").
            object("fourth", "sibling").build();
    JSONAssert.assertEquals(multiLevel.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMulitLevelObjectsWithinArray() throws ParseException, JSONException {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("{").
      append("\"first\":[{\"second\":{\"third\":{\"fourth\":\"ok\",\"fifth\":\"sibling\"}}}]").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first").array().
          object("second").
            object("third").
              object("fourth", "ok").
              object("fifth", "sibling").build();
    JSONAssert.assertEquals(multiLevel.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowMulitLevelArrayObjectOnInstantiatedTree() throws ParseException, JSONException {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("{").
      append("\"sibling\":\"first\",").
      append("\"first\":{\"second\":[\"one\",\"two\"]}").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("sibling", "first").
        object("first").
          object("second").
            array("one", "two").build();
    JSONAssert.assertEquals(multiLevel.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldNotAddAnythingYetIfOnlyKeyIsSpecified() throws ParseException, JSONException {
    StringBuilder none = new StringBuilder();
    none.append("{}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first").build();
    JSONAssert.assertEquals(none.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowArrayWithinObjectOfObject() throws ParseException, JSONException {
    StringBuilder arrayInObject = new StringBuilder();
    arrayInObject.append("{").
      append("\"outer\":{\"inner\":[\"one\",2,null]}").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("outer").
        object("inner").
        array("one").
        array(2).
        array((String) null).build();
    JSONAssert.assertEquals(arrayInObject.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldAllowArrayValueWithLoopPopulationOfMultiValueObjects() throws ParseException, JSONException {
    StringBuilder allValues = new StringBuilder();
    allValues.append("{\"first\":\"sibling\",\"root\":{\"array\":[").
      append("{\"object\":\"hey\",\"name\":\"ok\"},").
      append("{\"object\":\"hello\",\"name\":\"okk\"},").
      append("{\"object\":\"welcome\",\"name\":\"okkk\"}").
      append("]}}");
    JsonBuilder builder = new JsonBuilder(this.getAdapter()).
        object("first", "sibling").
        object("root").object("array").array();
    List<Map> list = new ArrayList<Map>();
    Map objects1 = new LinkedHashMap();
    Map objects2 = new LinkedHashMap();
    Map objects3 = new LinkedHashMap();
    objects1.put("object", "hey");
    objects1.put("name", "ok");
    objects2.put("object", "hello");
    objects2.put("name", "okk");
    objects3.put("object", "welcome");
    objects3.put("name", "okkk");
    list.add(objects1);
    list.add(objects2);
    list.add(objects3);
    for(Map<String, Object> objects : list) {
      builder.object();
      for(String key : objects.keySet()) {
        builder.object(key, objects.get(key));
      }
      builder.up();
    }
    Object json = builder.build();
    JSONAssert.assertEquals(allValues.toString(), json.toString(), false);
  }
  
  @Test
  public void shouldBePossibleToInsertIntoArrayWithLoop() throws ParseException, JSONException {
    StringBuilder allValues = new StringBuilder();
    allValues.append("{\"array\":[").
      append("\"string1\",\"string2\",\"string3\"").
      append("]}");
    JsonBuilder builder = new JsonBuilder(this.getAdapter()).
      object("array");
    String[] values = {"string1", "string2", "string3"};
    for(String value : values) {
      builder.array(value);
    }
    Object json = builder.build();
    JSONAssert.assertEquals(allValues.toString(), json.toString(), false);
  }
}