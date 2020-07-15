package com.cxp.bloght.pojo;

import java.util.Date;

public class MessageDo {
    private Long id;

    private String nickname;

    private String email;

    private String content;

    private Date create_time;

    private String avatar;

    private Long parent_message_id;

    private Boolean admin_message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Long getParent_message_id() {
        return parent_message_id;
    }

    public void setParent_message_id(Long parent_message_id) {
        this.parent_message_id = parent_message_id;
    }

    public Boolean getAdmin_message() {
        return admin_message;
    }

    public void setAdmin_message(Boolean admin_message) {
        this.admin_message = admin_message;
    }
}