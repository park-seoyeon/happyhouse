package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@CrossOrigin("*")
@RestController
@RequestMapping("/map")
public class HouseMapController {
   
   @Autowired
   private HouseMapService houseMapService;
   
   @GetMapping(value = "/sido")
   public Map<String, Object> sido() {
      Map<String, Object> map = new HashMap<>();
      System.out.println("method sido");
      try {
         List<SidoGugunCodeDto> sidos = houseMapService.getSido();
         map.put("status", "SUCCESS");
         map.put("data", sidos);
         System.out.println(sidos.size());
      } catch (Exception e) {
         System.out.println(e.getMessage());
         map.put("status", "FAIL");
         map.put("data", e.getMessage());
      }
      return map;
   }
   
   @GetMapping(value = "/gugun/{sidoname}")
   public Map<String, Object> gugun(@PathVariable("sidoname") String sido) {
      Map<String, Object> map = new HashMap<>();
      System.out.println("method gugun, sido : " + sido);
      try {
         List<SidoGugunCodeDto> guguns = houseMapService.getGugunInSido(sido);
         map.put("status", "SUCCESS");
         map.put("data", guguns);
      } catch (Exception e) {
         map.put("status", "FAIL");
         map.put("data", e.getMessage());
      }
      
      return map;
   }
   
   @GetMapping(value = "/dong/{gugunname}")
   public Map<String, Object> dong(@PathVariable("gugunname") String gugun) {
      Map<String, Object> map = new HashMap<>();
      System.out.println("method dong, gugun : " + gugun);
      try {
         List<HouseInfoDto> dongs = houseMapService.getDongInGugun(gugun);
         map.put("status", "SUCCESS");
         map.put("data", dongs);
      } catch (Exception e) {
         map.put("status", "FAIL");
         map.put("data", e.getMessage());
      }
      
      return map;
   }
   
   @GetMapping(value = "/apt/{dongname}")
   public Map<String, Object> apt(@PathVariable("dongname") String dong) {
      Map<String, Object> map = new HashMap<>();
      System.out.println("method apt, dong : " + dong);
      try {
         List<HouseInfoDto> apts = houseMapService.getAptInDong(dong);
         int len = apts.size();
         HouseInfoDto temp;
         for(int i = 0; i < len; i++) {
            temp = apts.get(i);
            if(temp.getType().equals("A"))
               apts.get(i).setType("아파트");
            else
               apts.get(i).setType("주택");               
         }
         map.put("status", "SUCCESS");
         map.put("data", apts);
      } catch (Exception e) {
         map.put("status", "FAIL");
         map.put("data", e.getMessage());
      }
      return map;
   }
   
   @GetMapping(value = "/apt/{dongname}/{aptName}")
   public Map<String, Object> aptName(@PathVariable("dongname") String dong, @PathVariable("aptName") String aptName) {
      Map<String, Object> map = new HashMap<>();
      System.out.println("method aptName, aptName : " + aptName + ", dong : " + dong);
      try {
         List<HouseInfoDto> apts = houseMapService.getAptByName(aptName, dong);
         int len = apts.size();
         HouseInfoDto temp;
         for(int i = 0; i < len; i++) {
            temp = apts.get(i);
            if(temp.getType().equals("A"))
               apts.get(i).setType("아파트");
            else
               apts.get(i).setType("주택");               
         }
         map.put("status", "SUCCESS");
         map.put("data", apts);
      } catch (Exception e) {
         map.put("status", "FAIL");
         map.put("data", e.getMessage());
      }
      return map;
   }
   
   @GetMapping(value= "/dongcode/{dong}")
//   public Map<String, Object> dongCodeByName(@PathVariable("dong") String dong) {
   public String dongCodeByName(@PathVariable("dong") String dong) {
      System.out.println("method dong code by dong name : " + dong);
//      Map<String, Object> map = new HashMap<>();
      try {
         String code = houseMapService.getCodeByName(dong);
//         map.put("status", "SUCCESS");
//         map.put("data", code);
         return code;
      } catch (Exception e) {
//         map.put("status", "FAIL");
//         map.put("data", e.getMessage());
         return "FAIL";
      }
//      return map;
   }

}