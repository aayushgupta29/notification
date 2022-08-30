package com.meesho.notificationservice.models.response;

import com.meesho.notificationservice.models.SearchEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SearchResponse {
    private List<SearchEntity> data;
}
