package com.ssafy.happyhouse.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapDao {

   List<SidoGugunCodeDto> getSido();
   List<SidoGugunCodeDto> getGugunInSido(String sido);
   List<HouseInfoDto> getDongInGugun(String gugun);
   List<HouseInfoDto> getAptInDong(String dong);
   List<HouseInfoDto> getAptByName(Map<String, String> m);
   String getCodeByName(String dong);
}