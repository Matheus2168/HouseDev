package pl.hotowy.housingdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hotowy.housingdev.model.*;
import pl.hotowy.housingdev.service.DatabaseOperator;

import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private DatabaseOperator operator;



    @GetMapping("/")
    public String index(Model model){


        List<HousingCommunity> housingCommunities = operator.selectAllHousingCommunities();
        for (HousingCommunity housingCommunity : housingCommunities) {
            //List<Flat> flats1 = housingCommunity.getHouse().getFlats();
            //for (Flat flat : flats1) {
              //  System.out.println(flat);
              //  List<Habitant> habitants = flat.getHabitants();
              //  for (Habitant habitant : habitants) {

              //  }
           // }
        }


        model.addAttribute("housingCommunities",housingCommunities);
        return "index";
    }

    @GetMapping("/hc/{id}")
    public String hcInfo(@PathVariable Long id, Model model){
        HousingCommunity toShow = operator.selectHousingCommunity(id.toString());

                //BUGFIX                            (when 2 habitants with same firstName and LastName was added to one Flat
        Set<Flat> set = new TreeSet<>();          //( /hc/{id} shows duplicated flat when database workbench shows proper amount
        set.addAll(toShow.getHouse().getFlats());
        ArrayList<Flat> bugFix = new ArrayList<>();
        bugFix.addAll(set);
        toShow.getHouse().setFlats(bugFix);
                //BUGFIX

        double totalArea = 0;
        int totalHabitants = 0;
        for (Flat flat : toShow.getHouse().getFlats()) {
            totalArea+= flat.getArea();
            List<Habitant> habitants = flat.getHabitants();
            for (Habitant habitant : habitants) {
                totalHabitants+=1;
            }
        }
        model.addAttribute("totalHabitants",totalHabitants);
        model.addAttribute("totalArea",totalArea);
        model.addAttribute("hc",toShow);
        return "hcInfo";
    }

    @GetMapping("/hc/{id}/flat/{idF}")
    public String flatInfo(@PathVariable long id, @PathVariable Long idF, Model model){

        Flat flat = operator.selectFlat(idF.toString());
        model.addAttribute("flat",flat);

        List<Habitant> habitants = flat.getHabitants();
        model.addAttribute("habitants",habitants);
        return "flat";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<Object> objects = operator.selectAll(House.class);
        List<Flat> flats = (List<Flat>)(Object) operator.selectAll(Flat.class);
        System.out.println(flats.size());

        List<House> list =  (List<House>)(Object) objects;




        model.addAttribute("houses", list);


        model.addAttribute("flats", flats);
        model.addAttribute("genders", Gender.values());
        return "add";
    }

    @PostMapping("/add/hc")
    public String addHc(Model model,
                        @RequestParam(value = "name") String name,
                        @RequestParam(value = "house",required = true) Long idHouse){
        HousingCommunity hc = new HousingCommunity();
        hc.setName(name);
        hc.setHouse(operator.selectHouse(idHouse.toString()));
        operator.save(hc);

        return "redirect:/add";
    }

    @PostMapping("/add/house")
    public String addHouse(Model model,
                        @RequestParam(value = "adress") String adress){
        House house = new House();
        house.setAdress(adress);

        operator.save(house);

        return "redirect:/add";
    }

    @PostMapping("/add/flat")
    public String addHouse(Model model,
                           @RequestParam(value = "number") int number,
                           @RequestParam(value = "area") String area,
                           @RequestParam(value = "house",required = true) Long idHouse
                                                                    ){
        Flat flat = new Flat();
        flat.setArea(Double.parseDouble(area));
        flat.setNumber(number);
        flat.setHouse(operator.selectHouse(idHouse.toString()));

        operator.save(flat);

        return "redirect:/add";
    }

    @PostMapping("/add/habitant")
    public String addHabitant(Model model,
                           @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "gender",required = true) Gender gender,
                           @RequestParam(value = "flat",required = true) Long flatId
    ){
        Habitant habitant = new Habitant();
        habitant.setFirstName(firstName);
        habitant.setLastName(lastName);
        habitant.setGender(gender);
        habitant.setFlat(operator.selectFlat(flatId.toString()));

        operator.save(habitant);

        return "redirect:/add";
    }

    @GetMapping("/edit")
    public String edit(Model model){

        List<HousingCommunity> hcList =(List<HousingCommunity>)(Object)operator.selectAll(HousingCommunity.class);
        List<House> houseList =(List<House>)(Object)operator.selectAll(House.class);
        List<Flat> flatList =(List<Flat>)(Object)operator.selectAll(Flat.class);
        List<Habitant> habitantList =(List<Habitant>)(Object)operator.selectAll(Habitant.class);

        model.addAttribute("hcList",hcList);
        model.addAttribute("houseList",houseList);
        model.addAttribute("flatList",flatList);
        model.addAttribute("habitantList",habitantList);
        return "edit";
    }

    @PostMapping("/edit/hc")
    public String editHc(Model model,
                                   @RequestParam (value = "id") String hcId){
        System.out.println(hcId);

        List<House> houses = (List<House>)(Object)operator.selectAll(House.class);
        model.addAttribute("hc",operator.selectHousingCommunity(hcId));
        model.addAttribute("houses",houses);



        return "editHc";
    }

    @PostMapping("/edit/hc/success/{id}")
    public String editedHc(@PathVariable Long id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "house") String houseId){

       HousingCommunity hc =operator.selectHousingCommunity(id.toString());
       hc.setName(name);
       if (houseId.equals("null")){
           hc.setHouse(null);
       }
       else {
           hc.setHouse(operator.selectHouse(houseId));
       }

       operator.update(hc);


        return "redirect:/";
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

        House house = operator.selectHouse("1");
        house.setAdress("Kosmonautow 216");
        operator.update(house);

        Flat flat = operator.selectFlat("1");
        flat.setNumber(1);
        flat.setHouse(house);
        flat.setArea(28.5);
        operator.update(flat);

        Habitant habitant = operator.selectHabitant("1");
        habitant.setGender(Gender.MALE);
        habitant.setFlat(flat);
        habitant.setFirstName("Jarek");
        habitant.setLastName("Glogowski");
        operator.update(habitant);

        Habitant hab2 = new Habitant();
        hab2.setFirstName("Anna");
        hab2.setLastName("Manna");
        hab2.setGender(Gender.FEMALE);
        hab2.setFlat(flat);

        operator.save(hab2);

        HousingCommunity test = operator.selectHousingCommunity("1");
        List<Flat> flats = test.getHouse().getFlats();
        System.out.println(flats);



    }
}
