package com.xxh.web.service;

import com.xxh.web.dao.UserDO;

/**
 * @author 小小黑
 */
public interface UserService {

    /**
     * e
     * @param id
     * @return
     */
    UserDO findUserByUserId(Integer id);

}
