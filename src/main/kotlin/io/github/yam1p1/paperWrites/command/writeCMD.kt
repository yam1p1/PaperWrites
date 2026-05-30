package io.github.yam1p1.paperWrites.command

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.ChatArgument
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.arguments.TextArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import io.github.yam1p1.paperWrites.MainLogic

object writeCMD{
    fun register(){
        CommandAPICommand("write")
            .withArguments(
                TextArgument("title"),
                GreedyStringArgument("contents")
            )
            .executesPlayer(PlayerCommandExecutor {player, args ->
                val title = args["title"] as String
                val contents = args["contents"] as String

                MainLogic().givePaper(player, title, contents)
            }).register()
    }
}