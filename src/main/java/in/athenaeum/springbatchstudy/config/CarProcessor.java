package in.athenaeum.springbatchstudy.config;

import in.athenaeum.springbatchstudy.model.Car;
import in.athenaeum.springbatchstudy.viewmodel.CarLoadViewModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CarProcessor implements ItemProcessor<CarLoadViewModel, Car> {

    @Override
    public Car process(CarLoadViewModel carLoadViewModel) throws Exception {
        Car newCar = new Car();
        BeanUtils.copyProperties(carLoadViewModel, newCar);
        return newCar;
    }
}
