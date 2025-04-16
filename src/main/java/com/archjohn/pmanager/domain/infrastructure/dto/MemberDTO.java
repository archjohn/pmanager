package com.archjohn.pmanager.domain.infrastructure.dto;

import com.archjohn.pmanager.domain.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private final String id;
    private final String name;
    private final String secret;
    private final String email;

    public static MemberDTO create(Member member) {

        return  new MemberDTO(
                member.getId(),
                member.getName(),
                member.getSecret(),
                member.getEmail()
        );

    }

}
