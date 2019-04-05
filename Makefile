NLP100 := http://www.cl.ecei.tohoku.ac.jp/nlp100/data
RESOURCES := src/main/resources

.PHONY: all

all: $(RESOURCES)/hightemp.txt $(RESOURCES)/neko.txt $(RESOURCES)/nlp.txt \
     $(RESOURCES)/neko.txt.mecab $(RESOURCES)/neko.txt.cabocha \
     $(RESOURCES)/jawiki-country.json $(RESOURCES)/artist.json

$(RESOURCES)/hightemp.txt $(RESOURCES)/neko.txt $(RESOURCES)/nlp.txt $(RESOURCES)/jawiki-country.json.gz $(RESOURCES)/artist.json.gz:
	if [ ! -d $(RESOURCES) ]; \
	then \
		mkdir -p $(RESOURCES); \
	fi
	wget -P $(RESOURCES) $(NLP100)/$(shell basename $@)

$(RESOURCES)/neko.txt.mecab: $(RESOURCES)/neko.txt
	cd $(RESOURCES); cat neko.txt | sed -e '/^$$/d' | mecab -o neko.txt.mecab

$(RESOURCES)/neko.txt.cabocha: $(RESOURCES)/neko.txt
	cd $(RESOURCES); cat neko.txt | sed -e '/^$$/d' | cabocha -f 1 -o neko.txt.cabocha

$(RESOURCES)/jawiki-country.json: $(RESOURCES)/jawiki-country.json.gz
	cd $(RESOURCES); gunzip $(shell basename $@).gz

$(RESOURCES)/artist.json: $(RESOURCES)/artist.json.gz
	cd $(RESOURCES); gunzip $(shell basename $@).gz
