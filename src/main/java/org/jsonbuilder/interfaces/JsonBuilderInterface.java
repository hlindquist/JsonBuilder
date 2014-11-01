package org.jsonbuilder.interfaces;

import org.jsonbuilder.JsonBuilder;

public interface JsonBuilderInterface {
  
  public JsonBuilder array();
  public JsonBuilder array(Number ... numbers);
  public JsonBuilder array(String ... values);
  public JsonBuilder array(Boolean ... booleans);
  public JsonBuilder array(Object ... objects);
  public JsonBuilder object(String name, String value);
  public JsonBuilder object(String name, Number value);
  public JsonBuilder object(String name, Boolean value);
  public JsonBuilder object(String name, Object value);
  public JsonBuilder object(String name);
  public JsonBuilder object();
  public JsonBuilder up();
  public Object build();
}