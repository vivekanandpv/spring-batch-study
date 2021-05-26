package in.athenaeum.springbatchstudy.repository;

import in.athenaeum.springbatchstudy.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car, Integer> {
}
