/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package top.cmarco.spigotcalculator;

import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import top.cmarco.spigotcalculator.command.CalculatorCommand;
import top.cmarco.spigotcalculator.config.CalculatorConfig;
import top.cmarco.spigotcalculator.listeners.CalculatorListener;

@Getter
public final class SpigotCalculator extends JavaPlugin {

    private CalculatorConfig calcConfig = null;
    private CalculatorCommand calculatorCommand = null;
    private CalculatorListener calculatorListener = null;

    private void setupConfig() {
        this.calcConfig = new CalculatorConfig(this);
        this.calcConfig.loadConfig();
    }

    private void setupListeners() {
        calculatorListener = new CalculatorListener(this);
        getServer().getPluginManager().registerEvents(this.calculatorListener, this);
    }

    private void setupCommands() {
        this.calculatorCommand = new CalculatorCommand(this);
    }

    private void registerCommands() {
        final Server server = super.getServer();

        server.getPluginCommand("calculator").setExecutor(this.calculatorCommand);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        this.setupConfig();
        this.setupCommands();
        this.registerCommands();
        this.setupListeners();
    }

}
