package eu.busi.projetpizza.dataAcces.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * <b> BaseEntity est la classe représentant une entité mère (laSuperClasse) qui a un comportement particulier </b>
 * <b> qui sera hérite par toutes ses entites  filles. </b>
 *
 * <p>
 * L'avatange est de pouvoir définir qui seront héritées par toutes les classes filles.
 * par contre, ces entitées mères ne sont pas manipulables.
 * </p>
 *
 * @author Eric Nyandwi
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}