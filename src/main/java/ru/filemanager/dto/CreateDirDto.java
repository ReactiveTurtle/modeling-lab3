package ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
public class CreateDirDto extends CreateNodeDto {
    public CreateDirDto(@NotNull UUID parentId, @NotNull String name) {
        super(parentId, name);
    }
}
