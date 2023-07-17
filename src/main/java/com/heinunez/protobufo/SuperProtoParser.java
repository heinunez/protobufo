package com.heinunez.protobufo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.JsonFormat;

public class SuperProtoParser {

  public void parse(String json, Builder builder) throws InvalidProtocolBufferException {
    JsonFormat.parser().merge(json, builder);
  }

}
