package com.example.client;

public interface AIClient {

    /**
     * Return a URL String to the generated image.
     * @param request   The text prompt describing the image to generate.
     * @return          The URL String to the generated image.
    */
    String createImageUrl(String request);

    String createImageB64(String request);

}
