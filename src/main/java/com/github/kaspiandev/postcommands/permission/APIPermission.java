package com.github.kaspiandev.postcommands.permission;

import com.github.kaspiandev.postcommands.user.ApiUser;

public interface APIPermission {

    String getName();

    boolean check(ApiUser user);

}
