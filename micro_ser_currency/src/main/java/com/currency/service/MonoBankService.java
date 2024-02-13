package com.currency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MonoBankService {

    @Value("${banks.monoBank}")
    private String base_url;

}
