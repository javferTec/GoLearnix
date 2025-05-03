#!/bin/bash

docker run -d --name redis -p 6379:6379 redis redis-server --save 60 1 --loglevel warning
