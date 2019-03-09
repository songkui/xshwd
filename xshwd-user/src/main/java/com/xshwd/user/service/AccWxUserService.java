package com.xshwd.user.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xshwd.orm.user.entity.AccWxUser;
import com.xshwd.orm.user.mapper.AccWxUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: SK ON  2018/10/29 14:58
 * @Description:
 */

@Service
public class AccWxUserService {

    @Autowired
    private AccWxUserMapper accWxUserMapper;

    /**
     * 通过openId查询
     * @param openId
     * @return
     */
    public AccWxUser getUserByOpenId(String openId){

        if (StringUtils.isEmpty(openId)){
            return  new AccWxUser();
        }

        EntityWrapper<AccWxUser> userWrapper = new EntityWrapper<>();
        userWrapper.and("open_id = {0}", openId);

        List<AccWxUser> bdList = accWxUserMapper.selectList(userWrapper);
        if (null == bdList || bdList.isEmpty()){
            return new AccWxUser();
        }
        return bdList.get(0);
    }





}
