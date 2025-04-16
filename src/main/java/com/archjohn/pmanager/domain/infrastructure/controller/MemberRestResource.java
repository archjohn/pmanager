package com.archjohn.pmanager.domain.infrastructure.controller;


import com.archjohn.pmanager.domain.entity.Member;
import com.archjohn.pmanager.domain.entity.Project;
import com.archjohn.pmanager.domain.infrastructure.dto.MemberDTO;
import com.archjohn.pmanager.domain.infrastructure.dto.ProjectDTO;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import com.archjohn.pmanager.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.archjohn.pmanager.domain.infrastructure.controller.RestConstants.*;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
public class MemberRestResource {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid SaveMemberDataDTO saveMemberData) {
        Member member = memberService.createMember(saveMemberData);

        return  ResponseEntity
                .created(URI.create(PATH_MEMBERS + "/" + member.getId()))
                .body(MemberDTO.create(member));
    }

}
