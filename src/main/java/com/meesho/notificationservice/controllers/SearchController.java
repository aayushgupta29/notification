package com.meesho.notificationservice.controllers;

import com.meesho.notificationservice.models.SearchEntity;
import com.meesho.notificationservice.models.request.SearchRequest;
import com.meesho.notificationservice.models.response.SearchResponse;
import com.meesho.notificationservice.repository.SearchRepository;
import com.meesho.notificationservice.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/search")
public class SearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;
    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/time/{pageNumber}")
    public ResponseEntity<SearchResponse> searchWithinTimeRange(@RequestBody SearchRequest request, @PathVariable int pageNumber) {
        List<SearchEntity> allMessagesInTimeRange = elasticSearchService.searchWithinTimeRange(request, pageNumber);
        return ResponseEntity.ok(SearchResponse.builder().data(allMessagesInTimeRange).build());
    }

    @GetMapping("/message/{pageNumber}")
    public ResponseEntity<SearchResponse> searchByMessage(@RequestBody SearchRequest request, @PathVariable int pageNumber) {
        List<SearchEntity> searchedMessages = elasticSearchService.searchByMessage(request, pageNumber);
        return ResponseEntity.ok(SearchResponse.builder().data(searchedMessages).build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<SearchEntity>> findAll() {
        return ResponseEntity.ok(searchRepository.findAll());
    }
}
