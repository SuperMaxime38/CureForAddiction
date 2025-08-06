package fr.maxime38.cfa.utils;

import java.util.Random;

public class VoiceLines {
	
	static Random rdm = new Random();
	
  public static String getStrikeLine() {
    String[] lines = { "Did you know that you had §a1/1000 000 §fchance to be hitten by a lightning strike ?\nWhat a coincidence !", "You're unlucky... who could have imagined that you'd be hitten by a lighting strike", 
          "Was the sky rainy ? Well it doesn't matter after all...", "Enjoy the life of a Tesla coil", "Standing under a tree during a thunder is highly discouraged" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getSheepLine() {
    String[] lines = { "This sheep give you a wool block to make you stop hurting him", "The sheep was scared and your item was stuck in his fur, so your item dropped", 
          "Why this wool piece is colored?", "Bêêêh", "I hope you weren't holding an end rod ?!" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getCowLine() {
    String[] lines = { "Cows' horns hurt", "Ouch, the cow gave you a horn", "Meow meow, I'm a cow I said meow meow I'm a cow !" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getBedLine() {
    String[] lines = { "Sorry I thought you were in the Nether §c>:)" , "Let's simulate the Nether !", "Intentional game feature huh?", "§cBoom §f>:)", "You weren't in the Nether ? Don't care + didn't ask" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getFlintLine() {
    String[] lines = { "Ouch ! Don't play with fire !", "the flamme scared you", "Dont play with that you'll burn yourself", "Ouch!", "Fire burns apparently..." };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getSandFallingLine() {
    String[] lines = { "This block is enjoying a reversed gravity", "It seems like this bloc kthought he was Superman", "It flies !", "It will fall back...\nProbably\nMaybe?\nI guess..." };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getWaterPlaceLine() {
    String[] lines = { "Your §bwater §fbucket wanted to be a §clava §fbucket", "You water buket was in fact a hidden lava bucket", 
          "Who wrote \"§bBucket§f\" on this \"§9Blue §cLava Bucket§f\" ???", "Nice lava bucket kiddo", "Lava is rarer than water... it's kind of a gift so" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getCanceledWaterLine() {
    String[] lines = { "Oh ! water wasn't placed ? It surely a lag right ?", "Due to water restrictions, this bucket has been canceled", "Wow ! That's impressive how fast this water evaporated !" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getColdenLava() {
    String[] lines = { "How this lava transformed into obsidian on its own ???", "The environment is so cold that the lava turned into obsidian", "Lava can hurt so we replaced it with obsidian :)" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getStonedLava() {
    String[] lines = { "You wanted to build a stone generator ? Yes ? No ? Well here is a stone at least", "Did you know that lava were melted stone ? Now stone is melted lava ?", "You needed this lava ? That's sad for you" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getChickenDamageLine() {
    String[] lines = { "Well it seems that this chicken was a firework", "Mhh... so this chicken can fly... Oh wait it exploded...", "This chicken prefered to die in a beautiful firework :')", "So beautiful... \nfor a deceased chicken" };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getCoalExplosionLine() {
    String[] lines = { "§4WATCH OUT ! §cThere is firedamp !", "What is this smell ? Wait... §cOH NO IT'S FIREDAMP !", "Your pickaxe on the rock made a §6spark§f...\nAnd the tunnel was full of §cgas§f..." };
    return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getStumbleLine() {
	  String[] lines = {"OUPS ! You stumbled on something !", "There was probably a §7rock §fyou stumbled on"};
	  return lines[rdm.nextInt(lines.length)];
  }
  
  public static String getDestroyedCropsLine(int truc) {
	  if(truc == 0) { // remplacé par de l'air (parce que je l'ai décidé)
		  String[] lines = {"Oh no ! The harvest are really bad this time..."};
		  return lines[rdm.nextInt(lines.length)];
	  }
	  if(truc == 1) { //qu'il ya de la lave !!
		  String[] lines = {"It seems that this crop was a §2Whopperflower §f! but in minecraft ? Well the most important is that you now have §clava §fthere", "This crop wanted to be lava, its wish has been granted"};
		  return lines[rdm.nextInt(lines.length)]; 
	  } else { //huh att... ah oui, que ya un fontaine ? jet ? truc ? de poisonous potato !!!!! (à pernes les fontaines)
		  String[] lines = {"this potato wasn't ripe"};
		  return lines[rdm.nextInt(lines.length)];
	  }
  }
}
