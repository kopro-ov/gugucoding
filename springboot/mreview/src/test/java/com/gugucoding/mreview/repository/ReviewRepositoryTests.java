package com.gugucoding.mreview.repository;

import com.gugucoding.mreview.entity.Member;
import com.gugucoding.mreview.entity.Movie;
import com.gugucoding.mreview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReviews() {

        //200개 리뷰 등록
        IntStream.rangeClosed(1, 200).forEach(i-> {
            //영화 번호
            Long mno = (long)(Math.random()*100)+1;
            //리뷰어 번호
            Long mid =  (long)(Math.random()*100)+1;
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한...."+i)
                    .build();
            reviewRepository.save(movieReview);

        });

    }

    @Test
    public void testGetMovieReviews() {

        Movie movie = Movie.builder().mno(92L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
            System.out.println(movieReview.getReviewnum());
            System.out.println(movieReview.getMember().getEmail());
        });


    }


}
