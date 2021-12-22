package br.com.rh.batidas.model.enums;

import br.com.rh.batidas.service.strategy.Registro;
import br.com.rh.batidas.service.strategy.RegistroEntrada;
import br.com.rh.batidas.service.strategy.RegistroIntervalo;
import br.com.rh.batidas.service.strategy.RegistroSaida;

public enum TipoRegistroPonto {

    ENTRADA {
        @Override
        public Registro registroDaBatida() {
            return new RegistroEntrada();
        }
    },
    INTERVALO {
        @Override
        public Registro registroDaBatida() {
            return new RegistroIntervalo();
        }
    },
    SAIDA {
        @Override
        public Registro registroDaBatida() {
            return new RegistroSaida();
        }
    };

    public abstract Registro registroDaBatida();
}
