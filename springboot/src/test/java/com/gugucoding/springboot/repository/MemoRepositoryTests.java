package com.gugucoding.springboot.repository;

import com.gugucoding.springboot.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i-> {
            Memo memo = Memo.builder().text("Sample ... "+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {

        //데이터베이스에 존재하는 no
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=====================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2() {

        //데이터베이스에 존재하느 mno
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("=================");
        System.out.println(memo);

    }

    @Test
    public void testUpdate() {

        Memo memo = Memo.builder().mno(100L).text("~~~~").build();

        System.out.println(memoRepository.save(memo));

    }

    @Test
    public void testDelete() {

        Long mno = 100L;
        memoRepository.deleteById(mno);

    }


}
