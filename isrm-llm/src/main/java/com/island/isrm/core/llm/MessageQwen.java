package com.island.isrm.core.llm;

// Copyright (c) Alibaba, Inc. and its affiliates.

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.alibaba.dashscope.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MessageQwen {
    public static void callWithMessage1()
            throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message systemMsg =
                Message.builder().role(Role.SYSTEM.getValue()).content("You are a helpful assistant.").build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content("如何做西红柿鸡蛋？").build();
        msgManager.add(systemMsg);
        msgManager.add(userMsg);
        QwenParam param =
                QwenParam.builder().model(Generation.Models.QWEN_TURBO).messages(msgManager.get())
                        .resultFormat(QwenParam.ResultFormat.MESSAGE)
                        .build();
        GenerationResult result = gen.call(param);
        System.out.println(result);
    }

    public static void callWithMessage2()
            throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.").build();
        Message userMsg =
                Message.builder().role(Role.USER.getValue()).content("你好，周末去哪里玩？").build();
        GenerationParam param = GenerationParam.builder().model("qwen-turbo")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE).topP(0.8).build();
        GenerationResult result = gen.call(param);
        System.out.println(result);
    }

    public static void callWithMessage3()
            throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg =
                Message.builder().role(Role.SYSTEM.getValue()).content("You are a helpful assistant.").build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content("如何做西红柿炖牛腩？").build();
        List<Message> messages = new ArrayList<>();
        messages.add(systemMsg);
        messages.add(userMsg);
        GenerationParam param =
                GenerationParam.builder().model("qwen-turbo").messages(messages)
                        .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                        .topP(0.8)
                        .build();
        GenerationResult result = gen.call(param);
        System.out.println(result);
        // 添加assistant返回到messages列表，user/assistant消息必须交替出现
        messages.add(result.getOutput().getChoices().get(0).getMessage());
        // new message
        userMsg = Message.builder().role(Role.USER.getValue()).content("不放糖可以吗？").build();
        messages.add(userMsg);
        result = gen.call(param);
        System.out.println(result);
        System.out.println(JsonUtils.toJson(result));
    }

    public static void main(String[] args){
        Constants.apiKey="sk-17a542a2d65744d492fedb83ad25d353";
        try {
//            callWithMessage1();
//            callWithMessage2();
            callWithMessage3();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}