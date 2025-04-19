package com.archjohn.pmanager.repository;

import com.archjohn.pmanager.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, String> {
    Optional<Member> findByIdAndDeleted(String id, boolean deleted);

    Optional<Member> findByEmailAndDeleted(String email, boolean deleted);

    default List<Member> findAllNotDeleted() {
        return findAll()
                .stream()
                .filter(m -> !m.getDeleted())
                .toList();
    }
}
