package top.cmarco.spigotcalculator.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import top.cmarco.spigotcalculator.SpigotCalculator;
import top.cmarco.spigotcalculator.config.CalculatorConfig;
import top.cmarco.spigotcalculator.gui.CalculatorGui;

import java.util.List;

@RequiredArgsConstructor
public class CalculatorCommand implements CommandExecutor {

    private final SpigotCalculator plugin;

    public static void sendColorMessage(final CommandSender sender, final String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void sendColorMessage(final CommandSender sender, final List<String> message) {
        message.forEach(str -> sendColorMessage(sender, str));
    }

    private static final String CALCULATOR_OPEN_PERM = "calculator.open";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final CalculatorConfig config = this.plugin.getCalcConfig();

        if (!(commandSender instanceof final Player player)) {
            sendColorMessage(commandSender, config.getConsoleBlocked());
            return true;
        }

        if (!player.hasPermission(CALCULATOR_OPEN_PERM)) {
            sendColorMessage(commandSender, config.getMissingPerms(CALCULATOR_OPEN_PERM));
            return true;
        }

        if (strings.length != 0) {
            sendColorMessage(commandSender, config.getIncorrectUsage());
            return true;
        }

        player.closeInventory();
        player.openInventory(CalculatorGui.CALCULATOR_INV);

        return true;
    }
}
