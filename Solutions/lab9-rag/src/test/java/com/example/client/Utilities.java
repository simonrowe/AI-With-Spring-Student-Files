package com.example.client;

import java.util.List;

public interface Utilities {

    //  Sample product catalog:
    List <String> productCatalog = List.of(
            "Wireless Mouse: A comfortable wireless mouse with ergonomic design and long battery life, perfect for seamless connectivity without cables.  See https://ourstore.com/123.  ",
            "Wireless Headphones: Lightweight, noise-canceling technology, immersive sound and long battery life, ideal for people on-the-go.  See https://ourstore.com/123.  ",
            "Bluetooth Speaker: Portable Bluetooth speaker with excellent sound quality and long battery life, suitable for outdoor use and parties.  See https://ourstore.com/123.  ",
            "4K Monitor: A 27-inch 4K UHD monitor with vibrant colors and high dynamic range for stunning visuals, providing an exceptional viewing experience.  See https://ourstore.com/123.  ",
            "Wireless Fitness Tracker: Track steps, distance, calories, and sleep with water-resistant design, built-in heart rate monitor, and smartphone notifications. Choose your style with multiple colors!  See https://ourstore.com/123.  ",
            "Smart Home Hub: Control lights, thermostats, and appliances with voice commands (Alexa & Google Assistant). Easy setup, create routines, and monitor energy usage for savings.  See https://ourstore.com/123.  ",
            "3D Printer: Beginner-friendly 3D printer with large print volume, Wi-Fi/USB connectivity, and a variety of compatible filaments. Built-in heated bed ensures high-quality prints.  See https://ourstore.com/123.  ",
            "VR Headset: Experience stunning visuals with a high-resolution display and wide field of view. Move naturally within VR worlds with motion tracking technology. Explore a growing library of games, experiences, and educational content. Designed for extended comfort.",
            "Foldable Drone: Capture breathtaking aerial footage with this compact, foldable drone. Easy to transport and perfect for capturing stunning views on the go.  See https://ourstore.com/123.  ",
            "Smart Garden Kit: Grow fresh herbs and vegetables year-round with this automated smart garden. Features automatic watering and lighting for effortless gardening.  Great for healthy eating.  See https://ourstore.com/567.  ",
            "Solar-Powered Phone Charger: Never run out of power again! This portable solar charger keeps your phone juiced up anywhere, using the power of the sun.  See https://ourstore.com/123.  ",
            "AI-Powered Language Translator: Break down language barriers in real-time with this innovative translator. Speaks and translates conversations seamlessly.  See https://ourstore.com/123.  ",
            "Wireless Earbuds: Compact, noise-canceling technology, immersive sound and long battery life, great for travel.  See https://ourstore.com/567"
    );

    //  Sample product catalog:
    List <String> productMatches = List.of(
        "Wireless Headphones: Lightweight, noise-canceling technology, immersive sound and long battery life, ideal for people on-the-go.  See https://ourstore.com/123.  ",
        "Wireless Earbuds: Compact, noise-canceling technology, immersive sound and long battery life, great for travel.  See https://ourstore.com/567"
    );

    //  Sample user query:
    String query = "I need high quality wireless headphones to block out noise on a plane";

    String[] sampleResults = {"ireless", "noise-cancel", "https", "123"};

}
