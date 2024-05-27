
The most universal way to run Ollama is via Docker container.
Make sure Docker is installed and running.  Running `docker ps` should result in no error.
Run the following commands:
```
docker pull ollama/ollama
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
docker exec -it ollama ollama run llama2
```
TODO THIS STILL DIDN'T WORK FOR ME STARTING FRESH.  I COULD SEE THE MODEL DOWNLOADED AND RUNNING IN THE CONTAINER.
OK, FOR SOME REASON RUNNING ollama run llama2 INSIDE THE CONTAINER DOESN'T WORK.  IF YOU RUN THIS FROM THE COMMAND
LINE THE MODEL WORKS.  WEIRD.  THERE IS NO MODEL RUNNING IN THE CONTAINER, BUT IT WORKS.

The first command downloads the official ollama image from Dockerhub.
The second runs ollama in the background, setting up a folder on your home directory called ".ollama" for storage.  It listens for traffic on port 11434, which is exactly what the SpringAI client will expect.
The third command instructs ollama to download the llama2 model.  Once this is done the container will respond to requests targeting the given model. 

Warning - these models are large; the llama2 model will be a 4GB download.  Llama3 is closer to 40!

If Docker does not work for any reason, you can also download local software for Windows, Mac, and Linux, see https://github.com/ollama/ollama/blob/main/README.md

Once this is done, you can run a command like: `ollama run llama2`


