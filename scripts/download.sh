#!/bin/sh

NLP100=http://www.cl.ecei.tohoku.ac.jp/nlp100
RESOURCES=$(git rev-parse --show-toplevel)/src/main/resources

if [ ! -d $RESOURCES ]
then
    mkdir $RESOURCES
fi

for f in hightemp.txt neko.txt jawiki-country.json.gz artist.json.gz
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

for g in jawiki-country.json artist.json
do
    if [ -f $RESOURCES/$g".gz" ] && ! [ -f $RESOURCES/$g ]
    then
        gunzip $RESOURCES/$g
    fi
done
