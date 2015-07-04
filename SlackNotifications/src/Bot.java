/**
 * Created by garygraham on 7/2/15.
 */
import com.google.common.io.CharStreams;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.apache.http.*;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Bot {
    SlackSession session;
    private String api_key = "xoxb-7128609975-8YLfyQ6r5a7EEg8lZYb5UQLP";


    public Bot(String api_key) {
        this.api_key = api_key;
        this.session = null;
    }

    public void listUsers(){
        HttpClient client = HttpClientBuilder.create().build();
        String req_string = "https://slack.com/api/users.list";
        HttpPost request = new HttpPost(req_string);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("token", api_key));
        try{
            request.setEntity(new UrlEncodedFormEntity(nameValuePairList, "UTF-8"));
            HttpResponse response = client.execute(request);
            String jsonResponse = CharStreams.toString(new InputStreamReader(response.getEntity().getContent()));
            JSONObject obj = (JSONObject)JSONValue.parse(jsonResponse);
            JSONArray members = (JSONArray)obj.get("members");
            for(Object user: members){
                System.out.println("user: " + user);

            }
            System.out.println("JSON: " + members);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void greetTeam(List<SlackUser> users){
        for (SlackUser user: users){
            if(isInboxVuduUserEmail(user.getUserMail())){
                greetInboxVuduUser(user);
            }else{
                greetNonInboxVuduUser(user);
            }
        }
    }
    public void greetInboxVuduUser(SlackUser user){
        //TODO Grab default message from helper class.
        throw new NotImplementedException();

    }
    public void greetNonInboxVuduUser(SlackUser user){
        String messageText = MessageFormatter.makeKnownUserGreeting(user);
        //getSession().sendMessage(getSession().getChannels())
    }
    public void getDmChannelForUser(SlackUser user){
        //getSession().findChannelByName(user.getUserMail())
    }
    public boolean isInboxVuduUserEmail(String mailbox){
        //TODO make DB call to check if he exists in credentials.
        throw new NotImplementedException();
    }



    public static void main(String[] args){
        Bot bot = new Bot("xoxb-7128609975-8YLfyQ6r5a7EEg8lZYb5UQLP");
        bot.listUsers();
    }
}
