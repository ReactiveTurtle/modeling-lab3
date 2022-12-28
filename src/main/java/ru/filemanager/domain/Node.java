package ru.filemanager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.filemanager.dto.CreateNodeDto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Node {
    @Id
    @GeneratedValue
    @Column(nullable = false, insertable = false, updatable = false)
    private UUID nodeId;

    @NotNull
    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(updatable = false)
    private String path;

    @Setter
    @NotNull
    @Column(nullable = false, updatable = false)
    private NodeType type;

    @Setter
    @NotNull
    @Column(nullable = false, updatable = false)
    private Date creationDateTime;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_node_id")
    private Node parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.REMOVE})
    private List<Node> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeId != null && Objects.equals(nodeId, node.nodeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
