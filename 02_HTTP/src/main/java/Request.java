public class Request {
    private String Post;
    private String date;
    private String host;
    private String contentType;
    private String authorization;

    public Request() {
    }

    public String getPost() {
        return this.Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
