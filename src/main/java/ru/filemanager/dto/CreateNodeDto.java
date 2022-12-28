package ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public abstract class CreateNodeDto {
    @NotNull
    private UUID parentId;
    @NotNull
    private String name;
}
