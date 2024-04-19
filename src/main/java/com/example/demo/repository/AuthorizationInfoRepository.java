package com.example.demo.repository;

import com.example.demo.domain.AuthorizationInfo;
import com.example.demo.domain.enumeration.Authorizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuthorizationInfoRepository extends JpaRepository<AuthorizationInfo, Integer> {


    AuthorizationInfo findTopByAuthorizerOrderByIdDesc(Authorizer authorizer);

    @Query("SELECT ai FROM AuthorizationInfo ai WHERE ai.id IN " +
            "(SELECT MAX(ai2.id) FROM AuthorizationInfo ai2 GROUP BY ai2.authorizer)")
    List<AuthorizationInfo> findLastStatusForAllAuthorizers();

    @Query("SELECT ai FROM AuthorizationInfo ai WHERE ai.retrievalTime = " +
            "(SELECT MAX(ai2.retrievalTime) FROM AuthorizationInfo ai2 WHERE ai2.authorizer = ai.authorizer)")
    List<AuthorizationInfo> findLastStatusForAllAuthorizersAndMaxData();

    @Query("SELECT ai FROM AuthorizationInfo ai WHERE ai.id IN " +
            "(SELECT MAX(ai2.id) FROM AuthorizationInfo ai2 " +
            "WHERE ai2.retrievalTime <= :data GROUP BY ai2.authorizer)")
    List<AuthorizationInfo> findLastStatusForAllAuthorizers(Instant data);


}
