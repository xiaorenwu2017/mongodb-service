package com.qjc.service;

import com.qjc.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MongoDBTestService {

    @Resource
    private MongoTemplate mongoTemplate;

    public void addMongoDB(User user) {
        //添加到Mongo数据库时，默认以对象User为集合名称添加
        mongoTemplate.insert(user);
        //也可以指定添加到某个集合中
        //mongoTemplate.insert(user, "testCollection");
    }

    public void deleteMongoDB(String userName) {
        //根据姓名删除
        Query query = Query.query(Criteria.where("userName").is(userName));
        mongoTemplate.remove(query, User.class);
    }

    public void updateMongoDB(User user) {
        //更改一条数据的一个字段
        Query query = Query.query(Criteria.where("_id").is(user.getId()));
        Update update = Update.update("age", user.getAge());
        //参数1:查询条件  参数2:修改  参数3:表名称（可以传对应集合的对象名）
        mongoTemplate.updateFirst(query, update, User.class);
    }

    public void updateManyMongoDB(User user) {
        //更改一条数据的多个字段
        Query query = Query.query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("age", user.getAge());
        update.set("userName", user.getUserName());
        //参数1:查询条件  参数2:修改  参数3:表名称（也可以是具体集合名）
        mongoTemplate.updateFirst(query, update, "user");
    }

    public User getOneMongoDB(String userName) {
        Query query = Query.query(Criteria.where("userName").is(userName));
        //findOne() 返回一个文档满足指定的查询条件。如果有多个文档满足查询条件，则返回第一个文档
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public List<User> getManyMongoDB(String userName) {
        Query query = Query.query(Criteria.where("userName").is(userName));
        //find() 返回所有满足条件的文档
        List<User> UserList = mongoTemplate.find(query, User.class, "user");
        return UserList;
    }

    public List<User> getAllMongoDB() {
        //findAll() 返回集合中所有的文档
        List<User> UserList = mongoTemplate.findAll(User.class, "user");
        return UserList;
    }

    public List<User> getFuzzyMongoDB(String userName) {
        //设置正则规则
        //Pattern.CASE_INSENSITIVE 启用不区分大小写的匹配
        Pattern pattern = Pattern.compile("^.*" + userName + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("userName").regex(pattern));
        List<User> UserList = mongoTemplate.find(query, User.class);
        return UserList;
    }

    public List<User> getpageMongoDB() {
        Query query = new Query();
        //跳过2条，每页显示2条
        query.skip(2).limit(2);
        List<User> UserList = mongoTemplate.find(query, User.class, "user");
        //总个数
        long count = mongoTemplate.count(query, "user");
        System.out.println(count);
        return UserList;
    }


}


