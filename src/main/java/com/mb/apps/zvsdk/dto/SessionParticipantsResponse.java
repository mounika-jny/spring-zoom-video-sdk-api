
package com.mb.apps.zvsdk.dto;

import java.util.List;

public class SessionParticipantsResponse {
    private int page_size;
    private String next_page_token;
    private List<SessionUser> users;

    public int getPage_size() { return page_size; }
    public void setPage_size(int page_size) { this.page_size = page_size; }

    public String getNext_page_token() { return next_page_token; }
    public void setNext_page_token(String next_page_token) { this.next_page_token = next_page_token; }

    public List<SessionUser> getUsers() { return users; }
    public void setUsers(List<SessionUser> users) { this.users = users; }
}
