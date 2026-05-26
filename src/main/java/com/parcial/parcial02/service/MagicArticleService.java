package com.parcial.parcial02.service;

import com.parcial.parcial02.repository.MagicArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicArticleService {
    private MagicArticleRepository articleRepository;


}
