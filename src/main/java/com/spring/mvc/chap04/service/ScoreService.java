package com.spring.mvc.chap04.service;


import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreMapper;
import com.spring.mvc.chap04.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//컨트롤러와 레파지토리 사이에서 비즈니스 로직 처리
//ex) 트랜잭션 처리, 예외처리, dto 변환 처리

//@RequiredArgsConstructor
@Service //빈 등록
public class ScoreService { //메모리 저장에서 db저장으로 바껴도 다른곳 안 고치고 여기만 고치면 됨 -> 개방폐쇠원칙
    //원래같으면 생성자 주입 받아야 하므로
     //생성자 만들고 @AutoWired 해야하는데 생성자 하나면 @AutoWired안해도 되고 롬복으로 생성자 만들었으므로 끝
//    private final ScoreRepository scoreRepository;

    private final ScoreMapper scoreRepository; //MyBatis


    @Autowired
    public ScoreService(ScoreMapper scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
    //목록조회
    /*
    * 컨트롤러는 데이터베이스를 통해 성적정보 리스트르 가져오길 원함.
    * 그런데 db는 성적정보를 전부 모아서 줌
    * 컨트롤러는 정보를 일부만 받길 원함 -> 이럴 때 서비스 사용
    * */
    public List<ScoreListResponseDTO> getList(String sort){ //학번순, 이름순, 평균순으로 정렬

        // scoreList에서 원하는 정보만 추출하고 이름을 마스킹해서
        // 다시 DTO리스트로 변환해줘야 한다.
        return scoreRepository.findAll(sort).stream()
                .map(ScoreListResponseDTO::new)
                .collect(Collectors.toList());
    }

    //등록 중간처리
    //컨트롤러는 나에게 DTO를 줬지만
    //레파지토리는 ScoreEntity를 달라고한다.
    //내가 변환해야겠네
    public boolean insertScore(ScoreRequestDTO dto) {
        //dto(ScoreDTO)를 entity(Score)로 변환해야함.
        //save 명령
        return scoreRepository.save(new Score(dto));
    }

    //삭제 중간처리
    public boolean delte(int stuNum){
//나중에 중간처리 할 거 있으면 여기에 추가하면 됨
//ex) 게시물 지울 때 글 지우기 전에 댓글, 첨부파일 등을 먼저 지우고 글 지워야 할 때
        return scoreRepository.deleteByStuNum(stuNum);
    }

    //상세조회, 수정화면조회 중간처리
    public Score retrieve(int stuNum){
        //만약에 스코어 전체d말고 몇개만 추리고 전후처리해서줘라
        return scoreRepository.findByStuNum(stuNum);
    }

}
