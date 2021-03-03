package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapService {
   
   List<SidoGugunCodeDto> getSido();
   List<SidoGugunCodeDto> getGugunInSido(String sido);
   List<HouseInfoDto> getDongInGugun(String gugun);
   List<HouseInfoDto> getAptInDong(String dong);
   List<HouseInfoDto> getAptByName(String aptName, String dong);
   String getCodeByName(String dong);
}