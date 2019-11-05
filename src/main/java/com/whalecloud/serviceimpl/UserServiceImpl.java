package com.whalecloud.serviceimpl;

import com.whalecloud.domain.re.User;
import com.whalecloud.domain.re.UserExample;
import com.whalecloud.mapper.re.UserMapper;
import com.whalecloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


/**
 * @author zhaoyanac
 * @date 2019/10/17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean login(String userName, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName).andPasswordEqualTo(password);

        User user = userMapper.selectByExample(example).get(0);

        if (StringUtils.isEmpty(user)) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public User getOne(String userName, String password) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName).andPasswordEqualTo(password);

        User user = userMapper.selectByExample(example).get(0);

        return user;
    }
}


