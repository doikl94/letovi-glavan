package com.example.letovi.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.example.letovi.models.Let;
import com.example.letovi.repository.LetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class LetServiceImp implements LetService{

    @Autowired
    private LetRepository letRepository;

    @Override
    public List<Let> checkExisting(String polazniAerodrom, String odredisniAerodrom, LocalDate datumPolaska, LocalDate datumPovratka, Integer brojPutnika, String valuta) {

        List<Let> letovi = letRepository.checkExisting(polazniAerodrom, odredisniAerodrom,datumPolaska, datumPovratka,brojPutnika,valuta);
        if(letovi.isEmpty()){

            try{
                return checkNew(polazniAerodrom,odredisniAerodrom,datumPolaska, datumPovratka, brojPutnika, valuta);
            }
            catch (Exception e){
                return null;
            }
        }
        else{
            return letovi;
        }

    }


    @Override
    public List<Let> checkNew(String polazniAerodrom, String odredisniAerodrom, LocalDate datumPolaska, LocalDate datumPovratka, Integer brojPutnika, String valuta) throws ResponseException {

        List<Let> letovi = new ArrayList<>();

        ArrayList<String> presjedanjaPol = new ArrayList<>();
        ArrayList<String> presjedanjaPov = new ArrayList<>();

        Amadeus amadeus = Amadeus
                .builder("QR0hscHGqm4AYTjFaIAZNg4Pwdikry4X", "KBewQ0OGLhuPXS1i")
                .build();

        FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
                Params.with("originLocationCode", polazniAerodrom)
                        .and("destinationLocationCode", odredisniAerodrom)
                        .and("departureDate", datumPolaska)
                        .and("returnDate", datumPovratka)
                        .and("currencyCode", valuta)
                        .and("adults", brojPutnika)
                        .and("max", 10));

        for ( FlightOfferSearch flight : flightOffersSearches)
        {
            FlightOfferSearch.Itinerary[] itinerarys = flight.getItineraries();
            for ( int i = 0;i < itinerarys.length;i++)
            {
                FlightOfferSearch.SearchSegment[] segments = itinerarys[i].getSegments();
                if(i==0){
                    Arrays.stream(segments).forEach(searchSegment -> presjedanjaPol.add(searchSegment.getDeparture().getIataCode()));
                    Arrays.stream(segments).forEach(searchSegment -> presjedanjaPol.add(searchSegment.getArrival().getIataCode()));
                }
                else{
                    Arrays.stream(segments).forEach(searchSegment -> presjedanjaPov.add(searchSegment.getDeparture().getIataCode()));
                    Arrays.stream(segments).forEach(searchSegment -> presjedanjaPov.add(searchSegment.getArrival().getIataCode()));
                }
            }


            Set<String> presjedanjaPolazak= new LinkedHashSet<>(presjedanjaPol);
            Set<String> presjedanjaPovratak= new LinkedHashSet<>(presjedanjaPov);

            String rutaPolaska = "";
            String rutaPovratka = "";

            for (String temp : presjedanjaPolazak) {
                rutaPolaska=rutaPolaska+temp+"-";
            }
            rutaPolaska=rutaPolaska.substring(0,rutaPolaska.length()-1);

            for (String temp : presjedanjaPovratak) {
                rutaPovratka=rutaPovratka+temp+"-";
            }
            rutaPovratka=rutaPovratka.substring(0,rutaPovratka.length()-1);

            Let noviLet = new Let();

            noviLet.setPolazniAerodrom(polazniAerodrom);
            noviLet.setOdredisniAerodrom(odredisniAerodrom);
            noviLet.setCijena(flight.getPrice().getGrandTotal());
            noviLet.setBrojPutnika(brojPutnika);
            noviLet.setRutaPolaska(rutaPolaska);
            noviLet.setRutaPovratka(rutaPovratka);
            noviLet.setDatumPolaska(datumPolaska);
            noviLet.setDatumPovratka(datumPovratka);
            noviLet.setValuta(valuta);


            letRepository.save(noviLet);

            letovi.add(noviLet);

            presjedanjaPol.clear();
            presjedanjaPov.clear();
        }

        System.out.println("Ipak jesmo");
        return letovi;
    }

}
