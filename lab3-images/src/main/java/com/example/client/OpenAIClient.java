package com.example.client;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//  TODO-04: Use a stereotype annotation to mark this class as a Spring bean.
//  Use an annotation to assign it to the "openai" profile.
@Component
@Profile("openai")
public class OpenAIClient {

    //  TODO-05: Define a private final field named "model" of type OpenAIImageModel.
    //   Define a constructor which dependency injects this field:

    private final OpenAiImageModel model;

    public OpenAIClient(OpenAiImageModel model) {
        this.model = model;
    }

    private static final OpenAiImageOptions DEFAULT_OPTIONS = OpenAiImageOptions.builder().withN(1).build();


    public String createImageUrl(String request) {

        //  TODO-06: Create an ImagePrompt object using the request and the DEFAULT_OPTIONS.
        //  (Feel free to experiment with your own custom-defined OpenAiImageOptions later).
        ImagePrompt prompt = new ImagePrompt(
                request,
                DEFAULT_OPTIONS
            );

        //  TODO-07: Create a variable of type ImageResponse.
        //   Populate it by calling model.call(), passing the prompt you've just created.
        ImageResponse response = model.call(prompt);

        //  TODO-08: Comment out the "return null;" line.
        //  Return the URL String from the previous call.
        //  It can be found by traversing the result / output.
        //  Organize your imports and save your work.
        return response.getResult().getOutput().getUrl();
        // return null;

    }

}
