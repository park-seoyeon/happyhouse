package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dao.HouseMapDao;

@Service
public class HouseMapServiceImpl implements HouseMapService {
   
   @Autowired
   private SqlSession session;

   @Override
   public List<SidoGugunCodeDto> getSido(){
      System.out.println("???");
      List<SidoGugunCodeDto> list = session.getMapper(HouseMapDao.class).getSido();
      System.out.println(list.size());
      for(SidoGugunCodeDto dto : list) {
         System.out.println(dto);
      }
      return session.getMapper(HouseMapDao.class).getSido();
   }

   @Override
   public List<SidoGugunCodeDto> getGugunInSido(String sido) {
      return session.getMapper(HouseMapDao.class).getGugunInSido(sido);
   }

   @Override
   public List<HouseInfoDto> getDongInGugun(String gugun) {
      return session.getMapper(HouseMapDao.class).getDongInGugun(gugun);
   }

   @Override
   public List<HouseInfoDto> getAptInDong(String dong) {
      return session.getMapper(HouseMapDao.class).getAptInDong(dong);
   }

   @Override
   public List<HouseInfoDto> getAptByName(String aptName, String dong) {
      Map<String, String> m = new HashMap<>();
      m.put("aptName", aptName);
      m.put("dong", dong);
      return session.getMapper(HouseMapDao.class).getAptByName(m);
   }

   @Override
   public String getCodeByName(String dong) {
      return session.getMapper(HouseMapDao.class).getCodeByName(dong);
   }
}