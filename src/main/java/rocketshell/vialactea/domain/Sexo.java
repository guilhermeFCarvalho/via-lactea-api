package rocketshell.vialactea.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sexo {
    FEMEA("Fêmea"),
    MACHO("Macho");
    private final String description;
}
