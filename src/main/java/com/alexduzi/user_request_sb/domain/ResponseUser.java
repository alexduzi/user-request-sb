package com.alexduzi.user_request_sb.domain;

import com.alexduzi.user_request_sb.dto.UserDTO;

import java.util.List;

public class ResponseUser {

    private List<UserDTO> content;

    public List<UserDTO> getContent() {
        return content;
    }

    public void setContent(List<UserDTO> content) {
        this.content = content;
    }
}
