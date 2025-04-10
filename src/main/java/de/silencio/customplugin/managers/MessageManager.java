package de.silencio.customplugin.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class MessageManager {
    private static final MiniMessage mm = MiniMessage.miniMessage();

    // Global
    public static final Component INVALID_PERMISSION = mm.deserialize("<red>You don't have permission to use this command.");
    public static final Component INVALID_USAGE = mm.deserialize("<red>Invalid command usage.");
    public static final Component INVALID_PLAYER = mm.deserialize("<red>Player not found.");
    public static final Component INVALID_SERVER = mm.deserialize("<red>Invalid server.");
    public static final Component EMPTY = Component.text("");

    // AFK
    private static final String NOW_AFK_TEMPLATE = "<gray><italic><name> is now AFK.";
    private static final String NOT_AFK_TEMPLATE = "<gray><italic><name> is no longer AFK.";

    // Chat
    private static final String JOIN_MESSAGE_TEMPLATE = "<dark_gray>[<dark_green>+<dark_gray>]<reset> <name>";
    private static final String LEAVE_MESSAGE_TEMPLATE = "<dark_gray>[<red>-<dark_gray>]<reset> <name>";
    private static final String CHAT_MESSAGE_TEMPLATE = "<dark_gray>[<dark_aqua><server><dark_gray>]<reset> <name><dark_gray>:<reset> <message>";
    private static final String SWITCH_SERVER_TEMPLATE = "<dark_gray>[<aqua>➟<dark_gray>]<reset> <name> <dark_gray>joined <dark_aqua><server>";

    // Ban Manager
    public static final Component BAN_MESSAGE = mm.deserialize("<red>You are banned from the network.");
    public static final Component KICK_MESSAGE = mm.deserialize("<red>You have been kicked from the server.");
    private static final String BANNED_TEMPLATE = "<green>Successfully banned <name>.";
    private static final String UNBANNED_TEMPLATE = "<green>Successfully unbanned <name>.";
    public static final Component NOT_BANNED = mm.deserialize("<red>Player is not banned.");
    public static final Component ALREADY_BANNED = mm.deserialize("<red>Player is already banned.");

    // Commands
    public static final Component MAP_MESSAGE = mm.deserialize("<aqua>The map can be viewed <gold><u><hover:show_text:'<gold>Click me!'><click:open_url:'https://map.devlencio.net'>here</click></hover></u></gold><aqua>.");
    public static final Component NO_INFO = mm.deserialize("<red>No info for this server.");
    public static final Component HELP_HEADER = mm.deserialize("<gold>These are all registered command, including aliases:");

    // Info Command
    public static final Component CHANGES = mm.deserialize("""
                                    <bold><gold>Changes:</gold></bold>
                                     <bold><blue>»</blue></bold> <gray> Axes can be used as weapons. They also support the Sharpness enchantment.
                                     <bold><blue>»</blue></bold> <gray> Sneaking twice whilst looking down allows you to sit anywhere.
                                     <bold><blue>»</blue></bold> <gray> Right clicking a crop with a hoe automatically harvests and replants it.
                                     <bold><blue>»</blue></bold> <gray> Crop Trampling is disabled.
                                     <bold><blue>»</blue></bold> <gray> Timber is installed. Shift to cut down the entire tree.
                                     <bold><blue>»</blue></bold> <gray> Concrete powder can be converted when dropped into a water filled cauldron.
                                     <bold><blue>»</blue></bold> <gray> Nether portals can have any shape or size.
                                     <bold><blue>»</blue></bold> <gray> Mobs can be silenced using a name tag called "silence me".
                                     <bold><blue>»</blue></bold> <gray> Revamped anvil xp costs.
                                     <bold><blue>»</blue></bold> <gray> The height limit is 2032 blocks.
                                     <bold><blue>»</blue></bold> <gray> You dont need an arrow in your inventory to use infinity.
                                     <bold><blue>»</blue></bold> <gray> Infinity and mending are combinable.
                                     <bold><blue>»</blue></bold> <gray> Villagers follow you when holding emeralds.
                                     <bold><blue>»</blue></bold> <gray> Villager discounts are shared between players.
                                    """);
    public static final Component CRAFTING = mm.deserialize("""
                                    <bold><gold>Crafting Tweaks:</gold></bold>
                                     <bold><blue>»</blue></bold> <gray> Ice can be unpacked into its former items.
                                     <bold><blue>»</blue></bold> <gray> Increased the amount of trapdoors, bark, stairs, bricks and carpets crafted.
                                     <bold><blue>»</blue></bold> <gray> Coal can be crafted into black dye.
                                     <bold><blue>»</blue></bold> <gray> Many recipes like bread, paper or shulker boxes are now shapeless.
                                     <bold><blue>»</blue></bold> <gray> You can dye blocks with already dyed blocks.
                                     <bold><blue>»</blue></bold> <gray> Sandstone and its variants can be dyed into their red variant using red dye.
                                     <bold><blue>»</blue></bold> <gray> Blackstone can now replace cobblestone in any recipe.
                                     <bold><blue>»</blue></bold> <gray> Concrete powder can be smelted into colored glass.""");
    public static final Component COMMANDS = mm.deserialize("""
                                    <bold><gold>Commands:</gold></bold>
                                     <bold><blue>»</blue></bold> <gray> /home set <name> -> Set an new home. You can set up to three.
                                     <bold><blue>»</blue></bold> <gray> /home tp <name> -> Teleport to a home.
                                     <bold><blue>»</blue></bold> <gray> /home delete <name> -> Removes a home.
                                     <bold><blue>»</blue></bold> <gray> /tpa <player> -> Request to teleport to someone.
                                     <bold><blue>»</blue></bold> <gray> /afk -> Sets your status to AFK.
                                     <bold><blue>»</blue></bold> <gray> /nick <nickname> -> Changes your displayed name.
                                     <bold><blue>»</blue></bold> <gray> /map -> Link to the browser map.""");

    public static Component nowAfk(String player) {
        return mm.deserialize(NOW_AFK_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component notAfk(String player) {
        return mm.deserialize(NOT_AFK_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component banSuccess(String player) {
        return mm.deserialize(BANNED_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component unbanSuccess(String player) {
        return mm.deserialize(UNBANNED_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component join(String player) {
        return mm.deserialize(JOIN_MESSAGE_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component leave(String player) {
        return mm.deserialize(LEAVE_MESSAGE_TEMPLATE, Placeholder.parsed("name", player));
    }

    public static Component chat(String player, String server, String message) {
        return mm.deserialize(CHAT_MESSAGE_TEMPLATE,
                Placeholder.parsed("server", server),
                Placeholder.parsed("name", player),
                Placeholder.parsed("message", message)
        );
    }

    public static Component switchServer(String player, String server) {
        return mm.deserialize(SWITCH_SERVER_TEMPLATE,
                Placeholder.parsed("server", server),
                Placeholder.parsed("name", player)
        );
    }
}
