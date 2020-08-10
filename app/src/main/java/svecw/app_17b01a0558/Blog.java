package svecw.app_17b01a0558;

public class Blog {
    private String titleval,titledes, image,Username;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public Blog() {
    }

    public String getTitleval() {
        return titleval;
    }

    public void setTitleval(String titleval) {
        this.titleval = titleval;
    }



    public String gettitledes() {
        return titledes;
    }

    public Blog(String titleval, String titledes,String username) {
        this.titleval = titleval;
        this.titledes = titledes;
        Username = username;
    }

    public void settitledes(String titledes) {
        this.titledes = titledes;
    }




}