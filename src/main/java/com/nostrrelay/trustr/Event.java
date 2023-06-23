package com.nostrrelay.trustr;

import java.util.List;

public class Event {

    private String id;
    private String pubkey;
    private Long created_at;
    private Integer kind;
    private List<List<String>> tags;
    private String content;
    private String sig;

    public Event() {
    }

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

    public String getPubkey() {
        return pubkey;
    }

    public Event setPubkey(String pubkey) {
        this.pubkey = pubkey;
        return this;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public Event setCreated_at(Long created_at) {
        this.created_at = created_at;
        return this;
    }

    public Integer getKind() {
        return kind;
    }

    public Event setKind(Integer kind) {
        this.kind = kind;
        return this;
    }

    public List<List<String>> getTags() {
        return tags;
    }

    public Event setTags(List<List<String>> tags) {
        this.tags = tags;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Event setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSig() {
        return sig;
    }

    public Event setSig(String sig) {
        this.sig = sig;
        return this;
    }
}
