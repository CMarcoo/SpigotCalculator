package top.cmarco.spigotcalculator.listeners;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import top.cmarco.spigotcalculator.SpigotCalculator;
import top.cmarco.spigotcalculator.gui.CalculatorGui;

@RequiredArgsConstructor
public final class CalculatorListener implements Listener {

    private final SpigotCalculator plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onEvent(InventoryClickEvent event) {

        if (event.getInventory().equals(CalculatorGui.CALCULATOR_INV)) {
            event.setCancelled(true);
        }

    }
}
