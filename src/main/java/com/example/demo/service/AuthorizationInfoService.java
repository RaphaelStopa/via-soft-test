package com.example.demo.service;

import com.example.demo.domain.AuthorizationInfo;
import com.example.demo.domain.enumeration.Authorizer;
import com.example.demo.repository.AuthorizationInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AuthorizationInfoService {

    private final AuthorizationInfoRepository authorizationInfoRepository;

    public AuthorizationInfoService(AuthorizationInfoRepository authorizationInfoRepository) {
        this.authorizationInfoRepository = authorizationInfoRepository;
    }

    public List<AuthorizationInfo> getAllAuthorizationInfos() {
        return authorizationInfoRepository.findAll();
    }

    public AuthorizationInfo getLastAuthorizer(Authorizer authorizer) {
        return  authorizationInfoRepository.findTopByAuthorizerOrderByIdDesc(authorizer);
    }

    public List<AuthorizationInfo> getStatusByAutorizador() {
        List<AuthorizationInfo> lastStatusList = authorizationInfoRepository.findLastStatusForAllAuthorizers();
        return lastStatusList;
    }

    public List<AuthorizationInfo> getLastStatusByAutorizadorAndDate(Instant data) {
        return authorizationInfoRepository.findLastStatusForAllAuthorizers(data);
    }

//    public Map<Authorizer, Long> getMostUnavailableAuthorizer() {
//    }
}
