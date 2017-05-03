package bot.controller;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

public class MusicBot extends ListenerAdapter 
{

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) 
    {
        // This makes sure we only execute our code when someone sends a message with "!play"
        if (!event.getMessage().getRawContent().startsWith("!play")) return;
        Guild guild = event.getGuild();
        // This will get the first voice channel with the name "music" - matching by voiceChannel.getName().equalsIgnoreCase("music")
        VoiceChannel channel = guild.getVoiceChannelsByName("music", true).get(0);
        AudioManager manager = guild.getAudioManager();

        // MySendHandler should be your AudioSendHandler implementation
        manager.setSendingHandler(new MySendHandler());
        // Here we finally connect to the target voice channel and it will automatically start pulling the audio from the MySendHandler instance
        manager.openAudioConnection(channel);
    }
}
