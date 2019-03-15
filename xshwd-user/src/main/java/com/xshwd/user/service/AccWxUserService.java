package com.xshwd.user.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xshwd.framework.util.Constants;
import com.xshwd.orm.user.entity.AccWxUser;
import com.xshwd.orm.user.mapper.AccWxUserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: SK ON  2018/10/29 14:58
 * @Description:
 */

@Service
public class AccWxUserService {
    private final Logger logger = LoggerFactory.getLogger(AccWxUserService.class);

    @Autowired
    private AccWxUserMapper accWxUserMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    public String login(AccWxUser accWxUser){
        if (null == accWxUser || StringUtils.isEmpty(accWxUser.getOpenId())){
            return null;
        }

        AccWxUser beforUser = getUserByOpenId(accWxUser.getOpenId());
        if (null == beforUser || null == beforUser.getIdx()){
            accWxUserMapper.insert(accWxUser);
            beforUser = accWxUser;
        }
        String token = DigestUtils.md5Hex(UUID.randomUUID().toString());
        String userJson = JSON.toJSONString(beforUser);
        stringRedisTemplate.opsForValue().set(token, userJson);
        logger.info("=== userInfo ===   key: |"+ token + "| VAL:"+ userJson);
        stringRedisTemplate.expire(token, Constants.TOKEN_LIFECYCLE, TimeUnit.MINUTES);
        return token;

    }





}
