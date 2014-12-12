package com.test.morphia.server;

import com.google.code.morphia.Morphia;

public class MorphiaFactory {

  private static Morphia morphia = null;

  public static Morphia getMorphia() {
    if (morphia == null)
      morphia = new Morphia();

    return morphia;
  }
}
