import sys
input = sys.stdin.readline

# Trie 생성
def make(s):
    ret, flag, t = "", True, trie
    for i in range(len(s)):
        x = s[i]
        if x not in t[0]:
            t[0][x] = [{}, 0]
            if flag:
                ret = s[:i+1]
                flag = False
        t = t[0][x]
    t[1] += 1
    if flag:
        ret = s + ["", str(t[1])][t[1]>1]
    return ret

# idx 0:= dict, 1:= 개수 확인용
trie = [{}, 0]
for _ in range(int(input())):
    print(make(input().strip()))
