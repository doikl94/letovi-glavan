package com.example.letovi.repository;

import com.example.letovi.models.Let;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer> {

    @Query("SELECT l FROM Let l WHERE " +
            "lower(l.polazniAerodrom) = lower(:polazniAerodrom) AND " +
            "lower(l.odredisniAerodrom) = lower(:odredisniAerodrom) AND " +
            "l.datumPolaska = :datumPolaska AND " +
            "l.datumPovratka = :datumPovratka AND " +
            "l.brojPutnika = :brojPutnika AND " +
            "lower(l.valuta) = lower(:valuta)")
    List<Let> checkExisting(@Param("polazniAerodrom") String polazniAerodrom,
                            @Param("odredisniAerodrom") String odredisniAerodrom,
                            @Param("datumPolaska") LocalDate datumPolaska,
                            @Param("datumPovratka") LocalDate datumPovratka,
                            @Param("brojPutnika") Integer brojPutnika,
                            @Param("valuta") String valuta);
}
