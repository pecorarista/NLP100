#!/bin/sh

NLP100=http://www.cl.ecei.tohoku.ac.jp/nlp100
RESOURCES=$(git rev-parse --show-toplevel)/src/main/resources

if [ ! -d $RESOURCES ]
then
    mkdir $RESOURCES
fi

for f in hightemp.txt neko.txt jawiki-country.json.gz
do
    wget --no-clobber $NLP100/data/$f -O $RESOURCES/$f
done

if [ -f $RESOURCES/neko.txt.mecab ]
then
    cat $RESOURCES/neko.txt \
        | sed -e '/^$/d' \
        | mecab -o $RESOURCES/neko.txt.mecab
fi

if [ -f $RESOURCES/neko.txt.cabocha ]
then
    cat $RESOURCES/neko.txt \
        | sed -e '/^$/d' \
        | cabocha -f 1 -o $RESOURCES/neko.txt.cabocha
fi

if [ -f $RESOURCES/jawiki-country.json.gz ] && ! [ -f $RESOURCES/jawiki-country.json ]
then
    gunzip $RESOURCES/jawiki-country.json.gz
fi
