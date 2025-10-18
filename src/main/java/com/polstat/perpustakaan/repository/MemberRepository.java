package com.polstat.perpustakaan.repository;
import com.polstat.perpustakaan.entity.Member;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, CrudRepository<Member,Long> {
    List<Member> findByName(@Param("name") String name);

    List<Member> findByMemberID(@Param("member_id") String memberID);
}
