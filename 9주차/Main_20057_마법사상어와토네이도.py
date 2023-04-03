from collections import deque
n = int(input())
arr = [[*map(int, input().split())] for _ in range(n)]
visit = [[0 for _ in range(n)] for _ in range(n)]
y, x = n//2, n//2
d = [0, 1, 0, -1]
visit[y][x] = 1
visit[y][x-1] = 1
queue = deque([[y, x-1, 0]])


def scatter(y, x, v):
    dy, dx = d[v], d[(v+3)%4]
    # 5%, 10%, 10%, 7%, 7%, 2%, 2%, 1%, 1%
    scatterVec = [[dy*2, dx*2], [dy, 1] if dy else [1, dx], [dy, -1] if dy else [-1, dx],
                  [0, 1] if dy else [1, 0], [0, -1] if dy else [-1, 0], [0, 2] if dy else [2, 0],
                  [0, -2] if dy else [-2, 0], [-dy, 1] if dy else [1, -dx], [-dy, -1] if dy else [-1, -dx]]
    scatterPer = [0.05, 0.1, 0.1, 0.07, 0.07, 0.02, 0.02, 0.01, 0.01]
    total = arr[y][x]
    outOfGrid = 0
    for i in range(9):
        yy = y + scatterVec[i][0]
        xx = x + scatterVec[i][1]
        sand = int(arr[y][x] * scatterPer[i])
        if sand:
            total -= sand
        if 0 <= yy < n and 0 <= xx < n:
            arr[yy][xx] += sand
        else:
            outOfGrid += sand
    if total:
        yy = y + dy
        xx = x + dx
        if 0 <= yy < n and 0 <= xx < n:
            arr[yy][xx] += total
        else:
            outOfGrid += total
    return outOfGrid


answer = 0
while queue:
    y, x, v = queue.popleft()
    answer += scatter(y, x, v)
    nextY, nextX = y + d[v], x+d[(v+3)%4]
    if y == 0 and x == 0:
        break
    checkY, checkX = y + d[(v+1)%4], x + d[v]
    if 0 <= checkY < n and 0 <= checkX < n and visit[checkY][checkX] == 0:
        nextY, nextX, v = checkY, checkX, (v+1)%4
    visit[nextY][nextX] = 1
    queue.append([nextY, nextX, v])

print(answer)