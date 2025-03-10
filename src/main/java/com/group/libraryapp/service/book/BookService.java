package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.userloanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.userloanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(
            BookRepository bookRepository,
            UserLoanHistoryRepository userLoanHistoryRepository,
            UserRepository userRepository
    ) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        // 책 정보를 가져온다.
       Book book = bookRepository.findByName(request.getBookName())
               .orElseThrow(IllegalAccessError::new);

        // 대출 중인 책인지 확인한다.
        // 대출 중인 책이 아니라면 대출 중인 책으로 변경하고 대출
       if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
           throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
       }

       // 유저 정보를 가져온다.

        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalAccessError::new);

        // 유저 정보와 책정보를 기반으로 대출 기록 저장
        // userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalAccessError::new);

        user.returnBook(request.getBookName());

        // UserLoanHistory history = us erLoanHistoryRepository.findByUserIdAndBookName(user.getId(),request.getBookName())
        //      .orElseThrow(IllegalArgumentException::new);
        //
        //  history.doReturn();
        // userLoanHistoryRepository.save(history);  생략가능 : 영속성 컨텍스트 효과 -> Transactional
    }
}
