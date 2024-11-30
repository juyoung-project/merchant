package com.kjy.merchant.common;

import java.util.Arrays;
import java.util.List;

public enum PermitUrl {

    PermitUrlList(Arrays.asList("/", "/api/health-check", "/api/sign-in", "/error", "/favicon.ico", "/api/sign-up", "/login",  "/sign-up-page"));

    private List<String> permitUrlList;

    private PermitUrl(List<String> permitURL) {
        this.permitUrlList = permitURL;
    }

    public String[] getPermitURL() {
        return permitUrlList.toArray(new String[0]);
    }

}