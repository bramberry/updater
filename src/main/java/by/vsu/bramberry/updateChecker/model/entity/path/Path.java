package by.vsu.bramberry.updateChecker.model.entity.path;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "path")
public class Path implements Serializable {
    @JsonIgnore
    private Long id;
    private String path;

    public Path() {
    }

    public Path(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path1 = (Path) o;
        return Objects.equals(id, path1.id) &&
                Objects.equals(path, path1.path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
