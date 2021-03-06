package com.clabuyakchai.api.controller;

import com.clabuyakchai.api.dto.LocalDTO;
import com.clabuyakchai.api.model.Local;
import com.clabuyakchai.api.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/local")
@RestController
public class LocalController {

    private LocalService localService;

    @Autowired
    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping(value = "/all")
    public List<Local> getAll(){
        return localService.getAll();
    }

    @GetMapping(value = "/signin")
    public String signIn(@RequestParam String phone){
        return localService.signIn(phone);
    }

    /*
    {
            "localID" : "1"
          "phone" : "375441234567",
          "email" : "chai@gmail.com",
          "gender" : "male",
          "name" : "Roman"
}
     */

    @PostMapping(value = "/signup")
    public String signUp(@RequestBody LocalDTO localDTO){
        return localService.signUp(localDTO);
    }

    @GetMapping(value = "/me")
    public LocalDTO getLocal(HttpServletRequest req){
        return localService.getLocalByPhone(req);
    }

    @PostMapping(value = "/update")
    public LocalDTO updateLocal(@RequestBody LocalDTO localDTO){
        return localService.updateLocal(localDTO);
    }

    @GetMapping(value = "/delete")
    public void deleteLocal(@RequestParam Long localID){
        localService.deleteLocalByID(localID);
    }

    @GetMapping(value = "/localbytimetable")
    public List<LocalDTO> findLocalByTimetableID(@RequestParam Long timetableID){
        return localService.findLocalByTimetableID(timetableID);
    }
}
