/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */
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
