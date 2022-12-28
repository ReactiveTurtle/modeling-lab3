package ru.filemanager.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@Getter
public class CreateFileDto extends CreateNodeDto{
    @NotNull
    private String path;

    public CreateFileDto(
            @NotNull UUID parentId,
            @NotNull String name,
            @NotNull String path
    ) {
        super(parentId, name);
        this.path = path;
    }
}
