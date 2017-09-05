package com.lvfq.code.dynamic.data;

import java.io.Serializable;

/**
 * CommentsBean
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/9/3 下午10:03
 * @desc :
 */

public class CommentsBean implements Serializable {
    private int commentsId;
    private String content;
    private UserBean replyUser; // 回复人信息
    private UserBean commentsUser;  // 评论人信息

    public int getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(UserBean replyUser) {
        this.replyUser = replyUser;
    }

    public UserBean getCommentsUser() {
        return commentsUser;
    }

    public void setCommentsUser(UserBean commentsUser) {
        this.commentsUser = commentsUser;
    }
}
