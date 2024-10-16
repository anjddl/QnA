package com.market.apple.domain.quiz.service;

import com.market.apple.domain.member.entity.Member;
import com.market.apple.domain.quiz.entity.Quiz;
import com.market.apple.domain.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public Quiz create(String title, String content, Member member, boolean isNotice) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setContent(content);
        quiz.setMember(member);
        quiz.setNotice(isNotice);
        quiz.setCount(0);

        return this.quizRepository.save(quiz);
    }

}
