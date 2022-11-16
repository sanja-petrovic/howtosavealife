package com.example.isa.service.implementation;

import com.example.isa.dto.NewsDto;
import com.example.isa.kafka.NewsProducer;
import com.example.isa.model.News;
import com.example.isa.repository.NewsRepository;
import com.example.isa.service.interfaces.NewsService;
import com.example.isa.util.Converters.NewsConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;
    private final NewsProducer producer;
    private final NewsConverter newsConverter;


    public NewsServiceImpl(NewsRepository repository, NewsProducer producer, NewsConverter newsConverter) {
        this.repository = repository;
        this.producer = producer;
        this.newsConverter = newsConverter;
    }

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }

    @Override
    public void create(News news) throws JsonProcessingException {
        repository.save(news);
        producer.sendMessage(newsConverter.entityToDto(news));
    }

    @Override
    public void send(NewsDto dto) throws JsonProcessingException {
        producer.sendMessage(dto);
    }


}
