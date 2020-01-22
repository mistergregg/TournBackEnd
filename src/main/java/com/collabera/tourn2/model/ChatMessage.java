package com.collabera.tourn2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "ChatMessage")
public class ChatMessage {
    @Id
    private String id;
    private String fromId;
    private String toId;
    private String toUsername;
    private String fromUsername;
    private String message;
}
