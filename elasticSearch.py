from datetime import datetime
import os
import json
import pickle
from elasticsearch import Elasticsearch

def read_data():
    docs = []
    for fileName in os.listdir(os.path.join(os.getcwd(), 'crawler/data')):
        with open(os.path.join('crawler/data', fileName), 'r') as data_file:
            print(os.path.join('crawler/data', fileName))
            data = data_file.read()
            data = data.split('\n')[0]
            data = json.loads(data)
            # tmp = dict()
            # tmp["abs"] = data["abs"]
            docs.append(data)
            # print(data)
    return docs

es = Elasticsearch([{u'host': u'127.0.0.1', u'port': 9200}])

# doc1 = {
#     'author': 'kimchy',
#     'text': 'Elasticsearch: cool. bonsai cool.',
#     'timestamp': datetime.now()
# }
# doc2 = {
#     'author': 'kimchy2',
#     'text': 'Elasticsearch2: cool. bonsai cool.',
#     'timestamp': datetime.now()
# }
#
# docs = [doc1, doc2]


# docs = read_data()
# es.indices.create(index="researchgate-data"
#                   # body={
#                   #        "mappings": {
#                   #            "page": {
#                   #                "_source": {"enabled": True},
#                   #                "properties": {
#                   #                    "url": {
#                   #                        "type": "string"
#                   #                    },
#                   #                    "page_text": {
#                   #                        "type": "string",
#                   #                        "term_vector": "yes"
#                   #                    },
#                   #                    "title": {
#                   #                        "type": "string",
#                   #                        "term_vector": "yes"
#                   #                    }
#                   #                }
#                   #            }
#                   #        }
#                   # }
# )
#
# index_id = 0
# try:
#     for doc in docs:
#         print(index_id)
#         es.index(index="researchgate-data", doc_type="paper", body=doc, id=index_id)
#         index_id += 1
# except:
#     print("error: ")
#     raise


# index_id = 0
# vectors = []
# for doc in docs:
#     temp = es.termvectors(index="researchgate-data",  doc_type="paper", id=index_id,  term_statistics=False, field_statistics=False, fields=['abs'])
#     index_id += 1
#     vec = temp["term_vectors"]["abs"]["terms"]
#     vec = dict(vec)
#     vec_clean = {}
#     for k, v in vec.items():
#         vec_clean[k] = v['term_freq']
#     vectors.append(vec_clean)
# print(vectors[len(vectors)-1])

# with open('dictionaries_object.object', 'wb') as file:
#     pickle.dump(vectors, file)

# with open('dictionaries.txt','w') as file:
#     file.write(vectors.__str__())

# es.indices.delete(index="researchgate-data")

x = input("Enter weight for the title: ")
y = input("Enter weight for the authors: ")
z = input("Enter weight for the abstract: ")
w = input("Enter weight for the page rank: ")

q = input("Enter your query for searching: ")


# x = "2.5"
# y = "0.3"
# z = "1.5"

res = es.search(index='researchgate-data', body={
    "query": {
        "multi_match": {
            "query": q,
            "fields": ["title^"+x, "names^"+y, "abs^"+z]
        }
    }})

print(res)






# b=dict([(k, v['term_freq'])for k,v in temp.iteritems()])
# -----------------------------------
# res = es.index(index="researchgate-data", doc_type='paper', id=1, body=doc)
# print(res['created'])
#
# res = es.get(index="researchgate-data", doc_type='paper', id=1)
# print(res['_source'])
#
# es.indices.refresh(index="researchgate-data")
# #termVector
#
# res = es.search(index="researchgate-data", body={"query": {"match_all": {}}})
#
# print("Got %d Hits:" % res['hits']['total'])
# for hit in res['hits']['hits']:
#     print("%(timestamp)s %(author)s: %(text)s" % hit["_source"])

