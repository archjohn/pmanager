package com.archjohn.pmanager.domain.infrastructure.controller;

import com.archjohn.pmanager.domain.entity.Member;
import com.archjohn.pmanager.domain.infrastructure.dto.MemberDTO;
import com.archjohn.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import com.archjohn.pmanager.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
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

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMemberById(@PathVariable("id") String memberId) {
        Member member = memberService.loadMemberById(memberId);
        return ResponseEntity.ok(MemberDTO.create(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable("id") String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(
            @PathVariable("id") String memberId,
            @Valid @RequestBody SaveMemberDataDTO saveMemberData
    ) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAllMembers(
            @RequestParam(value = "email", required = false) String email
    ) {
        List<Member> members = memberService.findAllMembers(email);

        return ResponseEntity.ok(
                members.stream().map(MemberDTO::create).toList()
        );
    }

}
