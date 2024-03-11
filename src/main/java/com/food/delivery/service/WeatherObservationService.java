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
    public List<WeatherObservation> getAllObservations() {
        return weatherObservationRepository.findAll();
    }

    public void saveWeatherObservation(WeatherObservation weatherObservation) {
        weatherObservationRepository.save(weatherObservation);
    }

    /**
     * Method, which processing weather observations data
     *
     */
    @Scheduled(cron = "0 15 * * * ?")
    public void processWeatherData() {

        try {
            NodeList listOfStationsXML = null;
            listOfStationsXML = convertDataToTheListOfStationsXML();

            for (int index = 0; index < listOfStationsXML.getLength(); index++) {
                Node node = listOfStationsXML.item(index);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    if (!name.equals("Tallinn-Harku") && !name.equals("Tartu-Tõravere") && !name.equals("Pärnu"))
                        continue;

                    String wmoCode = element.getElementsByTagName("wmocode").item(0).getTextContent();
                    String airTemperature = element
                            .getElementsByTagName("airtemperature")
                            .item(0).getTextContent();
                    String windSpeed = element
                            .getElementsByTagName("windspeed")
                            .item(0).getTextContent();
                    String phenomenon = element
                            .getElementsByTagName("phenomenon")
                            .item(0).getTextContent();
                    LocalDateTime localDateTime = LocalDateTime.now();

                    WeatherObservation weatherObservation = new WeatherObservation(name, wmoCode,
                            Double.parseDouble(airTemperature), Double.parseDouble(windSpeed),
                            phenomenon, localDateTime);
                    saveWeatherObservation(weatherObservation);

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private NodeList convertDataToTheListOfStationsXML() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());
        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("station");
    }

    public void setUrl(String url) {
        WeatherObservationService.url = url;
    }


}
