package com.msapay.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.banking.application.port.out.GetMembershipPort;
import com.msapay.common.CommonHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final CommonHttpClient commonHttpClient;

    private final String membershipServiceUrl;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
                                    @Value ("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
    }

    @Override
    public MembershipStatus getMembership(String membershipId) {
        //실제로 http call
        String url = String.join("/", membershipServiceUrl, membershipId);
        try{
            String response = commonHttpClient.sendGetRequest(url).body();
            //json -> object
            ObjectMapper objectMapper = new ObjectMapper();
            Membership memebership = objectMapper.readValue(response, Membership.class);
            if(memebership.isValid()){
                return new MembershipStatus(memebership.getMembershipId(),true);
            }else{
                return new MembershipStatus(memebership.getMembershipId(),false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // we need http client
        return null;
    }
}
