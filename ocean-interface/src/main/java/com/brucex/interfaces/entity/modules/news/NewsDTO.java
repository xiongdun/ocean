package com.brucex.interfaces.entity.modules.news;

import com.brucex.interfaces.entity.Common;
import com.brucex.interfaces.entity.modules.sys.UserDTO;

import java.util.Date;

public class NewsDTO extends Common {
    private static final long serialVersionUID = 7975025793864064692L;

    private String name;
    private String content;
    private Date datetime;
    private UserDTO user;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
