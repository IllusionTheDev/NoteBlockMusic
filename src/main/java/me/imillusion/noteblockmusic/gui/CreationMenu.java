package me.imillusion.noteblockmusic.gui;

import me.imillusion.noteblockmusic.song.Song;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CreationMenu implements InventoryHolder {

    private Inventory inv = Bukkit.createInventory(this, 54, "Music Creation");

    public void loadSong(Song song)
    {

    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
