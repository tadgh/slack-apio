/**
 * Created by garygraham on 7/2/15.
 */
public class MessageFormatter {
    public static String makeUnknownUserGreeting(SlackUser user){
        String messageText = String.format("Hi there %s! You don't seem to be an Inbox Vudu user yet! <https://www.inboxvudu.com|Follow this link> to sign up", user);
        return messageText;
    }

    public static String makeKnownUserGreeting(SlackUser user){
        String messageText = String.format("Good to see you %s!\n Since your team has integrated Inboxvudu and you are already a member,  you will now see your requests in real time in this chat!", user);
        return messageText;
    }
}
