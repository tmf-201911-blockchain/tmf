package com.whalecloud.serviceimpl;

import com.whalecloud.service.BidService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyanac
 * @date 2019/11/5
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BidServiceImpl implements BidService {

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void fetchList() {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void getOne() {

    }
}


