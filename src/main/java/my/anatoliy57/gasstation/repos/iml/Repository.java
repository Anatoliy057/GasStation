package my.anatoliy57.gasstation.repos.iml;

import java.util.List;

public interface Repository<ID, ENTITY> {

    ENTITY findById(ID id);

    List<ENTITY> findAll();

    ENTITY save(ENTITY order);

    void deleteById(ID id);
}
