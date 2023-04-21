package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


/**
 * # 요청 URL
 * 1. 학생 성적정보 등록화면을 보여주고, 성적정보 목록조회 처리
 * - /score/list : GET
 * <p>
 * 2. 성적 정보 등록 처리 요청
 * - /score/register : POST
 * <p>
 * 3. 성적정보 삭제 요청
 * - /score/remove : POST
 * <p>
 * 4. 성적정보 상세 조회 요청
 * - /score/detail; : GET
 */


@Controller
@RequestMapping("/score")
//@AllArgsConstructor : 모든 필드를 초기화하는 생성자
@RequiredArgsConstructor //final 필드만 초기화하는 생성자
public class ScoreController {

    //저장소에 의존해야 데이터를 받아서 클라에게 응답할 수 있음
    //private final ScoreRepository repository; //scoreService 만들면서 컨트롤러가 repository에 대한 의존성 제거
    private final ScoreService scoreService; //ScoreService 클래스의 @Service를 이용해 빈 등록

    //만약에 클래스의 생성자가 단 1개라면 자동으로 @Autowired를 써줌 -> @Autowired 생략가능
    //밑에 주석처리하고 위에 @AllArgsConstructor or @RequiredArgsConstructor 작성해서 객체 주입받음
//    @Autowired //스프링이 저장소 객체를 주입해줌
//    public ScoreController(ScoreRepository repository) {
//    this.repository = repository;
//    }

    //1. 성적등록화면 띄위기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "num") String sort) {
        System.out.println("/score/list : GET");
        System.out.println("정렬 요구사항 : " + sort);

        List<ScoreListResponseDTO> responseDTOList = scoreService.getList(sort); //db에 있는 모든 정보 //List : 순서가 있음

//        List<ScoreListResponseDTO> responseDTOList = new ArrayList<>();
//        for (Score s : scoreList) {
//            ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
//            responseDTOList.add(dto);
//        }

        model.addAttribute("sList", responseDTOList);
        return "chap04/score-list";
    }

    //    2. 성적 정보 등록 처리 요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {

        //입력데이터(쿼리스트링) 읽기
        System.out.println("/score/register : POST - \n" + dto);

//    dto(ScoreDTO)를 entity(Score)로 변환해야 함.
//    <save 명령>
//    repository.save(score); // ->ScoreService클래스의 insertScore -> 의존성 제거
        scoreService.insertScore(dto);

    //등록 요청에서 JSP 뷰 포워딩을 하면 갱신된 목록을 다시 한번 저장소에서 불러와 모델에 담는 추가적인 코드가 필요하지만
    //리다이렉트를 통해서 위에서 만든 /score/list : GET을 자동으로 다시 보낼 수 있다면 번거로운 코드가 줄어들 수 있겠다.
    //return "redirect:JSP 경로가 아니라 위에서 매핑된 요청RUL을 작성해야 함";
        return "redirect:/score/list";
    }

    //    3.  성적정보 삭제 요청
// *   - /score/remove : POST
    @GetMapping("/remove")
    public String remove(int stuNum) { // = @RequestMapping int stuNum
        System.out.println("/score/remove : GET");

//        repository.deleteByStuNum(stuNum); ->ScoreService클래스의 delte()가 수행하므로써 repository에 대한 의존성 제거
        scoreService.delte(stuNum);
        return "redirect:/score/list";
    }

    // *   4. 성적정보 상세 조회 요청
    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) int stuNum, Model model) {
        System.out.println("/score/detail");
        retrieve(stuNum, model);
        return "chap04/score-detail";
    }

    //5. 수정하기
    @GetMapping("/modify")
    public String modify(int stuNum, Model model) {
        System.out.println("/score/modify : GET");
        retrieve(stuNum, model);
        return "chap04/score-modify";
    }

    private void retrieve(int stuNum, Model model) {
//        Score inform = repository.findByStuNum(stuNum); // -> 밑의 scoreService 클래스가 대신하므로 repository에 대한 의존성 제거
        Score score = scoreService.retrieve(stuNum);
        model.addAttribute("inform", score);
    }

    @PostMapping("/modify")
    public String modify(int stuNum, ScoreRequestDTO dto) {
//        실무적 관점에서 stuNum을 따로받고, kor,eng,math를 따로 받으면 안되고 이걸
//        하나로 묶는 dto를 다시 만들어야 함
        System.out.println("/score/modify : POST");

//        Score score = repository.findByStuNum(stuNum); //repository에 대한 의존선 끊음
        Score score = scoreService.retrieve(stuNum);
        score.changeScore(dto);

        return "redirect:/score/detail?stuNum=" + stuNum;
    }

}
