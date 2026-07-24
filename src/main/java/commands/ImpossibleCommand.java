package commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import managers.GameManager;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

@NullMarked
public class ImpossibleCommand implements BasicCommand {

    @Override
    public void execute(CommandSourceStack source, String[] args) {
        if (args.length == 0) {
            source.getSender().sendPlainMessage("/impossible start | stop");
            return;
        }

        String sub = args[0].toLowerCase(Locale.ROOT);

        switch (sub) {
            case "start" -> {
                source.getSender().sendPlainMessage("게임 시작!");
                GameManager.start();
            }
            case "stop" -> source.getSender().sendPlainMessage("게임 종료!");
            default -> source.getSender().sendPlainMessage("/impossible start | stop");
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {
        if (args.length <= 1) {
            String prefix = args.length == 0 ? "" : args[0].toLowerCase(Locale.ROOT);
            return List.of("start", "stop").stream()
                    .filter(s -> s.startsWith(prefix))
                    .toList();
        }
        return List.of();
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return sender.isOp();
    }
}