#!/bin/bash

NLP100=http://www.cl.ecei.tohoku.ac.jp/nlp100
RESOURCES=$(git rev-parse --show-toplevel)/src/main/resources

if [ ! -d $RESOURCES ]
then
    mkdir $RESOURCES
fi

for f in hightemp.txt neko.txt
do
    if [ ! -f $RESOURCES/$f ]
    then
        wget $NLP100/data/$f -O $RESOURCES/$f
    fi
done

if [ ! -f $RESOURCES/neko.txt.mecab ]
then
    cat $RESOURCES/neko.txt \
        | sed -e '/^$/d' \
        | mecab -o $RESOURCES/neko.txt.mecab
fi

if [ ! -f $RESOURCES/neko.txt.cabocha ]
then
    cat $RESOURCES/neko.txt \
        | sed -e '/^$/d' \
        | cabocha -f 1 -o $RESOURCES/neko.txt.cabocha
fi

for f in jawiki-country.json artist.json
do
    if [ ! -f $RESOURCES/$f".gz" ] && [ ! -f $RESOURCES/$f ]
    then
        wget $NLP100/data/$f -O $RESOURCES/$f".gz"
    fi

    if [ -f $RESOURCES/$f".gz" ] && [ ! -f $RESOURCES/$f ]
    then
        gunzip $RESOURCES/$f".gz"
    fi
done

echo "[Ready]"
