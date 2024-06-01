package com.mercadolibre.rampup_juan_castano.unit.beans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mercadolibre.rampup_juan_castano.beans.RandomSampleBean;
import com.mercadolibre.rampup_juan_castano.dtos.SampleDTO;
import org.junit.jupiter.api.Test;

class RandomSampleBeanTest {

  @Test
  void randomPositiveTestOK() {
    RandomSampleBean randomSample = new RandomSampleBean();

    SampleDTO sample = randomSample.random();

    assertTrue(sample.getRandom() >= 0);
  }
}
