package model;

public enum StrategyType
{
    FANTASY("Fantasy"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    SCIFI("SciFi");

    private final String strat;

    StrategyType(String strat)
    {
	  this.strat = strat;
    }

    public static StrategyType getStrat(String str)
    {
        String str1 = str.replaceAll("\\s","");
        str1 = str1.toUpperCase();
        return StrategyType.valueOf(str1);
    }
    public String toString()
    {
        switch(this)
        {
            case FANTASY:
                return "Fantasy";
            case HORROR:
                return "Horror";
            case ROMANCE:
                return "Romance";
            case SCIFI:
                return "SciFi";
        }
        return null;
    }
}