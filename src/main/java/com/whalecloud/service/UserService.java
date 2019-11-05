package com.whalecloud.service;

import com.whalecloud.domain.re.User;

/**
 * @author zhaoyanac
 * @date 2019/10/17
 */
public interface UserService {

    Boolean login(String userName, String password);

    User getOne(String userName, String password);

}

