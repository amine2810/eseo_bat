package tn.esprit.spring.Requests;

public class ChangePWDRequest {

    private String oldPassword;
    private String newPassword;


    public ChangePWDRequest( String oldPassword,  String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;

    }

    public ChangePWDRequest() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
