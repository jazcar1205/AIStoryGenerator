package model.options;

public class Characters {
    private String characters;

    public Characters(){
        characters = "Any";
    }

    public Characters(String characters)
    {
        this.characters = characters;
    }


    public String getCharacters() { return characters; }
    public void setCharacters(String characters) { this.characters = characters; }

    @Override
    public String toString()
    {
        return characters;
    }
}
