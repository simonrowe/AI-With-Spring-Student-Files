package com.example.dao;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles({"redis-vector-store","openai-embedding"})
public class ProductVectorDaoTests {

    @Autowired ProductVectorDao dao;

    @BeforeEach
    public void setUp() {
        List <String> products = List.of(
            "Wireless Mouse: A comfortable wireless mouse with ergonomic design and long battery life, perfect for seamless connectivity without cables.",
            "Wireless Headphones: Lightweight, noise-canceling technology, immersive sound and long battery life, ideal for people on-the-go.",
            "Bluetooth Speaker: Portable Bluetooth speaker with excellent sound quality and long battery life, suitable for outdoor use and parties.",
            "4K Monitor: A 27-inch 4K UHD monitor with vibrant colors and high dynamic range for stunning visuals, providing an exceptional viewing experience.",
            "Wireless Fitness Tracker: Track steps, distance, calories, and sleep with water-resistant design, built-in heart rate monitor, and smartphone notifications. Choose your style with multiple colors!",
            "Smart Home Hub: Control lights, thermostats, and appliances with voice commands (Alexa & Google Assistant). Easy setup, create routines, and monitor energy usage for savings.",
            "3D Printer: Beginner-friendly 3D printer with large print volume, Wi-Fi/USB connectivity, and a variety of compatible filaments. Built-in heated bed ensures high-quality prints.",
            "VR Headset: Experience stunning visuals with a high-resolution display and wide field of view. Move naturally within VR worlds with motion tracking technology. Explore a growing library of games, experiences, and educational content. Designed for extended comfort.",
            "Foldable Drone: Capture breathtaking aerial footage with this compact, foldable drone. Easy to transport and perfect for capturing stunning views on the go.",
            "Smart Garden Kit: Grow fresh herbs and vegetables year-round with this automated smart garden. Features automatic watering and lighting for effortless gardening.",
            "Solar-Powered Phone Charger: Never run out of power again! This portable solar charger keeps your phone juiced up anywhere, using the power of the sun.",
            "AI-Powered Language Translator: Break down language barriers in real-time with this innovative translator. Speaks and translates conversations seamlessly."
        );
        dao.add(products);
    }

    @Test
    public void testFindClosestMatch() {
        String query = "I need a gift for my mom to help her with diet and exercise.";
        List<String> results = dao.findClosestMatches(query);
        results.forEach(System.out::println);

        assertThat(results)
                .extracting(element -> element.startsWith("Wireless Fitness Tracker"))
                .contains(true);
        assertThat(results)
                .extracting(element -> element.startsWith("Smart Garden Kit"))
                .contains(true);
    }


}
