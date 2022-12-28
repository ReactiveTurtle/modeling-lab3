package ru.filemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.filemanager.domain.Node;
import ru.filemanager.domain.NodeType;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeDto {
    public UUID id;
    @NotNull
    public String name;
    @NotNull
    public NodeType type;
    @NotNull
    public Date creationDateTime;
    public String size;

    public NodeDto(Node node) {
        this.id = node.getNodeId();
        this.name = node.getName();
        this.type = node.getType();
        this.creationDateTime = node.getCreationDateTime();
    }
}
