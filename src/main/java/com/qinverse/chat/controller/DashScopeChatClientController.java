package com.qinverse.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/dashscope/chat-client")
public class DashScopeChatClientController {

    private final ChatClient dashScopeChatClient;

    private static final String DEFAULT_PROMPT = "你好，介绍下你自己！";

    // 使用如下的方式注入 ChatClient
    public DashScopeChatClientController(ChatClient.Builder chatClientBuilder) {
        HashMap<String, Object> aa = new HashMap<>();

        this.dashScopeChatClient = chatClientBuilder.build();
    }

    /**
     * ChatClient 简单调用
     */
    @GetMapping("/simple/chat")
    public String simpleChat(String prompt) {
        if (StringUtils.isBlank(prompt)) {
            prompt = DEFAULT_PROMPT;
        }
        String content = dashScopeChatClient.prompt(prompt).call().content();
        log.info("simpleChat --> \n prompt ={}, \n content = {}", prompt, content);
        return content;
    }
}
