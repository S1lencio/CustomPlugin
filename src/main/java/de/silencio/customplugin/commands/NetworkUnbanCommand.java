package de.silencio.customplugin.commands;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import de.silencio.customplugin.CustomPlugin;
import de.silencio.customplugin.managers.BanManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NetworkUnbanCommand implements SimpleCommand {
    private final ProxyServer server;
    private final CustomPlugin plugin;
    private final BanManager banManager;
    private static final MiniMessage mm = MiniMessage.miniMessage();

    static final Component notBanned = mm.deserialize("<red>Player is not banned.");
    static final Component invalidPlayer = mm.deserialize("<red>Player not found.");
    static final Component invalidUsage = mm.deserialize("<red>Invalid command usage.");
    final static Component invalidPermission = mm.deserialize("<red>You don't have permission to use this command.");

    public NetworkUnbanCommand(ProxyServer server, CustomPlugin plugin) {
        this.server = server;
        this.plugin = plugin;
        this.banManager = plugin.getBanManager();
    }

    @Override
    public void execute(final Invocation invocation) {
        // Check if the player has the permission to use this command
        if (!invocation.source().hasPermission("custom.networkunban") || !invocation.source().hasPermission("custom.*")) {
            invocation.source().sendMessage(invalidPermission);
            return;
        }

        // Check if the command was used correctly
        if (invocation.arguments().length != 1) {
            invocation.source().sendMessage(invalidUsage);
            return;
        }

        // Check if the player exists
        banManager.getUUIDFromUsernameAsync(invocation.arguments()[0]).thenAccept(uuid -> {
            server.getScheduler().buildTask(plugin, () -> {
                if (uuid == null) {
                    // player does not exist
                    invocation.source().sendMessage(invalidPlayer);
                    return;
                }
                if (!banManager.isBanned(uuid)) {
                    invocation.source().sendMessage(notBanned);
                    return;
                }
                // Unban the player
                this.banManager.unbanPlayer(uuid);

                // Send a message to the player who executed the command
                final Component successMessage = mm.deserialize("<green>Successfully unbanned " + invocation.arguments()[0] + ".");
                invocation.source().sendMessage(successMessage);
            }).schedule();
        });
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(final Invocation invocation) {
        List<String> returnList = new ArrayList<>();

        if (!invocation.source().hasPermission("custom.networkunban") || !invocation.source().hasPermission("custom.*")) { return CompletableFuture.completedFuture(List.of()); }
        // TODO: suggest banned players
        return CompletableFuture.completedFuture(returnList);
    }
}
