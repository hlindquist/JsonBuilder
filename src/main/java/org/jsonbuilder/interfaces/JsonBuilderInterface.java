package org.jsonbuilder.interfaces;

import org.jsonbuilder.JsonBuilder;

public interface JsonBuilderInterface {
  
  //new
  public JsonBuilder array(Number ... numbers);
  public JsonBuilder array(String ... values);
  public JsonBuilder array(Boolean ... booleans);
  public JsonBuilder object(String name, String value);
  public JsonBuilder object(String name, Number value);
  public JsonBuilder object(String name, Boolean value);
  public JsonBuilder object(String name, Object value);
  public JsonBuilder object(String name);
  public JsonBuilder up();
  public Object build();
  
//  //array
//  public JsonObjectBuilder object();
//  public JsonArrayBuilder object(String name, String value);
//  public JsonArrayBuilder object(String name, Number value);
//  public JsonArrayBuilder object(String name, Boolean value);
//  public JsonArrayBuilder object(String name, Object value);
//  public JsonArrayBuilder values(Number ... numbers);
//  public JsonArrayBuilder values(String ... values);
//  public JsonArrayBuilder values(Boolean ... booleans);
//  public JsonArrayBuilder array();
//  public JsonObjectBuilder up();
//  public JsonArrayBuilder upArray();
//  public ArrayNode build();
//  
//  //object
//  public JsonObjectBuilder object(String name, Object value);
//  public JsonObjectBuilder object(String name, String value);
//  public JsonObjectBuilder object(String name, Number number);
//  public JsonObjectBuilder object(String name, Boolean value);
//  public JsonArrayBuilder array(String name);
//  public JsonObjectBuilder object(String name);
//  public JsonObjectBuilder up();
//  public JsonArrayBuilder upArray();
//  public ObjectNode build();
}