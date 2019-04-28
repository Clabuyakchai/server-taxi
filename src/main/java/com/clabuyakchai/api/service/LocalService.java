package com.clabuyakchai.api.service;

import com.clabuyakchai.api.model.Local;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocalService {
    List<Local> getAll();
}
