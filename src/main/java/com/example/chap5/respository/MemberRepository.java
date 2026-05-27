package com.example.chap5.respository;

import com.example.chap5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

//    Optional<Member> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
