package my.anatoliy57.gasstation.repos.iml;

import lombok.SneakyThrows;
import my.anatoliy57.gasstation.domain.entity.Order;
import my.anatoliy57.gasstation.util.IdGenerator;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayRepo<V> implements Repository<Long, V> {

    protected final Map<Long, V> repo;
    private final IdGenerator idGenerator;

    public ArrayRepo() {
        this.repo = Collections.synchronizedMap(new HashMap<>());
        idGenerator = new IdGenerator();
    }

    @Override
    public V findById(Long id) {
        return get(id);
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(repo.values());
    }

    @SneakyThrows
    @Override
    public V save(V v) {
        long id = idGenerator.generate();
        Field fieldId = v.getClass().getDeclaredField("id");
        fieldId.setAccessible(true);
        fieldId.set(v, id);
        repo.put(id, v);
        return v;
    }

    @Override
    public void deleteById(Long id) {
        repo.remove(id);
    }

    protected V findByPredicate(Predicate<V> predicate) {
        return repo.values().stream().filter(predicate).findFirst().orElse(null);
    }

    protected List<V> findAllByPredicate(Predicate<V> predicate) {
        return repo.values().stream().filter(predicate).collect(Collectors.toList());
    }

    private V get(long id) {
        return repo.get(id);
    }
}
