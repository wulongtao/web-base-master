package com.xxh.web.mapper;


import com.xxh.web.dao.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 小小黑
 */
@Component
public interface UserMapper {

    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM `tb_user` WHERE `id` = #{id}")
    UserDO findUserByUserId(@Param("id") Integer id);


}
