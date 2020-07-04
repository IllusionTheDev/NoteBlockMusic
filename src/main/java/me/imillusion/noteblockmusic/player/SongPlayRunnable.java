package me.imillusion.noteblockmusic.player;

import me.imillusion.noteblockmusic.song.Song;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class SongPlayRunnable implements Runnable {

    private Song song;
    private UUID uuid;

    private Location location = null;

    private int position;
    private int taskId;

    public SongPlayRunnable(JavaPlugin plugin, UUID uuid, Song song) {
        this.uuid = uuid;
        this.song = song;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, song.getTickDistance());
    }

    public SongPlayRunnable(JavaPlugin plugin, UUID uuid, Song song, Location location) {
        this.uuid = uuid;
        this.song = song;
        this.location = location;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, song.getTickDistance());
    }


    @Override
    public void run() {
        if(Bukkit.getPlayer(uuid) == null || !Bukkit.getPlayer(uuid).isOnline())
        {
            cancel();
            return;
        }

        Player player = Bukkit.getPlayer(uuid);

        if(location == null)
            song.getNotesByPosition(position).forEach(note -> note.play(player));
        else
            song.getNotesByPosition(position).forEach(note -> note.play(player, location));

        if(++position >= (song.getSongDuration() / song.getTickDistance()))
            cancel();

    }

    protected void cancel()
    {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
