
def shortestCommonSupersequence(s1: str, s2: str) -> str:
    inf = 10**9 + 7
    n1 = len(s1)
    n2 = len(s2)
    f = [[inf] * (n2+1) for _ in range(n1+1)]
    # 这题不需要 next move 记录，因为通过 比较f[i][j] 已经 隐含了 选择， 但有些题，需要 记录
    # nxt = [[-1] * (n2+1) for _ in range(n1+1)]

    for i in range(n1+1):
        f[i][n2] = n1 - i
    for j in range(n2+1):
        f[n1][j] = n2 - j
    
    # python 倒推的时候 记得 end + 1
    for i in range(n1-1, -1, -1):
        for j in range(n2-1, -1, -1):
            if s1[i] == s2[j]:
                f[i][j] = f[i+1][j+1] + 1
            else:
                if f[i+1][j] < f[i][j+1]:
                    f[i][j] = f[i+1][j]+1
                else:
                    f[i][j] = f[i][j+1]+1

    res = ""
    # 组装答案正着来，因为是next move
    i = j = 0
    while i <= n1 or j <= n2:
        if i == n1:
            res += s2[j:]
            break
        elif j == n2:
            res += s1[i:]
            break
        else:
            if s1[i] == s2[j]:
                res += s1[i]
                i+=1
                j+=1
            elif f[i][j] == f[i+1][j] + 1:
                res += s1[i]
                i+=1
            else:
                res += s2[j]
                j+=1
    return res

s1 = "acbbcccaa"
s2 = "bbbcaaaaa"
res = shortestCommonSupersequence(s1, s2)
print(res)