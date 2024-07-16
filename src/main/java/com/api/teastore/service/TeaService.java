package com.api.teastore.service;


import com.api.teastore.dto.TeaCreateDto;
import com.api.teastore.dto.TeaDtoExtended;
import com.api.teastore.exceptions.TeaCreateException;
import com.api.teastore.exceptions.TeaNotFoundException;
import com.api.teastore.models.Tea;
import com.api.teastore.repository.TeaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeaService {
    private final TeaRepository teaRepository;

    public List<Tea> getTea() {
        return teaRepository.findAll();
    }

    public Tea findTeaById(long teaId) {
        return teaRepository.findById(teaId)
                .orElseThrow(() -> new TeaNotFoundException("Чай с id " + teaId + " отсутствует"));
    }

    public Long addNewTea(Tea newTea) {
        if(newTea.getName().strip().isEmpty()){
            throw new TeaCreateException("Поле названия не должно быть пустым");
        }
        if(newTea.getDescription().strip().isEmpty()){
            throw new TeaCreateException("Поле описания не должно быть пустым");
        }
        if(newTea.getPrice().toString().strip().isEmpty()){
            throw new TeaCreateException("Поле цены не должно быть пустым");
        }
        if(newTea.getCountry().strip().isEmpty()){
            throw new TeaCreateException("Поле Страны не должно быть пустым");
        }

        return teaRepository.save(newTea).getId();
    }

    @Transactional
    public void putTea(long teaId, TeaCreateDto teaCreateDto) {
      Tea tea = findTeaById(teaId);

      if(!teaCreateDto.getName().strip().isEmpty()) {
          tea.setName(teaCreateDto.getName());
      }
      if(!teaCreateDto.getDescription().strip().isEmpty()) {
          tea.setDescription(teaCreateDto.getDescription());
      }
      if(!teaCreateDto.getPrice().toString().strip().isEmpty()) {
          tea.setPrice(teaCreateDto.getPrice());
      }
      if(!teaCreateDto.getCountry().strip().isEmpty()) {
          tea.setCountry(teaCreateDto.getCountry());
      }

      teaRepository.save(tea);
    }

    public void deleteTea(long teaId) {
        Tea tea = findTeaById(teaId);
        teaRepository.delete(tea);
    }
}
