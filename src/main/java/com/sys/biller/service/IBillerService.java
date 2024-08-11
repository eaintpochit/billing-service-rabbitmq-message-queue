package com.sys.biller.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IBillerService {

    public void send() throws JsonProcessingException;
}
