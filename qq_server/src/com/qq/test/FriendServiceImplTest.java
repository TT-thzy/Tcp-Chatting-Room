package com.qq.test;

import com.qq.pojo.User;
import com.qq.service.FriendService;
import com.qq.service.impl.FriendServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FriendServiceImplTest {

    private FriendService friendService;

    @Before
    public void before() {
        friendService = new FriendServiceImpl();
    }

    @Test
    public void isfriend() {
        User user = new User();
        user.setUid("9638b096c2354fbebc6992610b63e6eb");
//        user.setUsername("test");
        String id="photoTest";
        boolean isfriend = friendService.isFriend(user, id);
        System.out.println(isfriend);
    }
}