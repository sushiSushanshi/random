package com.person.random.service;

import com.github.javafaker.Faker;
import com.person.random.model.FaceRequest;
import com.person.random.model.FaceResponse;
import com.person.random.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class InformationService {
    private final WebClient webClient;
    private final Faker faker;
    private final Random random = new Random();

    @Autowired
    public InformationService(WebClient webClient) {
        this.webClient = webClient;
        this.faker = new Faker(new Locale("en"));
    }

    public Mono<PersonInfo> generatePerson() {
        FaceRequest request = new FaceRequest();
        request.setType("F");
        request.setAge("21-35");
        request.setRace("white");
        request.setEmotion("happy");

        return webClient.post()
                .uri("https://thispersonnotexist.org/load-faces")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(FaceResponse.class)
                .map(faceResponse -> {
                    List<String> faceIds = faceResponse.getFc();
                    String randomFaceId = faceIds.get(random.nextInt(faceIds.size()));

                    String firstName = faker.name().firstName();
                    String lastName = faker.name().lastName();
                    int age = 21 + random.nextInt(15); // between 21-35

                    PersonInfo person = new PersonInfo();
                    person.setFirstName(firstName);
                    person.setLastName(lastName);
                    person.setAge(age);
                    person.setImageUrl("https://thispersonnotexist.org/downloadimage/" + randomFaceId);

                    return person;
                });
    }
}
