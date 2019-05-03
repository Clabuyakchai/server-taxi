package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;

import java.util.List;

public interface LocalService {
    List<Local> getAll();
    String signIn(String phone);
    String signUp(LocalDTO localDTO);
    LocalDTO getLocalByPhone(String phone);
    LocalDTO updateLocal(LocalDTO localDTO);
    void deleteLocalByID(Long localID);
    List<LocalDTO> findLocalByTimetableID(Long timetableID);
}
