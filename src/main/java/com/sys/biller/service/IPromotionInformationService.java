package com.sys.biller.service;

import com.sys.biller.dto.PromotionMessageDto;

public interface IPromotionInformationService {

    void sendPromotionMsg(PromotionMessageDto dto);
}
