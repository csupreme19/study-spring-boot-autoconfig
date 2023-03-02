package hello.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public void initTable() {
        jdbcTemplate.execute("CREATE TABLE MEMBER(member_id varchar primary key, name varchar)");
    }

    public void save(Member member) {
        jdbcTemplate.update("INSERT INTO MEMBER(member_id, name) VALUES ( ?, ? )"
                , member.getMemberId()
                , member.getName());
    }

    public Member find(String memberId) {
        return jdbcTemplate.queryForObject("SELECT member_id, name FROM MEMBER WHERE member_id=?"
                , BeanPropertyRowMapper.newInstance(Member.class)
                , memberId);
    }

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT member_id, name FROM MEMBER"
                , BeanPropertyRowMapper.newInstance(Member.class));
    }

}
