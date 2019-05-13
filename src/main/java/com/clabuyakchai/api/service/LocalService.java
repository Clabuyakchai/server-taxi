package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LocalService {
    List<Local> getAll();
    String signIn(String phone);
    String signUp(LocalDTO localDTO);
    LocalDTO getLocalByPhone(HttpServletRequest req);
    LocalDTO updateLocal(LocalDTO localDTO);
    void deleteLocalByID(Long localID);
    List<LocalDTO> findLocalByTimetableID(Long timetableID);
}
