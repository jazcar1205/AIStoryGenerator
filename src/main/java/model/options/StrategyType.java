package model.options;

/**
 * Provides options for the genre/strategy.
 */
public enum StrategyType
{
    ANY("ANY"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    SCIFI("SciFi"),
    GENERIC("Generic");

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
            case ANY:
                return "Any";
        }
        return null;
    }
}