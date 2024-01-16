package ru.tnkv.dsrp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.dsrp.utils.Style;
import ru.tnkv.dsrp.utils.YamlManager;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("global.not-player")));
            return true;
        }

        ItemStack handItem = player.getInventory().getItemInMainHand();
        ItemStack helmetItem = player.getInventory().getHelmet();

        Material handItemType = handItem.getType();
        String messageKey;

        if (handItemType == Material.AIR) {
            messageKey = helmetItem == null ? "hat.need-item" : "hat.unequipped";
        } else {
            messageKey = helmetItem == null ? "hat.equipped" : "hat.changed";
        }


        player.getInventory().setItemInMainHand(helmetItem);
        player.getInventory().setHelmet(handItem);

        sender.sendMessage(Style.ampersand(YamlManager.getMessage(messageKey)));
        return true;
    }
}