package my.anatoliy57.gasstation.repos.iml;

import java.util.List;

public interface Repository<ID, ENTITY> {

    ENTITY findById(ID id);

    List<ENTITY> findAll();

    ENTITY save(ENTITY order);

    void deleteById(ID id);

    default ENTITY removeById(ID id) {
        ENTITY e = findById(id);
        deleteById(id);
        return e;
    }
}
