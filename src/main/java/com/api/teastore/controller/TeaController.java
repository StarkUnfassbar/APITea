package com.api.teastore.controller;

import com.api.teastore.dto.TeaCreateDto;
import com.api.teastore.dto.TeaDto;
import com.api.teastore.dto.TeaDtoExtended;
import com.api.teastore.dto.TeaResponseDto;
import com.api.teastore.exceptions.TeaControllerExceptionHandler;
import com.api.teastore.exceptions.TeaNotFoundException;
import com.api.teastore.models.Tea;
import com.api.teastore.service.TeaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "v1/api/tea")
@TeaControllerExceptionHandler
@RestController
public class TeaController {
    private final TeaService teaService;
    private final ModelMapper teaMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TeaDto> getTea() {
        List<Tea> teaList = teaService.getTea();
        List<TeaDto> teaListDto = new ArrayList<>();

        for (Tea tea : teaList) {
           TeaDto taskDto = teaMapper.map(tea, TeaDto.class);
            teaListDto.add(taskDto);
        }
        if (teaListDto.isEmpty()) {
            throw new TeaNotFoundException("Список чая пуст");
        }

        return teaListDto;
    }


    @GetMapping(path = "{teaId}")
    @ResponseStatus(HttpStatus.OK)
    public TeaDtoExtended getTea(@PathVariable long teaId) {
        Tea tea = teaService.findTeaById(teaId);
        TeaDtoExtended teaDto = teaMapper.map(tea, TeaDtoExtended.class);

        return teaDto;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TeaResponseDto CreateTask(@RequestBody TeaCreateDto teaCreateDto) {
        Tea newTea = teaMapper.map(teaCreateDto, Tea.class);

        Long teaId = teaService.addNewTea(newTea);

        return new TeaResponseDto().setId(teaId);
    }

    @PutMapping(path = "{teaId}")
    @ResponseStatus(HttpStatus.OK)
    public TeaDtoExtended patchTea (@PathVariable("teaId") long teaId, @RequestBody TeaCreateDto teaCreateDto){
       teaService.putTea(teaId,teaCreateDto);
       return teaMapper.map(teaService.findTeaById(teaId),TeaDtoExtended.class);
    }

    @DeleteMapping(path = "{teaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeaResponseDto deleteTea (@PathVariable("teaId") long teaId){
        teaService.deleteTea(teaId);
        return new TeaResponseDto().setId(teaId);
    }
}
