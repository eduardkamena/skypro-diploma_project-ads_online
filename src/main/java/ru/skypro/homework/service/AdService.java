package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entity.AdCreation;

import java.util.List;

public interface AdService {
    AdDto createAd(AdCreation request, String username);
    List<AdDto> getAllAds();
    List<AdDto> getUserAds(String username);
    AdDto updateAd(Long adId, AdCreation request, String username);
    void deleteAd(Long adId, String username);
}
