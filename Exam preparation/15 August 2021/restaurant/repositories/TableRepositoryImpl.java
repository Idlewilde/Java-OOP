package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Map<Integer,Table> tableRepository;

    public TableRepositoryImpl() {
        this.tableRepository = new LinkedHashMap<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(this.tableRepository.values());
    }

    @Override
    public void add(Table entity) {
    this.tableRepository.put(entity.getTableNumber(),entity);
    }

    @Override
    public Table byNumber(int number) {
        return tableRepository.get(number);
    }
}
