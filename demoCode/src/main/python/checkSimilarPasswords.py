
from typing import List

def checkSimilarPasswords(olds : List[str], news : List[str]) -> List[str]:
    
    def next(ch):
        return chr((ord(ch) - 97 + 1) % 26 + 97)
    
    def check(o, n):
        i = j = 0
        while i < len(o) and j < len(n):
            if o[i] == n[j] or o[i] == next(n[j]):
                i += 1
                j += 1
            else:
                j += 1
        return i == len(o)
    
    # print(next('a'), next('z'))
    res = []
    for o , n in zip(olds, news):
        if check(o, n):
            res.append('YES')
        else:
            res.append('NO')
    return res
    


news = ["baacbab","accdb", "baacba"]
olds = ["abdbc", "ach", "abb"]
print(checkSimilarPasswords(olds, news))