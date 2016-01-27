from builtins import print

import random
import math
import copy

#number of clusters = k

def isConverge(centroids1, centroids2):
    for centroid1 in centroids1:
        flag = False
        for centroid2 in centroids2:
            if sorted(centroid1.items()) == sorted(centroid2.items()):
                flag = False
                break
            flag = True
        if flag:
            return False
    return True



def normalizer(u):
    keys = u.keys()
    temp_u_cluster = u['cluster']

    u['cluster'] = 0

    vals = u.values()
    sum2 = 0

    for val in vals:
        sum2 += math.pow(val,2)

    sum2 = math.sqrt(sum2)

    for key in keys:
        u[key] = float(u[key] / sum2)

    u['cluster'] = temp_u_cluster

    return u


def pproduct(u, v):

    temp_u_cluster = u['cluster']
    temp_v_cluster = v['cluster']
    # print("u: "+str(temp_u_cluster)+" v: "+str(temp_v_cluster))
    u['cluster'] = 0
    v['cluster'] = 0

    # for key,val in u.items():
    #     u[key] = float(val / u_sum_val)
    #
    # for key,val in v.items():
    #     v[key] = float(val / v_sum_val)

    keys = u.keys() & v.keys()
    s = 0
    for key in keys:
        # u[key] = float(u[key] / u_sum_val)
        # v[key] = float(v[key] / v_sum_val)
        s += float(u[key]) * float(v[key])

    u['cluster'] = temp_u_cluster
    v['cluster'] = temp_v_cluster

    return s
    # return sum([u[key] * v[key] for key in u.keys() & v.keys()])

def psum(u, v):

    # print("u:::")
    # print(u)

    temp_u_cluster = u['cluster']
    temp_v_cluster = v['cluster']

    u['cluster'] = 0
    v['cluster'] = 0

    mutual = u.keys() & v.keys()
    s = {}
    for key in mutual:
        s[key] = u[key] + v[key]

    unmutual = (u.keys() | v.keys()) - (u.keys() & v.keys())

    for key in unmutual:
        if key in u:
            s[key] = u[key]
        else:
            s[key] = v[key]

    u['cluster'] = temp_u_cluster
    v['cluster'] = temp_v_cluster
    s['cluster'] = temp_v_cluster

    if u['cluster'] != v['cluster']:
        print("RIDIMMMMMM")

    return s

def kMeans(dictionaries, k):

    initial_docs = []

    for i in range(len(dictionaries)):
        dictionaries[i]['cluster'] = 1
        dictionaries[i] = normalizer(dictionaries[i])


    #random init
    for i in range(k):
        init_centroid = dictionaries[random.randint(0, len(dictionaries)-1)]
        init_centroid['cluster'] = i+1
        initial_docs.append(init_centroid)

    # init_centroid1 = dictionaries[0]
    # init_centroid1['cluster'] = 1
    # init_centroid2 = dictionaries[1]
    # init_centroid2['cluster'] = 2
    # initial_docs.append(init_centroid1)
    # initial_docs.append(init_centroid2)

    print("Centroids:")
    print(initial_docs)
    print("docs:")
    print(dictionaries)

    currentCentroids = copy.copy(initial_docs)
    previousCentroids = {}
    # for z in range(1000)
    # print(isConverge(currentCentroids, previousCentroids))
    while isConverge(currentCentroids, previousCentroids):
        print("hi")
        for j in range(len(dictionaries)):
            max_similarity = 0
            max_centroid_clus = 0
            for i in range(len(initial_docs)):
                # print("ii: "+str(i))
                centroid = initial_docs[i]
                similarity = pproduct(dictionaries[j], centroid)

                if(similarity >= max_similarity):
                    max_similarity = similarity
                    max_centroid_clus = centroid['cluster']
            dictionaries[j]['cluster'] = max_centroid_clus

        previousCentroids = copy.copy(initial_docs)

        # print("-------")
        # print(initial_docs)
        # print("##########")
        # print(dictionaries)
        #update centroids
        # initial_docs = []
        sum_of_each_cluster = []
        num_of_each_cluster = []

        for i in range(k):
            sum_of_each_cluster.append({})
            num_of_each_cluster.append(0)

        for j in range(len(dictionaries)):
            # print("<========>")
            # print(dictionaries[j]['cluster'])
            if len(sum_of_each_cluster[dictionaries[j]['cluster']-1].keys()):
                sum_of_each_cluster[dictionaries[j]['cluster']-1] = psum(sum_of_each_cluster[dictionaries[j]['cluster']-1], dictionaries[j])
            else:
                sum_of_each_cluster[dictionaries[j]['cluster']-1] = dictionaries[j]
            num_of_each_cluster[dictionaries[j]['cluster']-1] += 1
        # print(sum_of_each_cluster)

        for i in range(k):
            if len(sum_of_each_cluster[i].keys()): #isNotEmpty
                temp = sum_of_each_cluster[i]['cluster']
                for key, val in sum_of_each_cluster[i].items():
                    sum_of_each_cluster[i][key] = str(float(sum_of_each_cluster[i][key])/num_of_each_cluster[i])
                sum_of_each_cluster[i]['cluster'] = temp
                initial_docs[i] = sum_of_each_cluster[i]
        currentCentroids = copy.copy(initial_docs)
        # print(initial_docs)
        # print(dictionaries)

    print(dictionaries)
    print("last Centroids")
    print(currentCentroids)

dictionaries = []
dic1 = {
    'term1': 20,
    'term2': 10,
}
dic2 = {
    'term1': 10,
    'term2': 20,
}
dic3 = {
    'term1': 11,
    'term2': 21,
}
dic4 = {
    'term1': 20,
    'term2': 10,
}
dic5 = {
    'term1': 12,
    'term2': 22,
}
dic6 = {
    'term1': 12,
    'term2': 21,
}
dic7 = {
    'term1': 22,
    'term2': 11,
}
dictionaries.append(dic1)
dictionaries.append(dic2)
dictionaries.append(dic3)
dictionaries.append(dic4)
dictionaries.append(dic5)
dictionaries.append(dic6)
dictionaries.append(dic7)

res = kMeans(dictionaries, 2)
# print(res)
# print(psum(dic1, dic4))