package ru.skypro.homework.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdCreation;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public AdDto createAd(AdCreation request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        Ad ad = new Ad();
        ad.setTitle(request.getTitle());
        ad.setDescription(request.getDescription());
        ad.setPrice(request.getPrice());
        ad.setUser(user);

        Ad savedAd = adRepository.save(ad);
        return toDto(savedAd);
    }

    @Override
    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdDto> getUserAds(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return adRepository.findByUserId(user.getId()).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdDto updateAd(Long adId, AdCreation request, String username) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        if (!ad.getUser().getEmail().equals(username)) {
            throw new SecurityException("Доступ запрещен");
        }

        ad.setTitle(request.getTitle());
        ad.setDescription(request.getDescription());
        ad.setPrice(request.getPrice());
        Ad updatedAd = adRepository.save(ad);

        return toDto(updatedAd);
    }

    @Override
    public void deleteAd(Long adId, String username) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено"));

        if (!ad.getUser().getEmail().equals(username)) {
            throw new SecurityException("Доступ запрещен");
        }

        adRepository.delete(ad);
    }

    private AdDto toDto(Ad ad) {
        AdDto dto = new AdDto();
        dto.setId(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setPrice(ad.getPrice());
        dto.setImageUrl(ad.getImageUrl());
        return dto;
    }
}