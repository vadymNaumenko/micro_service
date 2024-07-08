#1/bin/bash
docker run -it --name processing-service -p 8090:8090 \
-e EUREKA_HOST="172.17.0.1" \
-d processing-service