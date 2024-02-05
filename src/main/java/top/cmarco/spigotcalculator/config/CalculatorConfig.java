/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file,
 * You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package top.cmarco.spigotcalculator.config;

import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import top.cmarco.spigotcalculator.SpigotCalculator;

import java.util.List;

@RequiredArgsConstructor
public final class CalculatorConfig {

    private final SpigotCalculator plugin;
    private FileConfiguration configuration = null;

    public void loadConfig() {
        this.plugin.saveDefaultConfig();
        this.configuration = this.plugin.getConfig();
    }

    public String getPrefix() {
        return this.configuration.getString("messages.prefix");
    }

    public List<String> getInfo() {
        String version = this.plugin.getDescription().getVersion();

        return this.configuration.getStringList("info")
                .stream()
                .map(s -> s.replace("{prefix}", this.getPrefix())
                        .replace("{version}", version))
                .toList();
    }

    public String getResult(final String result) {
        return this.configuration.getString("messages.result")
                .replace("{prefix}", this.getPrefix())
                .replace("{result}", result);
    }

    public String getMissingPerms(final String permission) {
        return this.configuration.getString("messages.missing-permissions")
                .replace("{prefix}", this.getPrefix())
                .replace("{permission}", permission);
    }

    public String getConsoleBlocked() {
        return this.configuration.getString("messages.console-blocked")
                .replace("{prefix}", this.getPrefix());
    }

    public String getIncorrectUsage() {
        return this.configuration.getString("messages.incorrect-usage")
                .replace("{prefix}", this.getPrefix());
    }
}
