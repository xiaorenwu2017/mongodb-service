package com.qjc.controller;

import com.qjc.entity.User;
import com.qjc.service.MongoDBTestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/mongodb")
public class MongoDBTestController {

    @Resource
    private MongoDBTestService mongoDBTestService;

    //增操作
    @PostMapping(value = "/add")
    public String addMongoDB(@RequestBody User user) {
        mongoDBTestService.addMongoDB(user);
        return "SUCCESS";
    }

    //删除满足条件的所有Document
    @GetMapping(value = "/delete/{userName}")
    public String deleteMongoDB(@PathVariable(value = "userName") String userName) {
        mongoDBTestService.deleteMongoDB(userName);
        return "SUCCESS";
    }

    //根据id更改一条数据的一个字段
    @PostMapping(value = "/update")
    public String updateMongoDB(@RequestBody User user) {
        mongoDBTestService.updateMongoDB(user);
        return "SUCCESS";
    }

    //根据id更改一条数据的多个字段
    @PostMapping(value = "/update/many")
    public String updateManyMongoDB(@RequestBody User user) {
        mongoDBTestService.updateManyMongoDB(user);
        return "SUCCESS";
    }


    //查询一条数据
    @GetMapping(value = "/find/one/{userName}")
    public User getOneMongoDB(@PathVariable(value = "userName") String userName) {
        return mongoDBTestService.getOneMongoDB(userName);
    }

    //查询多条数据
    @GetMapping(value = "/find/many/{userName}")
    public List<User> getManyMongoDB(@PathVariable(value = "userName") String userName) {
        return mongoDBTestService.getManyMongoDB(userName);
    }

    //查询所有数据
    @GetMapping(value = "/find/all")
    public List<User> getAllMongoDB() {
        return mongoDBTestService.getAllMongoDB();
    }

    //模糊查询
    @GetMapping(value = "/find/fuzzy/{userName}")
    public List<User> getFuzzyMongoDB(@PathVariable(value = "userName") String userName) {
        return mongoDBTestService.getFuzzyMongoDB(userName);
    }

    //分页查询
    @GetMapping(value = "/find/page")
    public List<User> getpageMongoDB() {
        return mongoDBTestService.getpageMongoDB();
    }


}

