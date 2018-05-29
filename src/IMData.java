public class IMData {


    private String userID;
    private String username;
    private String toUserID;
    private String toUsername;
    private String content;
    private String ip;
    public IMData(String ip,String userID, String username, String toUserID, String toUsername, String content) {
        this.ip = ip;
        this.userID = userID;
        this.username = username;
        this.toUserID = toUserID;
        this.toUsername = toUsername;
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
