package nguyen.shoestore.Repository;


import nguyen.shoestore.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepo extends JpaRepository<Color, Integer> {
    List<Color> findByStatus(Integer status);
    Color getByColorName(String colorName);
}
