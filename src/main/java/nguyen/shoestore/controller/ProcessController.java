package nguyen.shoestore.controller;

import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Process;
import nguyen.shoestore.Service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/status")
public class ProcessController {
    private ProcessService processService;
    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }
    @GetMapping
    public List<Process> getAllProcess() {
        return processService.getAllProcess();
    }
    @GetMapping(value = "/id")
    public Optional<Process> findProcessById(@RequestParam Integer processId) {
        return processService.findProcessById(processId);
    }
    @GetMapping(value = "/name")
    public Process findProcessByName(@RequestParam String processName) {
        return processService.findProcessByName(processName);
    }
    @PostMapping(value = "/add")
    public ResponseDTO addProcess(@RequestBody String processName){
        ResponseDTO response = new ResponseDTO();
        response = processService.AddProcess(processName);
        return response;
    }
}
