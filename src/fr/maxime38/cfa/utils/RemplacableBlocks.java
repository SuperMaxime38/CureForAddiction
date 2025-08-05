package fr.maxime38.cfa.utils;

public enum RemplacableBlocks {
	  AIR, TALL_GRASS, POPPY, DANDELION, CORNFLOWER, BLUE_ORCHID, ALLIUM, AZURE_BLUET, TULIP, OXEYE_DAISY, LILY_OF_THE_VALLEY;
	  
	  public static boolean contains(String value) {
	    byte b;
	    int i;
	    RemplacableBlocks[] arrayOfRemplacableBlocks;
	    for (i = (arrayOfRemplacableBlocks = values()).length, b = 0; b < i; ) {
	      RemplacableBlocks remplacableBlocks = arrayOfRemplacableBlocks[b];
	      if (remplacableBlocks.toString().equals(value))
	        return true; 
	      b++;
	    } 
	    return false;
	  }
	}
