package com.meesho.notificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

import static com.meesho.notificationservice.constants.Constants.INDEX_NAME;

@Document(indexName = INDEX_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchEntity {
    @Id
    private int id;
    private String phoneNumber;
    private String message;
    private long createdAt;
}
