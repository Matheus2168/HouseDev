package pl.hotowy.housingdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import pl.hotowy.housingdev.model.Flat;
import pl.hotowy.housingdev.model.Habitant;
import pl.hotowy.housingdev.model.House;
import pl.hotowy.housingdev.model.HousingCommunity;
import pl.hotowy.housingdev.service.DatabaseOperator;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private DatabaseOperator operator;



    @GetMapping("/")
    public String index(){
        prepareDb();

        return "index";
    }


    private void prepareDb(){
        operator.save(new HousingCommunity());
        operator.save(new House());
        operator.save(new Flat());
        operator.save(new Habitant());

        HousingCommunity housingCommunity = operator.selectHousingCommunity("1");
        housingCommunity.setName("Jagoda");
        housingCommunity.setHouse(operator.selectHouse("1"));
        operator.update(housingCommunity);

    }
}
