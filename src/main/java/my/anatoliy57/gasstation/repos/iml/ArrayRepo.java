package my.anatoliy57.gasstation.repos.iml;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrayRepo<V> implements Repository<Long, V> {

    private final Map<Long, V> repo;

    public ArrayRepo() {
        this.repo = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public V findById(Long id) {
        return get(Math.toIntExact(id));
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(repo.values());
    }

    @SneakyThrows
    @Override
    public V save(V v) {
        int id = repo.size();
        Field fieldId = v.getClass().getDeclaredField("id");
        fieldId.setAccessible(true);
        fieldId.set(v, (long) id);
        repo.put((long) id, v);
        return v;
    }

    @Override
    public void deleteById(Long id) {
        V deleted = get(Math.toIntExact(id));
        if (Objects.nonNull(deleted)) {
            repo.remove(id);
        }
    }

    protected V findByPredicate(Predicate<V> predicate) {
        return repo.values().stream().filter(predicate).findFirst().orElse(null);
    }

    protected List<V> findAllByPredicate(Predicate<V> predicate) {
        return repo.values().stream().filter(predicate).collect(Collectors.toList());
    }

    private V get(int index) {
        if(index < 0 || index >= repo.size()) {
            return null;
        }
        return repo.get((long) index);
    }
}
