package de.turing85;

import java.util.Random;
import java.util.UUID;

import lombok.Value;

@Value
public class LargeObject {
  private static final Random RANDOM = new Random();

  byte[] data;
  String fieldOne;
  String fieldTwo;

  public LargeObject() {
    this.data = new byte[100 * 1024 * 1024]; // 100 MB
    RANDOM.nextBytes(data);
    fieldOne = UUID.randomUUID().toString();
    fieldTwo = UUID.randomUUID().toString();
  }
}
