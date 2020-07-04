package me.imillusion.noteblockmusic.player;

import me.imillusion.noteblockmusic.NoteBlockMusicPlugin;
import me.imillusion.noteblockmusic.song.Song;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SongPlayer {

    private NoteBlockMusicPlugin main;

    public SongPlayer(NoteBlockMusicPlugin main) {
        this.main = main;
    }

    private Map<UUID, SongPlayRunnable> currentSongs = new HashMap<>();

    public void playSong(Player player, Song song)
    {
        playSong(player, song, player.getLocation());
    }

    public void playSong(Player player, Song song, Location playLocation)
    {
        if(currentSongs.containsKey(player.getUniqueId()))
            currentSongs.get(player.getUniqueId()).cancel();

        currentSongs.put(player.getUniqueId(), new SongPlayRunnable(main, player.getUniqueId(), song, playLocation));
    }
}
