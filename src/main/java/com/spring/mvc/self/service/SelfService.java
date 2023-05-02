package com.spring.mvc.self.service;

import com.spring.mvc.self.dto.RequestDTO;
import com.spring.mvc.self.dto.ResponseDTO;
import com.spring.mvc.self.dto.SelfDetailDTO;
import com.spring.mvc.self.entity.Self;
import com.spring.mvc.self.repository.SelfMapper;
import com.spring.mvc.self.repository.SelfRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class SelfService {

    private final SelfRepositoryImpl selfRepository;

    public List<ResponseDTO> getList(){
        return selfRepository.findAll()
                .stream().map(self->new ResponseDTO(self))
                .collect(toList());// entity에서 dto로 변경해야함
    }

    public boolean save(RequestDTO dto){
        return selfRepository.save(new Self(dto));
    }

    public ResponseDTO findOne(int boardNo){
        Self one = selfRepository.findOne(boardNo);
        int viewCount=one.getViewCount();
        one.setViewCount(++viewCount);
        return new ResponseDTO(one);
    }

    public boolean modify(RequestDTO dto){
        return selfRepository.modify(dto);
    }
}
