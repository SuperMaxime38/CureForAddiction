package fr.maxime38.cfa.utils;

import java.util.Random;

public class RandomBetween {
  public static double randomDouble(double min, double max) {
    Random rdm = new Random();
    return rdm.nextDouble() * (max - min) + min;
  }
}
