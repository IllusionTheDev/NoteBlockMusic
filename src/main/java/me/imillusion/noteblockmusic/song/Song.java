package me.imillusion.noteblockmusic.song;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Song {

    @Setter
    private int tickDistance = 1;

    private List<Note> notes = new ArrayList<>();

    public Song(int tickDistance)
    {
        this.tickDistance = tickDistance;
    }

    public Song(List<Note> notes) {
        this.notes = notes;
    }

    public Song(ConfigurationSection section)
    {
        tickDistance = section.getInt("tick-distance");

        for(String key : section.getConfigurationSection("notes").getKeys(false))
        {
            int position = section.getInt("notes." + key + ".position");
            float pitch = (float) section.getDouble("notes." + key + ".pitch");
            float volume = (float) section.getDouble("notes." + key + ".volume");
            Sound sound = Sound.valueOf(section.getString("notes." + key + ".sound"));

            notes.add(new Note(position, pitch, volume, sound));
        }
    }

    public void save(ConfigurationSection section)
    {
        section.set("tick-distance", tickDistance);

        for(int i = 0; i < notes.size(); i++)
        {
            Note note = notes.get(i);

            section.set("notes." + i + ".position", note.getPosition());
            section.set("notes." + i + ".pitch", note.getPitch());
            section.set("notes." + i + ".volume", note.getVolume());
            section.set("notes." + i + ".sound", note.getSound().name());
        }
    }

    public List<Note> getNotesByPosition(int position)
    {
        return notes.stream().filter(note -> note.getPosition() == position).collect(Collectors.toList());
    }

    public int getSongDuration()
    {
        int duration = 0;

        for(Note note : notes)
            if(note.getPosition() < duration)
                duration = note.getPosition();

        return duration * tickDistance;
    }
}
