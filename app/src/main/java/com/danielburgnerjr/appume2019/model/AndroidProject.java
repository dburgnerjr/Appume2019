package com.danielburgnerjr.appume2019.model;

import java.util.List;

public class AndroidProject {
    private String strAppName;
    private String strGitHubAndroid;
    private String strPlayStore;
    private String strGitHubiOS;
    private String strAppStore;
    private List<String> lstAppDescription;

    public AndroidProject(String strApp, String strGHA, String strPS, String strGHI, String strAS, List<String> lstAD) {
        this.strAppName = strApp;
        this.strGitHubAndroid = strGHA;
        this.strPlayStore = strPS;
        this.strGitHubiOS = strGHI;
        this.strAppStore = strAS;
        this.lstAppDescription = lstAD;
    }

    public String getAppName() {
        return strAppName;
    }

    public String getGitHubAndroid() {
        return strGitHubAndroid;
    }

    public String getPlayStore() {
        return strPlayStore;
    }

    public String getGitHubiOS() {
        return strGitHubiOS;
    }

    public String getAppStore() {
        return strAppStore;
    }

    public List<String> getAppDescription() {
        return lstAppDescription;
    }
}
