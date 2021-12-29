package nguyen.shoestore.Service;

import nguyen.shoestore.Entity.Process;
import nguyen.shoestore.common.MessageUtils;
import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Repository.ProcessRepo;
import nguyen.shoestore.Repository.PurchaseOrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessService {
    private final ProcessRepo processRepo;
    private final PurchaseOrderRepo orderRepo;

    public ProcessService(ProcessRepo processRepo, PurchaseOrderRepo orderRepo) {
        this.processRepo = processRepo;
        this.orderRepo = orderRepo;
    }
    public List<Process> getAllProcess() {
        return processRepo.findAll();
    }
    public Optional<Process> findProcessById(Integer processId) {
        return processRepo.findById(processId);
    }
    public Process findProcessByName(String processName){
        return processRepo.getByProcessName(processName);
    }
    @Transactional
    public ResponseDTO AddProcess(String processName){
        ResponseDTO responseDTO = new ResponseDTO();
        Process process = processRepo.getByProcessName(processName);
        Assert.isNull(process, MessageUtils.getMessage("error.notfound",processName));
        processRepo.save(process);
        return responseDTO;
    }
}
