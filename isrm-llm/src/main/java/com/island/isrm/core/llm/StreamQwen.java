package com.island.isrm.core.llm;

// Copyright (c) Alibaba, Inc. and its affiliates.

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.ResultCallback;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.alibaba.dashscope.utils.JsonUtils;
import io.reactivex.Flowable;

public class StreamQwen {
    public static void streamCallWithMessage()
            throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        Message userMsg =
                Message.builder().role(Role.USER.getValue()).content("如何做西红柿炖牛腩？").build();
        GenerationParam param = GenerationParam.builder()
                .model("qwen-turbo")
                .messages(Arrays.asList(userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE) // the result if message format.
                .topP(0.8).enableSearch(true) // set streaming output
                .incrementalOutput(true) // get streaming output incrementally
                .build();
        Flowable<GenerationResult> result = gen.streamCall(param);
        StringBuilder fullContent = new StringBuilder();
        result.blockingForEach(message -> {
            fullContent.append(message.getOutput().getChoices().get(0).getMessage().getContent());
            System.out.println(JsonUtils.toJson(message));
        });
        System.out.println("Full content: \n" + fullContent.toString());
    }

    public static void streamCallWithCallback()
            throws NoApiKeyException, ApiException, InputRequiredException, InterruptedException {
        Generation gen = new Generation();
        Message userMsg =
                Message.builder().role(Role.USER.getValue()).content("如何做西红柿炖牛腩？").build();
        GenerationParam param = GenerationParam.builder()
                .model("qwen-turbo")
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)  //set result format message
                .messages(Arrays.asList(userMsg)) // set messages
                .topP(0.8)
                .incrementalOutput(true) // set streaming output incrementally
                .build();
        Semaphore semaphore = new Semaphore(0);
        StringBuilder fullContent = new StringBuilder();
        gen.streamCall(param, new ResultCallback<GenerationResult>() {

            @Override
            public void onEvent(GenerationResult message) {
                fullContent
                        .append(message.getOutput().getChoices().get(0).getMessage().getContent());
                System.out.println(message);
            }

            @Override
            public void onError(Exception err) {
                System.out.println(String.format("Exception: %s", err.getMessage()));
                semaphore.release();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
                semaphore.release();
            }

        });
        semaphore.acquire();
        System.out.println("Full content: \n" + fullContent.toString());
    }

    public static void main(String[] args) {
        Constants.apiKey="sk-17a542a2d65744d492fedb83ad25d353";
        try {
            streamCallWithMessage();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.out.println(e.getMessage());
        }
        try {
            streamCallWithCallback();
        } catch (ApiException | NoApiKeyException | InputRequiredException
                 | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }
}
