package nguyen.shoestore.Repository;

import nguyen.shoestore.Entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepo extends JpaRepository<Process, Integer> {

    Process getByProcessName(String processName);
}
