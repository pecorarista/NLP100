#!/bin/bash

if [ ! -d $HOME/jvm ]
then
    mkdir $HOME/jvm
fi
tar -xzvf $1 -C $HOME/jvm
