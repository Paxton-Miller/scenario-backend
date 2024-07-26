import pkuseg
from collections import Counter
import pprint
import sys
import io
import json

def load_stop_words(stop_words_path):
    # load stop words
    stopwords = set()
    with open(stop_words_path, 'r', encoding='utf-8') as f:
        for line in f:
            stopwords.add(line.strip())
    return stopwords

def load_content():
    # load main content
    content = []
    with open("D:\\24PostGraduate\\ConceptualScenario\\wordcloud\\cn.txt", encoding="utf-8") as f:
        content = f.read()
    return content

def statistic_words(stopwords, content):
    seg = pkuseg.pkuseg(postag=False)
    words = seg.cut(content)
    filtered_words = [word for word in words if word not in stopwords]
    counter = Counter(filtered_words)
    return counter

def counter2json(counter):
    json_array = []

    for word, count in counter:
        json_array.append({"name": word, "value": count})

    json_output = json.dumps(json_array, ensure_ascii=False)
    return json_output

if __name__ == '__main__':
    stop_words_path = sys.argv[1]
    content = sys.argv[2]
    print(content)
    counter = statistic_words(load_stop_words(stop_words_path), content)
    # pprint.pprint(counter.most_common(20))
    # sys.stdout.flush()
    # do set the print encoding because springboot contradicts with its encoding
    sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
    print(counter2json(counter.most_common(20)))