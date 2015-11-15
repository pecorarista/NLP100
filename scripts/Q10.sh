#!/bin/bash

cat $(git rev-parse --show-toplevel)/src/main/resources/hightemp.txt \
    | wc -l
