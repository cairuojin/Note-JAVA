package com.gjsyoung.service;

import com.gjsyoung.domain.Spittle;

import java.util.List;
public interface SpittleService {
    List<Spittle> findSpittles(long max , int count);

    Spittle findOneSpittles(long id);

    Spittle save(Spittle spittle);
}
