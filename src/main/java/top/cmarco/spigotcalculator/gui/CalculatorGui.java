package top.cmarco.spigotcalculator.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public final class CalculatorGui {

    public final static Inventory CALCULATOR_INV = Bukkit.createInventory(null, 9*5, "Calculator");

    private final static String SUM_URL = "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/3edd20be93520949e6ce789dc4f43efaeb28c717ee6bfcbbe02780142f716\"}}}";
    public final static ItemStack SUM = getSkull(SUM_URL, 1, "Sum");

    private final static String EQUAL_URL = "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/787689f833436aa711abbd45168856775a2b114656cdf4dc5a6c6f1afae520\"}}}";
    public final static ItemStack EQUAL = getSkull(EQUAL_URL, 1, "Equals");

    private final static String COMPUTER_URL = "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/203bba4ebc8f9f51268893e70a38e6c487cda58582cb8c5c4e63e144fbf9\"}}}";
    public final static ItemStack COMPUTER = getSkull(COMPUTER_URL, 1, "Computations");

    private final static String[] DIGITS_URLS = new String[]{
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/55a224807693978ed834355f9e5145f9c56ef68cf6f2c9e1734a46e246aae1\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/31a9463fd3c433d5e1d9fec6d5d4b09a83a970b0b74dd546ce67a73348caab\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/acb419d984d8796373c9646233c7a02664bd2ce3a1d3476dd9b1c5463b14ebe\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/f8ebab57b7614bb22a117be43e848bcd14daecb50e8f5d0926e4864dff470\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/62bfcfb489da867dce96e3c3c17a3db7c79cae8ac1f9a5a8c8ac95e4ba3\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/ef4ecf110b0acee4af1da343fb136f1f2c216857dfda6961defdbee7b9528\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/f331a6a6fcd6995b62088d353bfb68d9b89ae258325caf3f2886464f54a7329\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/d4ba6ac07d422377a855793f36dea2ed240223f52fd1648181612ecd1a0cfd5\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/c61a8a641437be9aea207253dd3f25440d954ea2b5866c552f386b29ac4d049\"}}}",
            "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/a1928e1bfd86a9b79397c4cb4b65ef99af49b7d5f7957ad62c0c699a622cfbe\"}}}"
    };

    public final static ItemStack[] DIGITS = new ItemStack[]{
            getSkull(DIGITS_URLS[0], 1, "0"),
            getSkull(DIGITS_URLS[1], 1, "1"),
            getSkull(DIGITS_URLS[2], 2, "2"),
            getSkull(DIGITS_URLS[3], 3, "3"),
            getSkull(DIGITS_URLS[4], 4, "4"),
            getSkull(DIGITS_URLS[5], 5, "5"),
            getSkull(DIGITS_URLS[6], 6, "6"),
            getSkull(DIGITS_URLS[7], 7, "7"),
            getSkull(DIGITS_URLS[8], 8, "8"),
            getSkull(DIGITS_URLS[9], 9, "9")
    };


    static {
        CALCULATOR_INV.setItem(12, DIGITS[0]);
        CALCULATOR_INV.setItem(0, DIGITS[1]);
        CALCULATOR_INV.setItem(1, DIGITS[2]);
        CALCULATOR_INV.setItem(2, DIGITS[3]);
        CALCULATOR_INV.setItem(9, DIGITS[4]);
        CALCULATOR_INV.setItem(10, DIGITS[5]);
        CALCULATOR_INV.setItem(11, DIGITS[6]);
        CALCULATOR_INV.setItem(18, DIGITS[7]);
        CALCULATOR_INV.setItem(19, DIGITS[8]);
        CALCULATOR_INV.setItem(20, DIGITS[9]);
        
        CALCULATOR_INV.setItem(8, COMPUTER);
        CALCULATOR_INV.setItem(16, SUM);
        CALCULATOR_INV.setItem(25, EQUAL);
    }

    private static PlayerProfile getProfile(String url) {
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID()); // Get a new player profile
        PlayerTextures textures = profile.getTextures();
        URL urlObject;
        try {
            urlObject = new URL(url); // The URL to the skin, for example: https://textures.minecraft.net/texture/18813764b2abc94ec3c3bc67b9147c21be850cdf996679703157f4555997ea63a
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Invalid URL", exception);
        }
        textures.setSkin(urlObject); // Set the skin of the player profile to the URL
        profile.setTextures(textures); // Set the textures back to the profile
        return profile;
    }

    public static ItemStack getSkull(String url, int amount, String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
        if(url.isEmpty()) return head;
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        headMeta.setOwnerProfile(getProfile(url));
        headMeta.setDisplayName(name);
        head.setItemMeta(headMeta);
        return head;
    }

    /**
     * Fill a collection of integer positions with the same given ItemStack.
     * @param positions The slots to fill.
     * @param inventory The target Inventory.
     * @param item The item that will be filling the slots.
     */
    public static void fillInventoryRepeatedItem(Collection<Integer> positions, Inventory inventory, ItemStack item) {
        positions.forEach(pos -> inventory.setItem(pos, item));
    }

    /**
     * Creates a custom ItemStack with given characteristics.
     * @param material The material of the ItemStack.
     * @param amount The amount of items in the ItemStack.
     * @param name The display name of the ItemStack.
     * @param desc The lore of the ItemStack.
     * @return returns a new instance of the ItemStack with given characteristics.
     */
    public static ItemStack createCustomItem(Material material, int amount, String name, String... desc) {
        final ItemStack item = new ItemStack(material, amount <= 0 ? 1 : amount);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (desc.length != 0) {
            itemMeta.setLore(Arrays.asList(desc));
        }
        item.setItemMeta(itemMeta);
        return item;
    }
}
