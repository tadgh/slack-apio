import org.json.simple.JSONObject;

/**
 * Created by garyg on 7/3/2015.
 */
public class SlackUser {
    private String id;
    private String realName;
    private String email;

    private String dmChannelId;
    private boolean isInboxvuduUser;

    private SlackUser(String id, String realName, String email){
        this.id = id;
        this.realName = realName;
        this.email = email;
    }

    public static SlackUser createFromJSONObject(JSONObject jsonBlob){

        String id  = (String)jsonBlob.get("id");
        String realName = (String)jsonBlob.get("real_name");
        JSONObject profile = (JSONObject)jsonBlob.get("profile");
        String email = (String)profile.get("email");
        SlackUser newUser = new SlackUser(id, realName, email);
        return newUser;
    }

    public String toString(){
        return String.format("id: %s\t real name: %s\t email: %s", id, realName, email);
    }
}
