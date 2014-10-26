package org.jsonbuilder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import org.jsonbuilder.interfaces.JsonAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public abstract class JsonBuilderTest {
  
  protected abstract JsonAdapter getAdapter();
  
  @Test
  public void shouldAllowAllJsonValuesInArray() {
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
    String nill = null;
    Object json = new JsonBuilder(this.getAdapter()).
      array("string", "stringtwo").
      array(5, 223).
      array(true, false).
      array(nill, nill).
      array().array(4,6,7).up().
      object("object", 7).build();
    if(json instanceof ArrayList) {
      assertEquals(allValues.toString(), new Gson().toJson(json));
    } else {
      assertEquals(allValues.toString(), json.toString());
    }
  }
  
  @Test
  public void shouldAllowMultilevelArraysWithObjects() {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("[").
      append("4,6,[10,{\"last\":true}]").
      append("]");
    Object json = new JsonBuilder(this.getAdapter()).
        array(4,6).
        array().array(10).
        object("last", true).build();
    if(json instanceof ArrayList) {
      assertEquals(multiLevel.toString(), new Gson().toJson(json));
    } else {
      assertEquals(multiLevel.toString(), json.toString());
    }
  }
  
  @Test
  public void shouldAllowMultiStringValueObjects() {
    StringBuilder multiValue = new StringBuilder();
    multiValue.append("{").
      append("\"first\":\"one\",").
      append("\"second\":\"two\",").
      append("\"third\":3,").
      append("\"fourth\":null").
      append("}");
    Object nill = null;
    Object json = new JsonBuilder(this.getAdapter()).
        object("first", "one").
        object("second", "two").
        object("third", 3).
        object("fourth", nill).build();
    if(json instanceof DBObject) {
      String serialized = JSON.serialize(json);
      assertEquals(multiValue.toString(), new JsonParser().parse(serialized).toString());
    } else {
      assertEquals(multiValue.toString(), json.toString());
    }
  }
  
  @Test
  public void shouldAllowMulitLevelObjects() {
    StringBuilder multiLevel = new StringBuilder();
    multiLevel.append("{").
      append("\"first\":{\"second\":{\"third\":\"ok\"}}").
      append("}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first").
          object("second").
            object("third", "ok").build();
    if(json instanceof DBObject) {
      String serialized = JSON.serialize(json);
      assertEquals(multiLevel.toString(), new JsonParser().parse(serialized).toString());
    } else {
      assertEquals(multiLevel.toString(), json.toString());
    }
  }
  
  @Test
  public void shouldNotAddAnythingYetIfOnlyKeyIsSpecified() {
    StringBuilder none = new StringBuilder();
    none.append("{}");
    Object json = new JsonBuilder(this.getAdapter()).
        object("first").build();
    if(json instanceof DBObject) {
      String serialized = JSON.serialize(json);
      assertEquals(none.toString(), new JsonParser().parse(serialized).toString());
    } else {
      assertEquals(none.toString(), json.toString());
    }
  }
  
  @Test
  public void shouldAllowArrayWithinObjectOfObject() {
    StringBuilder arrayInObject = new StringBuilder();
    arrayInObject.append("{").
      append("\"outer\":{\"inner\":[\"one\",2,null]}").
      append("}");
    Object nill = null;
    Object json = new JsonBuilder(this.getAdapter()).
        object("outer").
        object("inner").
        array("one").
        array(2).
        array(nill).build();
    if(json instanceof DBObject) {
      String serialized = JSON.serialize(json);
      assertEquals(arrayInObject.toString(), new JsonParser().parse(serialized).toString());
    } else {
      assertEquals(arrayInObject.toString(), json.toString());
    }
  }
}