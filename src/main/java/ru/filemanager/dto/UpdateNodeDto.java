package ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.filemanager.domain.NodeType;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class UpdateNodeDto {
    @NotNull
    private String name;
}
