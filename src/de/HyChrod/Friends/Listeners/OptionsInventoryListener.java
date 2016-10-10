/*
*
* This class was made by HyChrod
* All rights reserved, 2016
*
*/
package de.HyChrod.Friends.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.HyChrod.Friends.FileManager;
import de.HyChrod.Friends.Friends;
import de.HyChrod.Friends.Util.InventoryBuilder;
import de.HyChrod.Friends.Util.InventoryTypes;
import de.HyChrod.Friends.Util.ItemStacks;
import de.HyChrod.Friends.Util.PlayerUtilities;

public class OptionsInventoryListener implements Listener {

	private Friends plugin;

	public OptionsInventoryListener(Friends friends) {
		this.plugin = friends;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();

		if (e.getInventory() != null) {
			if (e.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&',
					FileManager.ConfigCfg.getString("Friends.GUI.OptionsInv.Title")))) {
				e.setCancelled(true);
				if (e.getCurrentItem() != null) {
					if (e.getCurrentItem().hasItemMeta()) {
						if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
							PlayerUtilities pu = new PlayerUtilities(p);
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equals(ItemStacks.OPTIONSBUTTON(pu.get(3), "option_noRequests", "�a")
											.getItemMeta().getDisplayName())) {
								pu.toggleOption("option_noRequests");
								this.reOpenInv(p);
								return;
							}
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equals(ItemStacks.OPTIONSBUTTON(pu.get(3), "option_noChat", "�b")
											.getItemMeta().getDisplayName())) {
								pu.toggleOption("option_noChat");
								this.reOpenInv(p);
								return;
							}
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equals(ItemStacks.OPTIONSBUTTON(pu.get(3), "option_noJumping", "�c")
											.getItemMeta().getDisplayName())) {
								pu.toggleOption("option_noJumping");
								this.reOpenInv(p);
								return;
							}
							if (e.getCurrentItem().getItemMeta().getDisplayName()
									.equals(ItemStacks.OPTIONSBUTTON(pu.get(3), "option_noMsg", "�d")
											.getItemMeta().getDisplayName())) {
								pu.toggleOption("option_noMsg");
								this.reOpenInv(p);
								return;
							}
							if (e.getCurrentItem().equals(ItemStacks.OPTIONS_BACK.getItem())) {
								InventoryBuilder.openInv(p, InventoryBuilder.INVENTORY(plugin, p, InventoryTypes.MAIN, false));
								return;
							}
						}
					}
				}
			}
		}
	}

	public void reOpenInv(final Player player) {
		InventoryBuilder.openInv(player, InventoryBuilder.OPTIONS_INVENTORY(player, false));
	}

}
