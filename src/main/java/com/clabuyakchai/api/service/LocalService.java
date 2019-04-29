package com.clabuyakchai.api.service;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;

import java.util.List;

public interface LocalService {
    List<Local> getAll();
    String signInUp(LocalDTO localDTO);
    LocalDTO getLocalByPhone(String phone);
    LocalDTO updateLocal(LocalDTO localDTO);
    void deleteLocalByPhone(String phone);
}
