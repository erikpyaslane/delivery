package com.food.delivery.service;

import com.food.delivery.entity.WeatherObservation;
import com.food.delivery.repository.WeatherObservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class WeatherObservationService {

    private final WeatherObservationRepository weatherObservationRepository;

    private static String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

    public WeatherObservationService(WeatherObservationRepository weatherObservationRepository) {
        this.weatherObservationRepository = weatherObservationRepository;
    }

    /**
     * Gets all weather observations from database using repository
     * @return List of WeatherObservation objects
     */
    public List<WeatherObservation> getAllWeatherObservations() {
        return weatherObservationRepository.findAll();
    }
    /**
     * Gets the latest weather observation from database using repository
     * @return List of WeatherObservation objects
     */
    public List<WeatherObservation> getLatestWeatherObservations() {
        return weatherObservationRepository.findTop3ByOrderByTimeOfObservationDesc();
    }

    /**
     * Method, which gets all observations of city name
     *
     * @param cityName name of city
     * @return list of WeatherObservation objects
     */
    public List<WeatherObservation> getObservationsByCityName(String cityName) {
        String stationName = mapCityNameToStationName(cityName);
        return weatherObservationRepository.findWeatherObservationsByStationName(stationName);
    }

    /**
     * Method, which gets all observations of city name
     *
     * @param cityName name of city
     * @return list of WeatherObservation objects
     */
    public WeatherObservation getLatestObservationByCityName(String cityName) {
        String stationName = mapCityNameToStationName(cityName);
        return weatherObservationRepository.findTopByStationNameOrderByTimeOfObservationDesc(stationName);
    }

    /**
     * Method, which saves WeatherObservation object to database
     * @param weatherObservation object to save
     */
    public void saveWeatherObservation(WeatherObservation weatherObservation) {
        weatherObservationRepository.save(weatherObservation);
    }
    /**
     * Method, which saves list of WeatherObservation objects to database
     * @param weatherObservations list of objects to save
     */
    public void saveWeatherObservations(List<WeatherObservation> weatherObservations) {
        weatherObservationRepository.saveAll(weatherObservations);
    }

    /**
     * Method, which processes weather observations data
     * Automatically every hour at HH:15
     *
     */
    @Scheduled(cron = "0 15 * * * ?")
    public void updateWeatherData() {

        try {
            NodeList stationList = fetchStationDataFromXML();
            List<WeatherObservation> weatherObservations = extractValidObservations(stationList);
            saveWeatherObservations(weatherObservations);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private NodeList fetchStationDataFromXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("station");
    }

    private List<WeatherObservation> extractValidObservations(NodeList stationList) {
        List<WeatherObservation> observations = new ArrayList<>();
        for (int index = 0; index < stationList.getLength(); index++) {
            Node node = stationList.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                if (isValidStation(name)) {
                    WeatherObservation observation = createObservationFromElement(element);
                    observations.add(observation);
                }
            }
        }
        return observations;
    }

    private WeatherObservation createObservationFromElement(Element element) {

        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String wmoCode = element.getElementsByTagName("wmocode").item(0).getTextContent();
        String airTemperature = element.getElementsByTagName("airtemperature").item(0).getTextContent();
        String windSpeed = element.getElementsByTagName("windspeed").item(0).getTextContent();
        String phenomenon = element.getElementsByTagName("phenomenon").item(0).getTextContent();
        LocalDateTime localDateTime = LocalDateTime.now();

        return new WeatherObservation(name, wmoCode, Double.parseDouble(airTemperature),
                Double.parseDouble(windSpeed), phenomenon, localDateTime);
    }

    private boolean isValidStation(String name) {
            return name.equals("Tallinn-Harku") || name.equals("Tartu-Tõravere") || name.equals("Pärnu");
    }

    private String mapCityNameToStationName(String cityName) {
        return switch (cityName) {
            case "Tallinn" -> cityName + "-Harku";
            case "Tartu" -> cityName + "-Tõravere";
            default -> cityName;
        };
    }

    public void setUrl(String url) {
        WeatherObservationService.url = url;
    }


}
