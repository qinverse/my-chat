package com.qinverse.chat.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/deepseek/chat-client")
public class DeepSeekController {

    private final DashScopeChatModel chatModel;

    public DeepSeekController(DashScopeChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/{prompt}")
    public Generation chat(@PathVariable(value = "prompt") String prompt) {

        ChatResponse chatResponse = chatModel.call(new Prompt(prompt));

        return chatResponse.getResult();
    }

}
