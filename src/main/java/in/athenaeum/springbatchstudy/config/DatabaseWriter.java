package in.athenaeum.springbatchstudy.config;

import in.athenaeum.springbatchstudy.model.Car;
import in.athenaeum.springbatchstudy.repository.ICarRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseWriter implements ItemWriter<Car> {
    private final ICarRepository repository;

    public DatabaseWriter(ICarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(List<? extends Car> cars) throws Exception{
        repository.saveAllAndFlush(cars);
    }
}
