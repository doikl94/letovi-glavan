package com.example.letovi.service;

import com.amadeus.exceptions.ResponseException;
import com.example.letovi.models.Let;

import java.time.LocalDate;
import java.util.List;

public interface LetService {
    List<Let> checkExisting(String polazniAerodrom, String odredisniAerodrom, LocalDate datumPolaska, LocalDate datumPovratka, Integer brojPutnika, String valuta);
    List<Let> checkNew(String polazniAerodrom, String odredisniAerodrom, LocalDate datumPolaska, LocalDate datumPovratka, Integer brojPutnika, String valuta) throws ResponseException;
}
