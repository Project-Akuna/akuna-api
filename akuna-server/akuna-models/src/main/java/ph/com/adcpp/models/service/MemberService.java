package ph.com.adcpp.models.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.models.entity.Member;
import ph.com.adcpp.models.repository.MemberRepository;

import java.util.List;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Slf4j
@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> saveAll(List<Member> members) {
        return memberRepository.save(members);
    }
}
