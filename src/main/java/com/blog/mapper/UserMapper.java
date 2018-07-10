package com.blog.mapper;

import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import org.springframework.stereotype.Repository;

/**
 * @author shibo
 */
@Repository
public interface UserMapper {
    public void save(User user) throws Exception;

    public UserVo getUserVoById(String userId) throws Exception;
}