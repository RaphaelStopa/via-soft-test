package com.example.demo.web.rest;



import com.example.demo.NFeScraper;
import com.example.demo.domain.AuthorizationInfo;
import com.example.demo.domain.enumeration.Authorizer;
import com.example.demo.service.AuthorizationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/AuthorizationInfo")
public class AuthorizationInfoResource {

    @Autowired
    private AuthorizationInfoService authorizationInfoService;

    @Autowired
    private NFeScraper nFeService;

    @GetMapping("/realizar-scraping")
    public List<AuthorizationInfo> realizarScraping() throws IOException {
        nFeService.scrapAndStore();
        return authorizationInfoService.getAllAuthorizationInfos();
    }

    //3
    @GetMapping
    public List<AuthorizationInfo> getLastByAuthorizerList() {
        return authorizationInfoService.getStatusByAutorizador();
    }

    // 4
    @GetMapping("/{authorizer}")
    public AuthorizationInfo getLastByAuthorizer(@PathVariable Authorizer authorizer) {
        return authorizationInfoService.getLastAuthorizer(authorizer);
    }

    //5
    @GetMapping("/ByDate")
    public List<AuthorizationInfo> getLastStatusByAutorizadorAndDate(
            @RequestParam(name = "data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant data) {
        return authorizationInfoService.getLastStatusByAutorizadorAndDate(data);
    }

//    //6 errado
//    @GetMapping("/Unavailable")
//    public Map<Authorizer, Long> getMostUnavailableAuthorizer() {
//        return authorizationInfoService.getMostUnavailableAuthorizer();
//    }

}
