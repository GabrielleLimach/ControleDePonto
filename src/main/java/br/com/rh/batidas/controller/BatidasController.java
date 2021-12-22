package br.com.rh.batidas.controller;

import br.com.rh.batidas.service.RegistroDePontoService;
import br.com.rh.batidas.utils.LocalDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/batidas")
public class BatidasController {

    private final RegistroDePontoService registroDePontoService;

    @PostMapping
    public ResponseEntity registrarHorario(@RequestParam String dataHora) {
        registroDePontoService.registrarPonto(LocalDateUtils.toConvertStringLocalDateTime(dataHora));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
