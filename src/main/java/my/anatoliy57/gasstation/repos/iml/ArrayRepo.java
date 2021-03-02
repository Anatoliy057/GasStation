package my.anatoliy57.gasstation.repos.iml;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayRepo<V> implements Repository<Long, V> {

    private final ArrayList<V> repo;

    public ArrayRepo() {
        this.repo = new ArrayList<>();
    }

    @Override
    public V findById(Long id) {
        return get(Math.toIntExact(id));
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(repo);
    }

    @SneakyThrows
    @Override
    public V save(V v) {
        int id = repo.size();
        Field fieldId = v.getClass().getDeclaredField("id");
        fieldId.setAccessible(true);
        fieldId.set(v, (long) id);
        repo.add(v);
        return v;
    }

    @Override
    public void deleteById(Long id) {
        V deleted = get(Math.toIntExact(id));
        if (Objects.nonNull(deleted)) {
            repo.remove(Math.toIntExact(id));
            repo.trimToSize();
        }
    }

    protected V findByPredicate(Predicate<V> predicate) {
        return repo.stream().filter(predicate).findFirst().orElse(null);
    }

    private V get(int index) {
        if(index < 0 || index >= repo.size()) {
            return null;
        }
        return repo.get(index);
    }
}
