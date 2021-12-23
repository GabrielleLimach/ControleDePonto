package br.com.rh.batidas.controller.registros;

import br.com.rh.batidas.service.RegistroDePontoService;
import br.com.rh.batidas.utils.LocalDateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/batidas")
public class BatidasController {

    private final RegistroDePontoService registroDePontoService;

    @ApiOperation(value = "Rota para Registro de Batidas de Ponto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna Status CREATED"),
            @ApiResponse(code = 400, message = "Data e hora em formato inválido"),
            @ApiResponse(code = 400, message = "Campo obrigatório não informado"),
            @ApiResponse(code = 409, message = "Horários já registrado"),
            @ApiResponse(code = 403, message = "Apenas 4 horários podem ser registrados por dia"),
            @ApiResponse(code = 403, message = "Deve haver no mínimo 1 hora de almoço"),
            @ApiResponse(code = 403, message = "Sábado e domingo não são permitidos como dia de trabalho"),
    })
    @PostMapping
    public ResponseEntity registrarHorario(@RequestParam String dataHora) {
        registroDePontoService.registrarPonto(LocalDateUtils.toConvertStringLocalDateTime(dataHora));
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
