package com.archjohn.pmanager.domain.service;

import com.archjohn.pmanager.domain.entity.Member;
import com.archjohn.pmanager.domain.exeception.MemberNotFoundException;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import com.archjohn.pmanager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(SaveMemberDataDTO saveMemberData) {
        Member member = Member
                .builder()
                .name(saveMemberData.getName())
                .email(saveMemberData.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member loadMemberById(String memberId) {
        return  memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }
}
