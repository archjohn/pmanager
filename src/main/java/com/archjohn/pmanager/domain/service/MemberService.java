package com.archjohn.pmanager.domain.service;

import com.archjohn.pmanager.domain.entity.Member;
import com.archjohn.pmanager.domain.exeception.MemberNotFoundException;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import com.archjohn.pmanager.domain.infrastructure.exception.DuplicateMemberException;
import com.archjohn.pmanager.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member createMember(SaveMemberDataDTO saveMemberData) {
        if (existsMemberWithEmail(saveMemberData.getEmail(), null)) {
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }

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

    @Transactional
    public void deleteMember(String memberId) {
        Member member = loadMemberById(memberId);
        member.setDeleted(true);
    }


    @Transactional
    public Member updateMember(String memberId, SaveMemberDataDTO saveMemberData){
        if (existsMemberWithEmail(saveMemberData.getEmail(), memberId)) {
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }

        Member member = loadMemberById(memberId);

        member.setName(saveMemberData.getName());
        member.setEmail(saveMemberData.getEmail());

        return member;
    }

    private boolean existsMemberWithEmail(String email, String idToExclude) {
        return memberRepository
                .findByIdAndDeleted(email, false)
                .filter(m -> !Objects.equals(m.getId(), idToExclude))
                .isPresent();
    }


}
