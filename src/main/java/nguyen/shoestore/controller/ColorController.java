package nguyen.shoestore.controller;

import nguyen.shoestore.Dto.ResponseDTO;
import nguyen.shoestore.Entity.Color;
import nguyen.shoestore.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/color")
public class ColorController {
    private ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> getAvailableColors() {
        return colorService.getAllColorActive();
    }

    @GetMapping(value = "/allcolor")
    public List<Color> getAllColor() {
        return colorService.getAllColors();
    }

    @GetMapping(value = "/colordeleted")
    public List<Color> getUnAvailableColors() {
        return colorService.getAllColorsUnActive();
    }

    @GetMapping(value = "/id")
    public Optional<Color> findColorById(@RequestParam Integer colorId) {
        return colorService.findColorById(colorId);
    }

    @GetMapping(value = "/name")
    public Color findColorByName(@RequestParam String colorName) {
        return colorService.findColorByName(colorName);
    }

    @PostMapping(value = "/add")
    public ResponseDTO addColor(@RequestBody String colorName) {
        ResponseDTO response = new ResponseDTO();
        response = colorService.AddColor(colorName);
        return response;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseDTO deleteColor(@PathVariable(name = "id") Integer colorId) {
        ResponseDTO response = new ResponseDTO();
        response = colorService.DeleteColor(colorId);
        return response;
    }
}
