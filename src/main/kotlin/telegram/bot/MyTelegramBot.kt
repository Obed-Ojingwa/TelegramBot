package org.example.telegram.bot

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.io.File

class MyTelegramBot : TelegramLongPollingBot() {

    private val botToken = BotConfigToken.botToken.toString()
    private val botUsername = BotConfigToken.botUsername.toString()
    private val sentWelcomeMessages = mutableSetOf<Long>()

    override fun getBotToken(): String = botToken

    override fun getBotUsername(): String = botUsername

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val chatId = update.message.chatId
            val receivedMessage = update.message.text

            // Send welcome message and image if it's the first message from the user
            if (!sentWelcomeMessages.contains(chatId)) {
                sendWelcomeMessage(chatId)
                sendWelcomeImage(chatId)
                sentWelcomeMessages.add(chatId)
            }

            when (receivedMessage) {
                "/start" -> sendWelcomeMessage(chatId)
                else -> sendTapCount(chatId, receivedMessage)
            }
        }
    }

    private fun sendWelcomeMessage(chatId: Long) {
        val message = SendMessage()
        message.chatId = chatId.toString()
        message.text = "Delighted to have you!!!."
        message.text = "Follow our twitter channel" +
                "https://x.com/saturn69453/status/1802062302633464270?s=46"
        message.text = "Join our telegram channel" +
                "https://t.me/+aTyZGC3DVEphNGVk"


        try {
            execute(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendWelcomeImage(chatId: Long) {
        val photo = SendPhoto()
        photo.chatId = chatId.toString()
        val imagePath = "C:\\Users\\CHINAZA\\IdeaProjects\\SATURN\\src\\main\\resources\\saturnX.jpeg"
        val imageFile = File(imagePath)

        if (imageFile.exists() && imageFile.canRead()) {
            photo.photo = InputFile(imageFile)
            photo.caption = """
    We are delighted to have you! Our social handles are available for this bot.
    We are under heavy construction, yet almost done with the game integration.
    ${'$'}SATURN exemplifies the dynamic and often unpredictable nature of the cryptocurrency market. By blending astrology with innovation, it captures the essence of what makes meme coins appealing while striving for real-world applicability. As the project develops, it will be fascinating to see how ${'$'}SATURN navigates the challenges and opportunities that lie ahead, potentially setting new standards for meme coins and beyond. Come onboard if you’re tired of earth definitely not a fan of Mars, let’s set sail to Saturn.
    https://t.me/+aTyZGC3DVEphNGVk
    Follow our twitter channel
    https://x.com/saturn69453/status/1802062302633464270?s=46
""".trimIndent()
            val message = SendMessage()
            message.chatId = chatId.toString()
            message.text = "$*SATURN exemplifies the dynamic and often unpredictable nature of the cryptocurrency market. By blending astrology with innovation, it captures the essence of what makes meme coins appealing while striving for real-world applicability. As the project develops, it will be fascinating to see how $*SATURN navigates the challenges and opportunities that lie ahead, potentially setting new standards for meme coins and beyond. \n" +
                    "\n" +
                    "Come onboard if you’re tired of earth definitely not a fan of Mars, let’s set sail to Saturn" +
                    "https://t.me/+aTyZGC3DVEphNGVk"

            message.text = "Follow our twitter channel" +
                    "https://x.com/saturn69453/status/1802062302633464270?s=46"
            message.text = "Join our telegram channel" +
                    "https://t.me/+aTyZGC3DVEphNGVk"


            try {
                execute(photo)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            println("Image file not found or cannot be read: $imagePath")
            if (!imageFile.exists()) {
                println("File does not exist.")
            }
            if (!imageFile.canRead()) {
                println("File cannot be read.")
            }
        }
    }

    private fun sendTapCount(chatId: Long, taps: String) {
        val message = SendMessage()
        message.chatId = chatId.toString()
        message.text = "We are delighted to have you\nOur social handles will be provided to this bot\nWe are on under heavy construction, yet almost done with the game integration"
        message.text = "$*SATURN exemplifies the dynamic and often unpredictable nature of the cryptocurrency market. By blending astrology with innovation, it captures the essence of what makes meme coins appealing while striving for real-world applicability. As the project develops, it will be fascinating to see how $*SATURN navigates the challenges and opportunities that lie ahead, potentially setting new standards for meme coins and beyond. \n" +
                "" +
                "Come onboard if you’re tired of earth definitely not a fan of Mars, let’s set sail to Saturn" +
                "https://t.me/+aTyZGC3DVEphNGVk"

        message.text = "Follow our twitter channel" +
                "https://x.com/saturn69453/status/1802062302633464270?s=46"
        message.text = "Join our telegram channel" +
                "https://t.me/+aTyZGC3DVEphNGVk"

        try {
            execute(message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main() {
    try {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(MyTelegramBot())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

