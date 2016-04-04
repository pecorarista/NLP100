#!/bin/bash

ROOT_DIR=$(git rev-parse --show-toplevel)
SOURCE_CODES=$ROOT_DIR/src/main/scala/nlp100

if [ -f $ROOT_DIR/progress ]
then
    rm $ROOT_DIR/progress
fi

for i in 01 02 03 04 05 06 07 08 09 10
do

    echo -n " * "chapter"$i "

    d=$SOURCE_CODES/"chapter"$i

    if [ -d "$d" ]
    then

        PROGRESS=$(ls -1 $d | wc -l)

        if [ "$i" == "07" ] && [ -d "$ROOT_DIR/Q69" ]
        then
            PROGRESS=$(($PROGRESS + 1))
        fi

        for j in $(seq 1 10)
        do
            if [ $j -le $PROGRESS ]
            then
                echo -n "+"
            else
                echo -n "-"
            fi
        done

        if [ $PROGRESS -ne 10 ]
        then
            echo -n " "
        fi

        echo " "$PROGRESS"/10"

    else

        echo "----------  0/10"

    fi
done
