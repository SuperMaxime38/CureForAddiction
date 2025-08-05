package fr.maxime38.cfa.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VoiceLines {
  public static String getStrikeLine() {
    List<String> lines = Arrays.asList(new String[] { "Did you know that you had §a1/1000 000 §fchance to be hitten by a lightning strike ?\nWhat a coincidence !", "You're unlucky... who could have imagined that you'd be hitten by a lighting strike", 
          "Was the sky rainy ? Well it doesn't matter after all...", "Enjoy the life of a Tesla coil", "Standing under a tree during a thunder is highly discouraged" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getSheepLine() {
    List<String> lines = Arrays.asList(new String[] { "This sheep give you a wool block to make you stop hurting him", "The sheep was scared and your item was stuck in his fur, so your item dropped", 
          "Why this wool piece is colored?", "Bêêêh", "I hope you weren't holding an end rod ?!" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getCowLine() {
    List<String> lines = Arrays.asList(new String[] { "Cows' horns hurt", "Ouch, the cow gave you a horn", "Meow meow, I'm a cow I said meow meow I'm a cow !" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getBedLine() {
    List<String> lines = Arrays.asList(new String[] { "Sorry I thought you were in the Nether §c>:)" , "Let's simulate the Nether !", "Intentional game feature huh?", "§cBoom §f>:)", "You weren't in the Nether ? Don't care + didn't ask" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getFlintLine() {
    List<String> lines = Arrays.asList(new String[] { "Ouch ! Don't play with fire !", "the flamme scared you", "Dont play with that you'll burn yourself", "Ouch!", "Fire burns apparently..." });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getSandFallingLine() {
    List<String> lines = Arrays.asList(new String[] { "This block is enjoying a reversed gravity", "It seems like this bloc kthought he was Superman", "It flies !", "It will fall back...\nProbably\nMaybe?\nI guess..." });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getWaterPlaceLine() {
    List<String> lines = Arrays.asList(new String[] { "Your §bwater §fbucket wanted to be a §clava §fbucket", "You water buket was in fact a hidden lava bucket", 
          "Who wrote \"§bBucket§f\" on this \"§9Blue §cLava Bucket§f\" ???", "Nice lava bucket kiddo", "Lava is rarer than water... it's kind of a gift so" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getCanceledWaterLine() {
    List<String> lines = Arrays.asList(new String[] { "Oh ! water wasn't placed ? It surely a lag right ?", "Due to water restrictions, this bucket has been canceled", "Wow ! That's impressive how fast this water evaporated !" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getColdenLava() {
    List<String> lines = Arrays.asList(new String[] { "How this lava transformed into obsidian on its own ???", "The environment is so cold that the lava turned into obsidian", "Lava can hurt so we replaced it with obsidian :)" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getStonedLava() {
    List<String> lines = Arrays.asList(new String[] { "You wanted to build a stone generator ? Yes ? No ? Well here is a stone at least", "Did you know that lava were melted stone ? Now stone is melted lava ?", "You needed this lava ? That's sad for you" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getChickenDamageLine() {
    List<String> lines = Arrays.asList(new String[] { "Well it seems that this chicken was a firework", "Mhh... so this chicken can fly... Oh wait it exploded...", "This chicken prefered to die in a beautiful firework :')", "So beautiful... \nfor a deceased chicken" });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
  
  public static String getCoalExplosionLine() {
    List<String> lines = Arrays.asList(new String[] { "§4WATCH OUT ! §cThere is firedamp !", "What is this smell ? Wait... §cOH NO IT'S FIREDAMP !", "Your pickaxe on the rock made a §6spark§f...\nAnd the tunnel was full of §cgas§f..." });
    Random rdm = new Random();
    return lines.get(rdm.nextInt(lines.size()));
  }
}
