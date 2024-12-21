package com.kjy.merchant.common;

import java.util.Arrays;
import java.util.List;

public enum PermitUrl {

    PermitUrlList(Arrays.asList("/" ,
                                "/api/refresh-token-check",
                                "/api/temp-create-admin",
                                "/admin-login",
                                "/api/admin-sign-in",
//                                "/api/deactivate-tenant",
//                                "/api/copy-tenant-with-data",
//                                "/api/create-tenant",
//                                "/api/read-tenant",
//                                "/api/health-check",
                                "/api/sign-in",
                                "/error",
                                "/favicon.ico",
                                "/api/sign-up",
                                "/login",
                                "/sign-up-page"));

    private List<String> permitUrlList;

    private PermitUrl(List<String> permitURL) {
        this.permitUrlList = permitURL;
    }

    public String[] getPermitURL() {
        return permitUrlList.toArray(new String[0]);
    }

}
