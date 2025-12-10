package model.options;

public class Tone {
    private String tone;

    public Tone(){
        tone = "Any";
    }

    public Tone(String tone)
    {
        this.tone = tone;
    }


    public String getTone() { return tone; }
    public void setTone(String tone) { this.tone = tone; }

    @Override
    public String toString()
    {
        return tone;
    }
}
