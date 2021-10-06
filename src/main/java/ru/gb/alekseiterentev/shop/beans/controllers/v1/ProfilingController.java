package ru.gb.alekseiterentev.shop.beans.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.alekseiterentev.shop.profiling.ProfilingAspect;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profiling")
public class ProfilingController {

    private final ProfilingAspect profilingAspect;

    @GetMapping
    public Map<String, Long> getProfilingData() {
        return profilingAspect.getProfilingData();
    }
}
