package com.example.demo.domain;


import com.example.demo.domain.enumeration.Authorizer;
import com.example.demo.domain.enumeration.Status;
import jakarta.persistence.*;

import java.time.Instant;


@Entity
@Table(name = "authorization_info")
public class AuthorizationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authorizer")
    @Enumerated(EnumType.STRING)
    private Authorizer authorizer;

    @Column(name = "authorization_4")
    @Enumerated(EnumType.STRING)
    private Status authorization4;

    @Column(name = "authorization_return_4")
    @Enumerated(EnumType.STRING)
    private Status authorizationReturn4;

    @Column(name = "cancellation_4")
    @Enumerated(EnumType.STRING)
    private Status cancellation4;

    @Column(name = "protocol_query_4")
    @Enumerated(EnumType.STRING)
    private Status protocolQuery4;

    @Column(name = "service_status_4")
    @Enumerated(EnumType.STRING)
    private Status serviceStatus4;

    //String pq nao sei o que seria qui
    @Column(name = "average_time")
    private String averageTime;

    @Column(name = "registry_query_4")
    @Enumerated(EnumType.STRING)
    private Status registryQuery4;

    @Column(name = "event_reception_4")
    @Enumerated(EnumType.STRING)
    private Status eventReception4;

    @Column(name = "retrieval_time")
    private Instant retrievalTime;

    @Override
    public String toString() {
        return "AuthorizationInfo{" +
                "id=" + id +
                ", authorizer=" + authorizer +
                ", authorization4=" + authorization4 +
                ", authorizationReturn4=" + authorizationReturn4 +
                ", cancellation4=" + cancellation4 +
                ", protocolQuery4=" + protocolQuery4 +
                ", serviceStatus4=" + serviceStatus4 +
                ", averageTime='" + averageTime + '\'' +
                ", registryQuery4=" + registryQuery4 +
                ", eventReception4=" + eventReception4 +
                ", retrievalTime=" + retrievalTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authorizer getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    public Status getAuthorization4() {
        return authorization4;
    }

    public void setAuthorization4(Status authorization4) {
        this.authorization4 = authorization4;
    }

    public Status getAuthorizationReturn4() {
        return authorizationReturn4;
    }

    public void setAuthorizationReturn4(Status authorizationReturn4) {
        this.authorizationReturn4 = authorizationReturn4;
    }

    public Status getCancellation4() {
        return cancellation4;
    }

    public void setCancellation4(Status cancellation4) {
        this.cancellation4 = cancellation4;
    }

    public Status getProtocolQuery4() {
        return protocolQuery4;
    }

    public void setProtocolQuery4(Status protocolQuery4) {
        this.protocolQuery4 = protocolQuery4;
    }

    public Status getServiceStatus4() {
        return serviceStatus4;
    }

    public void setServiceStatus4(Status serviceStatus4) {
        this.serviceStatus4 = serviceStatus4;
    }

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }

    public Status getRegistryQuery4() {
        return registryQuery4;
    }

    public void setRegistryQuery4(Status registryQuery4) {
        this.registryQuery4 = registryQuery4;
    }

    public Status getEventReception4() {
        return eventReception4;
    }

    public void setEventReception4(Status eventReception4) {
        this.eventReception4 = eventReception4;
    }

    public Instant getRetrievalTime() {
        return retrievalTime;
    }

    public void setRetrievalTime(Instant retrievalTime) {
        this.retrievalTime = retrievalTime;
    }
}
