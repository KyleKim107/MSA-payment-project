package com.msapay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
public class Membership {
    // Membership 클래스는 오염이 되면 안되는 클래스, 핵심 도메인
    // field에 final을 붙이면 생성자에 의해 생성될때 주입받은 값 외로 변경 불가능하다.
    private final Long membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean isValid;
    private final boolean isCorp;

    public static Membership generateMember(MembershipId id
    ,MembershipName name
    ,MembershipAddress address
    ,MembershipEmail email
    ,MembershipValid valid
    ,MembershipCorp corp){
        return new Membership(id.id
        ,name.name
        ,email.email
        ,address.address
        ,valid.valid
        ,corp.isCorp);
    }

    @Value
    public static class MembershipId{
        Long id;
        public MembershipId(Long value){
            this.id = value;
        }
    }

    @Value
    public static class MembershipName{
        String name;
        public MembershipName(String value){
            this.name = value;
        }
    }
    @Value
    public static class MembershipEmail{
        String email;
        public MembershipEmail(String value){
            this.email = value;
        }
    }
    @Value
    public static class MembershipAddress{
        String address;
        public MembershipAddress(String value){
            this.address = value;
        }
    }
    @Value
    public static class MembershipValid{
        boolean valid;
        public MembershipValid(boolean value){
            this.valid = value;
        }
    }
    @Value
    public static class MembershipCorp{
        boolean isCorp;
        public MembershipCorp(boolean value){
            this.isCorp = value;
        }
    }
}
