package me.imillusion.noteblockmusic.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class Note {

    private int position;

    private float pitch;
    private float volume;

    private Sound sound;

    public void play(Player player)
    {
        play(player, player.getLocation());
    }

    public void play(Player player, Location location)
    {
        player.playSound(location, sound, volume, pitch);
    }
}
