package ge.ufc.httpclient.model;

public class Setting {
    private String url1;
    private String url2;
    private int timeout;

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }


    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url) {
        this.url1 = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


}
