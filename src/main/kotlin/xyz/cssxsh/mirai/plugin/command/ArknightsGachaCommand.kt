package xyz.cssxsh.mirai.plugin.command

import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.message.data.toPlainText
import xyz.cssxsh.arknights.excel.*
import xyz.cssxsh.mirai.plugin.*
import xyz.cssxsh.mirai.plugin.data.*

object ArknightsGachaCommand : CompositeCommand(
    owner = ArknightsHelperPlugin,
    "gacha", "抽卡",
    description = "明日方舟助手抽卡指令"
) {

    @SubCommand("one", "单抽")
    @Description("单抽")
    suspend fun CommandSenderOnMessage<*>.one(times: Int = 1) = sendMessage {
        check(coin >= times * POOL_USE_COIN) { "合成玉不足" }
        // coin -= times * POOL_USE_COIN
        val result: RecruitResult = (1..times).map { gacha(pool = obtain.pool(rule)) }.groupBy { it.rarity }
        "当前卡池[${pool}] \n".toPlainText() + result.getContent()
    }

    @SubCommand("ten", "十连")
    @Description("十连")
    suspend fun CommandSenderOnMessage<*>.ten(times: Int = 1) = one(times * 10)

    @SubCommand("pool", "卡池")
    @Description("设置新卡池")
    suspend fun CommandSenderOnMessage<*>.pool(name_: String, set: Boolean = false) = sendMessage {
        val name = name_.lines().first()
        val lines = fromEvent.message.content.lines().filter {
            it.matches(BUILD_POOL_LINE) || it.startsWith('#')
        }.joinToString(";")
        rules[name] = lines.trim()
        if (set) pool = name
        "卡池[${name}] -> $lines 已写入".toPlainText()
    }

    @SubCommand("detail", "详情")
    @Description("查看卡池详情")
    suspend fun CommandSenderOnMessage<*>.detail() = sendMessage {
        rules.entries.joinToString("\n") { (name, rule) ->
            "===> [${name}]" + rule.split(';').joinToString("\n")
        }.toPlainText()
    }

    @SubCommand("set", "设置")
    @Description("设置卡池")
    suspend fun CommandSenderOnMessage<*>.set(name: String = GachaPoolRule.NORMAL.name) = sendMessage {
        check(name in rules) { "卡池不存在" }
        pool = name
        "卡池[${pool}]设置完毕".toPlainText()
    }
}