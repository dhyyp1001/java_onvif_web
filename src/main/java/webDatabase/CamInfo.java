package webDatabase;

public class CamInfo {
    private String sName;
    private String camIP;
    private String camRtsp;//rtsp port
    private String camHttp;
    private String modIP;
    private String modPort;
    private String camId;
    private String camPwd;
    private String mediaUri;

    private String owner;
    private String manager;
    private String operator;

    public CamInfo(String sName, String camIP, String camRtsp, String camHttp, String modIP, String modPort, String camId, String camPwd, String mediaUri, String owner, String manager, String operator) {
        this.sName = sName;
        this.camIP = camIP;
        this.camRtsp = camRtsp;
        this.camHttp = camHttp;
        this.modIP = modIP;
        this.modPort = modPort;
        this.camId = camId;
        this.camPwd = camPwd;
        this.mediaUri = mediaUri;
        this.owner = owner;
        this.manager = manager;
        this.operator = operator;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getCamIP() {
        return camIP;
    }

    public void setCamIP(String camIP) {
        this.camIP = camIP;
    }

    public String getCamRtsp() {
        return camRtsp;
    }

    public void setCamRtsp(String camRtsp) {
        this.camRtsp = camRtsp;
    }

    public String getCamHttp() {
        return camHttp;
    }

    public void setCamHttp(String camHttp) {
        this.camHttp = camHttp;
    }

    public String getModIP() {
        return modIP;
    }

    public void setModIP(String modIP) {
        this.modIP = modIP;
    }

    public String getModPort() {
        return modPort;
    }

    public void setModPort(String modPort) {
        this.modPort = modPort;
    }

    public String getCamId() {
        return camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }

    public String getCamPwd() {
        return camPwd;
    }

    public void setCamPwd(String camPwd) {
        this.camPwd = camPwd;
    }

    public String getMediaUri() {
        return mediaUri;
    }

    public void setMediaUri(String mediaUri) {
        this.mediaUri = mediaUri;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
