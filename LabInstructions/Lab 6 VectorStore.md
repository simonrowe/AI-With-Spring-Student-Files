
install Redis by running on Docker:

docker pull redis/redis-stack:latest
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest

Note:  You can set a password on startup like this:
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 -e REDIS_ARGS="--requirepass mypassword" redis/redis-stack:latest

BUT- Then I got a "noauth authentication required" message from Jedis on application startup.