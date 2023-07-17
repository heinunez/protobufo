package com.heinunez.protobufo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.protobuf.InvalidProtocolBufferException;
import com.heinunez.protobufo.protos.AddressBook;
import com.heinunez.protobufo.protos.Person;
import com.heinunez.protobufo.protos.Person.PhoneNumber;
import com.heinunez.protobufo.protos.Person.PhoneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuperProtoParserTest {

  private SuperProtoParser parser;

  @BeforeEach
  void beforeEach() {
    parser = new SuperProtoParser();
  }

  @Test
  void personTest() throws InvalidProtocolBufferException {
    var json = "{\"email\":\"heiner.nunez@mail.pe\",\"name\":\"heiner\",\"id\":1}";

    var builder = Person.newBuilder();
    parser.parse(json, builder);

    var person = builder.build();
    assertEquals("heiner.nunez@mail.pe", person.getEmail());
    assertEquals("heiner", person.getName());
    assertEquals(1, person.getId());
  }

  @Test
  void phoneNumberTest() throws InvalidProtocolBufferException {
    var json = "{\"number\":\"123456\",\"type\":\"MOBILE\"}";

    var builder = PhoneNumber.newBuilder();
    parser.parse(json, builder);

    var phoneNumber = builder.build();
    assertEquals("123456", phoneNumber.getNumber());
    assertEquals(PhoneType.MOBILE, phoneNumber.getType());
  }

  @Test
  void addressBookTest() throws InvalidProtocolBufferException {
    var json = "{\"people\":[{\"email\":\"heiner.nunez@mail.pe\",\"name\":\"heiner\",\"id\":1}]}";

    var builder = AddressBook.newBuilder();
    parser.parse(json, builder);

    var addressBook = builder.build();
    assertEquals(1, addressBook.getPeopleCount());
    assertNotNull(addressBook.getPeople(0));
  }

}