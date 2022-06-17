package com.example.demo.configuration;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.model.Avatar;
import com.example.demo.service.AvatarService;
import com.example.demo.service.serviceImpl.AvatarServiceImpl;
import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class Sendgridconfig {

    @Value("${sendgrid.key}")
    private String key;

    @Bean
    public SendGrid getSendegrid(){
        return new SendGrid(key);
    }
    
    @Bean
    public AvatarDAO getAvatarDao(){
        return new AvatarDAO() {
            @Override
            public List<Avatar> findAll() {
                return null;
            }

            @Override
            public List<Avatar> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Avatar> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Avatar> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Avatar> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Avatar> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Avatar> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Avatar getOne(Long aLong) {
                return null;
            }

            @Override
            public Avatar getById(Long aLong) {
                return null;
            }

            @Override
            public Avatar getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Avatar> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Avatar> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Avatar> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Avatar> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Avatar> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Avatar entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Avatar> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Avatar> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Avatar> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Avatar> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Avatar> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Avatar, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

}
