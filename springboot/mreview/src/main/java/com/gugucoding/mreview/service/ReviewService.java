package com.gugucoding.mreview.service;

import com.gugucoding.mreview.dto.ReviewDTO;
import com.gugucoding.mreview.entity.Member;
import com.gugucoding.mreview.entity.Movie;
import com.gugucoding.mreview.entity.Review;

import java.util.List;

public interface ReviewService {

    //영화의 모든 리뷰 조회
    List<ReviewDTO> getListOfMovie(Long mno);

    //영화 리뷰 등록
    Long register(ReviewDTO movieReviewDTO);

    //특정한 영화리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    //리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO) {

        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();
        return movieReview;

    }

    default ReviewDTO entityToDto(Review movieReview) {

        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;

    }

}
