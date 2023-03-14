import sys
from collections import deque
from copy import deepcopy
input = sys.stdin.readline

answer = 0
temp = 0
end = 0
left = 0

def choise(y,x,v,q):
    global temp
    global end
    global left
    q.append([y,x])
    if v == 3:
        temp = 0
        left = 0
        arr = deepcopy(mp)
        kill(q, arr)
        return
    for j in range(x+1, m):
        if mp[n][j] == 0:
            if end == 1:
                return
            choise(n,j,v+1,q)
            q.pop()

def kill(q, arr):
    global temp
    k = set()
    for arc in q:
        queue = deque([arc])
        visit = [[0 for _ in range(m)] for _ in range(n+1)]
        visit[arc[0]][arc[1]] = 1
        while queue:
            y, x = queue.popleft()
            for i in range(4):
                yy = y + dy[i]
                xx = x + dx[i]
                if 0 <= yy < n+1 and 0 <= xx < m and visit[yy][xx] == 0 and visit[y][x] <= r:
                    if arr[yy][xx] == 1:
                        k.add((yy,xx))
                        queue = []
                        break
                    else:
                        visit[yy][xx] = visit[y][x]+1
                        queue.append([yy, xx])
    for y, x in k:
        temp += 1
        arr[y][x] = 0
    move(q,arr)

def move(q,arr):
    global temp
    global answer
    global end
    global left
    if temp == s:
        end = 1
        answer = temp
        return
    for i in range(m):
        if arr[n-1][i] == 1:
            arr[n-1][i] = 0
            left += 1
    if temp + left == s:
        answer = max(answer, temp)
        return
    for i in range(n-2,-1,-1):
        for j in range(m):
            if arr[i][j] == 1:
                arr[i+1][j] = 1
                arr[i][j] = 0
    kill(q,arr)


n, m, r = map(int,input().split())
mp = [list(map(int,input().split())) for _ in range(n)] + [[0]*m]
s = 0
for i in range(n):
    s += sum(mp[i])
dy = [0,1,-1,0]
dx = [-1,0,0,1]
for j in range(m):
    choise(n,j,1,[])
print(answer)